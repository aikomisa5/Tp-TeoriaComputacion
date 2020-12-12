package lr0.gramatica;

import exceptions.BadFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserLR0Service {

    private static final String patternString = "X_\\{[0-9]+\\}\\s+->\\s+(([a-z]*(X_\\{[0-9]+\\})*[a-z]*))+";
    private static final String formatoGramatica = "<variable> -> <body>. Ej: X_{4} -> X_{34}aX_{1}bcd";


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

                /***Ejemplo: X_{4} -> X_{34}aX_{1}bcd  or  <variable>  -> <body>  </></variable>***/

                String[] parts = data.split("->");

                if (parts[0].contains(Gramatica.SIGNO_DISTINGUIDO))
                    cantEstadoInicial += 1;

                produccion.setVariable(parts[0].trim());

                String simbolos = parts[1].trim();
                List<String> simbolosList = agregarSimbolos(simbolos, produccion);

                produccion.setBody(simbolosList);

                producciones.add(produccion);

                response.setProducciones(producciones);

            }

            if (cantEstadoInicial == 0) {
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

    private List<String> agregarSimbolos(String simbolos, Produccion produccion) {

        List<String> result = produccion.getBody();

        while (!simbolos.isEmpty()) {
            char charLowerCase = String.valueOf(simbolos.charAt(0)).toLowerCase().charAt(0);

            if (simbolos.charAt(0) == charLowerCase) { //Si es terminal
                result.add(String.valueOf(simbolos.charAt(0)));
                simbolos = simbolos.substring(1);
            } else { //Si es variable

                String variable = simbolos.substring(simbolos.indexOf("X"), simbolos.indexOf("}")+1);

                simbolos = simbolos.substring(simbolos.indexOf("}")+1);

                result.add(variable);
            }
        }

        return result;
    }


}
