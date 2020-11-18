package gramatica;

import exceptions.BadFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GramaticaService {

    private static final String patternString = "[a-zA-Z]{1}\\s+->\\s+[a-zA-Z]+";
    private static final String formatoGramatica = "SimboloInput -> Simbolos. Ej: S -> ABC";
    private static final String EPSILON = "E";
    private static final String ESTADO_INICIAL = "S";

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
        int cantEstadoInicial = 0;

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

                if(parts[0].contains(ESTADO_INICIAL))
                    cantEstadoInicial += 1;

                produccion.setSimboloInput(parts[0].trim());
                String simbolos = parts[1].trim();
                List<Character> simbolosList = produccion.getSimbolos();

                for (int i = 0; i < simbolos.length(); i++) {
                    simbolosList.add(simbolos.charAt(i));
                }
                produccion.setSimbolos(simbolosList);


                producciones.add(produccion);

                response.setEstadoInicial(ESTADO_INICIAL);
                response.setProducciones(producciones);

            }

            if (cantEstadoInicial == 0) {
                String msj = "Ocurrio un error, la gramática no tiene estado inicial S.";
                System.out.println(msj);
                throw new BadFileException(msj);
            }

          /* Este checkeo lo comentamos hasta consultar con ivo la duda de la tarjeta
           * https://trello.com/c/3kRRqbuQ
          if (cantEstadoInicial > 1) {
                String msj = "Ocurrio un error, la gramática tiene más de un estado inicial S.";
                System.out.println(msj);
                throw new BadFileException(msj);
            }
            */


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


}
