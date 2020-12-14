package gramatica;


import java.util.ArrayList;
import java.util.List;

public class Produccion implements Comparable<Produccion>{

    private String simboloInput;
    private List<Character> simbolos;

    public Produccion() {
        super();
        simboloInput = "S";
        simbolos = new ArrayList<>();
    }

    public Produccion(String simboloInput, List<Character> simbolos) {
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

    public List<Character> getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(List<Character> simbolos) {
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

        if (!this.simboloInput.equals(produccion.getSimboloInput())) {
            return false;
        }

        if (!this.simbolos.equals(produccion.simbolos))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return ((this.simboloInput + this.simbolos.toString())).hashCode();
    }


	@Override
	public String toString() {
		return "Produccion [simboloInput=" + simboloInput + ", simbolos=" + simbolos + "]";
	}

    @Override
    public int compareTo(Produccion o) {

        int result2 = this.simboloInput.compareTo(o.simboloInput);

        if (result2 != 0) {
            return result2;
        }

        return this.getSimbolos().toString().compareTo(o.getSimbolos().toString());
    }
}