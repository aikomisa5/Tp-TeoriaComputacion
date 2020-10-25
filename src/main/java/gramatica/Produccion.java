package gramatica;

import java.util.ArrayList;

public class Produccion {

    private String simboloInput;
    private ArrayList<Character> simbolos;



    public Produccion() {
        super();
        simboloInput = "S";
        simbolos = new ArrayList<>();
    }

    public Produccion(String simboloInput, ArrayList<Character> simbolos) {
        super();
        this.simboloInput = simboloInput;
        this.simbolos = simbolos;
    }

    public String getSimboloInput() {
        return simboloInput;
    }

    public void setSimboloInput(String simboloInput) {
        this.simboloInput = simboloInput;
    }

    public ArrayList<Character> getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(ArrayList<Character> simbolos) {
        this.simbolos = simbolos;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Produccion produccion = (Produccion) obj;


        if (this.simboloInput.equals(produccion.getSimboloInput()) == false) {
            return false;
        }

        if (this.simbolos.equals(simbolos) == false) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Produccion{" +
                "simboloInput='" + simboloInput + '\'' +
                ", simbolos=" + simbolos +
                '}';
    }
}
