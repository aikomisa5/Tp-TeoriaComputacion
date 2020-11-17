package gramatica;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gramatica {

    private List<Produccion> producciones;
    private String estadoInicial = "S";

    private static final String terminalPatternString = "[a-z]";

    public Gramatica() {
        super();
        producciones = new ArrayList<Produccion>();
    }

    public Gramatica(List<Produccion> producciones) {
        super();
        this.producciones = producciones;
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void setProducciones(List<Produccion> producciones) {
        this.producciones = producciones;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    @Override
    public String toString() {
        return "Gramatica{" +
                "producciones=" + producciones +
                ", estadoInicial='" + estadoInicial + '\'' +
                '}';
    }

    /*
    * Función que actualiza las producciones eliminando del lado derecho todas las E.
    * En caso de que sea solo una producción con una E, elimina la producción.
    * Crece exponencialmente agregando nuevas producciones.
     */

    public void eliminarE() {

        List<Produccion> newProducciones = new ArrayList<>();

        List<Character> N = new ArrayList<>();

        //1º Agrego los simbolos que derivan directamente de vacio
        for(Produccion p : getProducciones()) {
            boolean isNullable = false;

            for (Character c : p.getSimbolos()) {
                if (c.equals('E')) {
                    isNullable = true;
                }
            }
            if (isNullable)
                N.add(p.getSimboloInput().charAt(0));
        }
        //2º Verifico si existe alguna produccion que esté completamente en N
        for(Produccion p : getProducciones()) {
            boolean isNullable = true;

            for (Character c : p.getSimbolos()) {
                if (!N.contains(c)) {
                    isNullable = false;
                }
            }
            if (isNullable)
                N.add(p.getSimboloInput().charAt(0));
        }

        //3º Genero nueva gramática, analizando la anterior y agregando producciones nuevas para los casos nullables.
        for(Produccion p : getProducciones()) {

            ArrayList<String> nullables = filtroNullables(p.getSimbolos(),N);

            ArrayList<String> combinacionesNullables = obtenerCombinacionesNullables(nullables);

            //Genero nuevas producciones con las combinaciones de nullables
            for (String combinacion : combinacionesNullables) {

                ArrayList<Character> newSimbolos = new ArrayList<>();
                String terminales = "";
                for (Character c : p.getSimbolos()) {
                    if (!Character.isUpperCase(c)){
                        newSimbolos.add(c); //Es terminal, lo agrego.
                        terminales = terminales + c.toString();
                    }

                    if (combinacion.contains(c.toString()))
                        newSimbolos.add(c);
                }
                //Caso particular para dejar sólo los terminales
                if(!terminales.isEmpty()) {
                    ArrayList<Character> terminalesSimbolos = getCharacters(terminales.toCharArray());
                    Produccion newP = new Produccion(p.getSimboloInput(), terminalesSimbolos);
                    newProducciones.add(newP);
                }
                //Caso particular para produccion vacía
                if(!newSimbolos.isEmpty()) {
                    Produccion newP = new Produccion(p.getSimboloInput(), newSimbolos);
                    newProducciones.add(newP);
                }
            }
        }

        setProducciones(newProducciones);
    }

    private ArrayList<Character> getCharacters(char[] toCharArray) {
        ArrayList<Character> resultList = new ArrayList<>();

        for (int i = 0 ; i < toCharArray.length; i++){
            resultList.add(Character.valueOf(toCharArray[i]));

        }

        return resultList;
    }

    private ArrayList<String> obtenerCombinacionesNullables(ArrayList<String> nullables) {
        ArrayList<String> result = new ArrayList<>();
        int n = nullables.size();
        int total = (int) Math.pow(2d, Double.valueOf(n));
        for (int i = 1; i < total; i++) {
            String code = Integer.toBinaryString(total | i).substring(1);
            String simbolos = "";
            for (int j = 0; j < n; j++) {
                if (code.charAt(j) == '1') {
                    simbolos = simbolos + nullables.get(j);
                }
            }
            result.add(simbolos);
        }
        return result;
    }


    private ArrayList<String> filtroNullables(ArrayList<Character> simbolos, List<Character> n) {

        ArrayList<String> result = new ArrayList<>();

        for (Character c : simbolos)
            if (n.contains(c))
                result.add(c.toString());

        return  result;
    }


    public void eliminarProduccionesUnitarias() {
        //TODO:
    }

    public void eliminarSimbolosNoGeneradores() {
        StringBuilder toPattern = new StringBuilder("");
        for(Produccion produccion : getProducciones()){
            // identifico los simbolos terminales en mi caso base.
            boolean derivaTodosTerminales = true;
            for (Character simbolo : produccion.getSimbolos()){
                boolean esTerminal = esSimboloTerminal(simbolo);
                derivaTodosTerminales = derivaTodosTerminales && esTerminal;
                if (esTerminal)
                    toPattern.append(simbolo);
            }
            if (derivaTodosTerminales)
                // hago esto porque por algun motivo el simbolo input no es un char.
                toPattern.append(produccion.getSimboloInput().charAt(0));
        }
        Pattern pattern1 = Pattern.compile("["+toPattern.toString()+"]+");

        for (int i = 0; i < toPattern.toString().length(); i++) {
            for(Produccion produccion : getProducciones()) {
                String produccionEnString = "";
                for (Character simboloProduccion :produccion.getSimbolos())
                    produccionEnString = produccionEnString.concat("" + simboloProduccion);
                Matcher matcher = pattern1.matcher(produccionEnString);
                if (matcher.matches()){
                    if (!toPattern.toString().contains(produccion.getSimboloInput()))
                        toPattern.append(produccion.getSimboloInput().charAt(0));
                }
            }
        }
        List<Produccion> produccionesDeSimbolosGeneradores = new ArrayList<>();
        boolean estadoInicialEstaContenido = false;
        for(Produccion produccion : getProducciones()){
            boolean hayUnSimboloInutil = false;
            for (Character simbolo : produccion.getSimbolos())
                hayUnSimboloInutil = hayUnSimboloInutil || !toPattern.toString().contains(""+simbolo);
            if (toPattern.toString().contains(produccion.getSimboloInput()) && !hayUnSimboloInutil) {
                if (produccion.getSimboloInput().equals(estadoInicial))
                    estadoInicialEstaContenido = true;
                produccionesDeSimbolosGeneradores.add(produccion);
            }

        }
        //Si no hubiese estado inicial, significa que esta gramatica no forma ningun string
        setProducciones(estadoInicialEstaContenido ? produccionesDeSimbolosGeneradores : new ArrayList<>());
    }

    private boolean esSimboloTerminal(Character simbolo)
    {
        Pattern pattern = Pattern.compile(terminalPatternString);
        Matcher matcher = pattern.matcher(""+simbolo);
        return matcher.matches();
    }

    public void eliminarSimbolosNoAlcanzables() {
        //TODO:
    }
}
