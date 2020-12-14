package lr0.gramatica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Gramatica {


    public static final String SIGNO_DISTINGUIDO = "X_{1}";
    public static final String SIGNO_DISTINGUIDO_PRIMA = "X_{0}";
    public static final String PIVOTE = "*";

    private List<Produccion> producciones;

    public Gramatica() {
        producciones = new ArrayList<>();
    }

    public Gramatica(List<Produccion> producciones, String ladoIzquierdo) {
        this.producciones = producciones;
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void setProducciones(List<Produccion> producciones) {
        this.producciones = producciones;
    }


    public void aumentarGramatica(int index, Produccion p) {
        this.getProducciones().add(index, p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gramatica gramatica = (Gramatica) o;
        return Objects.equals(producciones, gramatica.producciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producciones);
    }

    public HashSet<Produccion> obtenerProduccionesDeVariableConPivote(String variable) {

        HashSet<Produccion> result = new HashSet<>();

        for (int i = 0; i < producciones.size(); i++) {
            if (producciones.get(i).getVariable().equals(variable)) {
                String variableOriginal = producciones.get(i).getVariable();
                List<String> simbolosOriginal = new ArrayList<>(producciones.get(i).getBody());

                Produccion p = new Produccion();
                p.setBody(simbolosOriginal);
                p.setVariable(variableOriginal);

                if (p.getBody().get(0).charAt(0) != Gramatica.PIVOTE.charAt(0)){
                    String newPrimerSimbolo = Gramatica.PIVOTE + p.getBody().get(0);
                    p.getBody().remove(0);
                    p.getBody().add(0, newPrimerSimbolo);
                    result.add(p);
                }
            }
        }

        return result;

    }
}
