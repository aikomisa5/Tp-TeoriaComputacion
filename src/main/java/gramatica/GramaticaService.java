package gramatica;

import exceptions.BadFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import automata.Automata;

public class GramaticaService {

    private static final String patternString = "[a-zA-Z]{1}\\s+->\\s+[a-zA-Z]+";
    private static final String formatoGramatica = "SimboloInput -> Simbolos. Ej: S -> ABC";
    private static final String EPSILON = "E";
    private static final String SIMBOLO_INICIAL = "S";
	private static final String LINEA = "----------------------------------------";

    /**
     * Metodos publicos
     **/

    public Gramatica getGramaticaFromTxtFile(String nombreGramatica) throws BadFileException, FileNotFoundException {

        return readGramaticaFromTxtFile(nombreGramatica);
    }

    /**
     * Metodos privados
     **/


    private Gramatica readGramaticaFromTxtFile(String nombreGramatica) throws BadFileException, FileNotFoundException {
        Gramatica response = new Gramatica();

        Scanner myReader = null;
        int cantSimboloInicial = 0;

        try {
            File myObj = new File("src/main/resources/" + nombreGramatica);
            myReader = new Scanner(myObj);

            if (myReader.hasNextLine() == false) {
                String msj = "Ocurrio un error al intentar obtener los datos de la gramática del archivo, debido a que el archivo esta vacio";
                System.out.println(msj);
                throw new BadFileException(msj);
            }

            List<Produccion> producciones = new ArrayList<Produccion>();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                Pattern pattern = Pattern.compile(patternString);

                Matcher matcher = pattern.matcher(data);
                boolean matches = matcher.matches();

                if (matches == false) {
                    String msj = "Ocurrio un error, la producción no cumple con el formato necesario en el archivo. La misma debe ser de la forma: " + formatoGramatica + ", y la misma es: " + data;
                    System.out.println(msj);
                    throw new BadFileException(msj);
                }

                Produccion produccion = new Produccion();

                /***Ejemplo: S -> ABC***/

                String[] parts = data.split("->");

                if(parts[0].contains(SIMBOLO_INICIAL))
                    cantSimboloInicial += 1;

                produccion.setSimboloInput(parts[0].trim());
                String simbolos = parts[1].trim();
                List<Character> simbolosList = produccion.getSimbolos();

                for (int i = 0; i < simbolos.length(); i++) {
                    simbolosList.add(simbolos.charAt(i));
                }
                produccion.setSimbolos(simbolosList);


                producciones.add(produccion);

                response.setEstadoInicial(SIMBOLO_INICIAL);
                response.setProducciones(producciones);

            }

            if (cantSimboloInicial == 0) {
                String msj = "Ocurrio un error, la gramática no tiene estado inicial S.";
                System.out.println(msj);
                throw new BadFileException(msj);
            }


        } catch (FileNotFoundException e) {
            System.out.println("Ocurrio un error, debido a que no se encontro el archivo: " + e.getMessage());
            throw e;
        } finally {
            if (myReader != null) {
                myReader.close();
            }
        }


        return response;
    }
    
    public boolean algoritmoCYK(Gramatica gramatica, String palabra) {
		if(palabra == null ) {
			throw new NullPointerException("El string no debe ser null");
		}
		
		if(palabra.length() == 0) {
			throw new IllegalArgumentException("Debe ingresar un string con longitud mayor o igual a 1");
		}
	
		if (gramatica.isInFNC()) {
			int n = palabra.length();
			int r = gramatica.getProducciones().size();
			
	//		P[n,n,r] es un array de booleans. Inicializo todos los elementos de P en false. 
			boolean P[][][] =  new boolean[n][n][r];
			for(int i = 0 ; i < n ; i++) {
				for (int j = 0 ; j < n ; j++) {
					for (int k = 0 ; k < r ; k++) {
						P[i][j][k] = false;
					}
				}
			}
				
	//		Para cada produccion de longitud 1:  Rj -> ai
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < r ; j++) {
					Produccion produccion = gramatica.getProducciones().get(j);
					if(produccion.getSimbolos().size() == 1) {
						for(Character c : produccion.getSimbolos()) {
							if(c.equals(palabra.charAt(i)) && gramatica.esSimboloTerminalNoEpsilon(c)) {
								int k = gramatica.buscarIndiceProduccion(produccion.getSimboloInput());
									P[i][0][k] = true;
							}
						}
					}
				}
			}	
						
	//	    Para cada produccion con longitud 2: R_A -> R_B R_C. 
			for(int i = 1 ; i < n ; i++) {  
				for (int j = 0; j < n - i ; j++) {
					for(int k = 0 ; k < i ; k++) {
						for (int p = 0 ; p < r ; p++) {
							Produccion produccion = gramatica.getProducciones().get(p);
							if (produccion.getSimbolos().size() == 2) {
								
								int A = gramatica.buscarIndiceProduccion(produccion.getSimboloInput());
								int B = gramatica.buscarIndiceProduccion(produccion.getSimbolos().get(0).toString());
								int C = gramatica.buscarIndiceProduccion(produccion.getSimbolos().get(1).toString());
									
								if (P[j][k][B] == true && P[j + k + 1][i - k - 1][C] == true) {
									P[j][i][A] = true;
								}
							}
						}
					}
				}
			}
			
			// Si el simbolo inicial S está en el indice [0][n-1], entonces el string es reconocido por la gramatica
			int s = gramatica.buscarIndiceProduccion(gramatica.getEstadoInicial());
			if(P[0][n-1][s]) {
				return true;
			}
		}
		return false;	
	}
    
    public void printGramatica(Gramatica gramatica) {
		System.out.println(LINEA);
		System.out.println("Simbolo inicial: "+ SIMBOLO_INICIAL);
		System.out.println("Gramatica:");
		gramatica.getProducciones().forEach(g ->{
			System.out.println(g.getSimboloInput() + " -> " + g.getSimbolos());
		});
		System.out.println(LINEA);
    }
}