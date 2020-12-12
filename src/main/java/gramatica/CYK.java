package gramatica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CYK {

	private Gramatica gramatica;
    private static final String terminalesPatternString = "[a-df-z]";
	
	public CYK(Gramatica gramatica) {
		this.gramatica = gramatica;
	}

	public void parsingCYK(String palabra) {
		if(algoritmoCYK(palabra)) {
			System.out.println("S pertenece a X_1"+ palabra.length() + ", la gramática genera el string "+ palabra);
		}else {
			System.out.println("S no pertenece a X_1"+ palabra.length() + ", la gramática no genera el string "+ palabra);
		}
	}
	
	public boolean algoritmoCYK(String palabra) {
		if(palabra == null ) {
			throw new NullPointerException("El string no debe ser null");
		}
		
		if(palabra.length() == 0) {
			throw new IllegalArgumentException("Debe ingresar un string con longitud mayor o igual a 1");
		}
		
		if (gramatica.isInFNC() && esMinuscula(palabra)) {
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
							if(c.equals(palabra.charAt(i)) && esTerminal(c)) {
								int k = buscarIndiceProduccion(produccion.getSimboloInput());
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
								
								int A = buscarIndiceProduccion(produccion.getSimboloInput());
								int B = buscarIndiceProduccion(produccion.getSimbolos().get(0).toString());
								int C = buscarIndiceProduccion(produccion.getSimbolos().get(1).toString());
									
								if (P[j][k][B] == true && P[j + k + 1][i - k - 1][C] == true) {
									P[j][i][A] = true;
								}
							}
						}
					}
				}
			}
			
			// Si el simbolo inicial S está en el indice [0][n-1], entonces el string es reconocido por la gramatica
			int s = buscarIndiceProduccion(gramatica.getEstadoInicial());
			if(P[0][n-1][s]) {
				return true;
			}
		}
		return false;	
	}
		
	private int buscarIndiceProduccion(String simbolo) {
		int indice = -1;
		for(int i = 0 ; i < gramatica.getProducciones().size() ; i++) {
			if(gramatica.getProducciones().get(i).getSimboloInput().equals(simbolo)) {
				indice = i;
			}
		}
		return indice;
	}
	
	private boolean esTerminal(Character simbolo){
		Pattern pattern = Pattern.compile(terminalesPatternString);
		Matcher matcher = pattern.matcher("" + simbolo);
	    return matcher.matches();
	}
	
	private boolean esMinuscula(String palabra) {
		return palabra.equals(palabra.toLowerCase());
	}
}