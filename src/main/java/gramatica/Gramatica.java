package gramatica;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gramatica {

    private List<Produccion> producciones;
    private String estadoInicial = "S";

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
        //TODO:
    }

    public void eliminarSimbolosNoAlcanzables() {
        //TODO:
    }
}
