package lr0.afd;

import lr0.gramatica.Gramatica;

import java.util.ArrayList;
import java.util.List;

public class Nodo {

    private String nombreEstado;
    private Gramatica gramatica;
    private List<Trancision> transiciones;
    private boolean esFinal;

    public Nodo(String nombreEstado, Gramatica gramatica) {
        this.nombreEstado = nombreEstado;
        this.gramatica = gramatica;
        this.transiciones = new ArrayList<>();
        this.esFinal = false;
    }

    public Nodo(String nombreEstado, Gramatica gramatica, List<Trancision> transiciones, boolean esFinal) {
        this.nombreEstado = nombreEstado;
        this.gramatica = gramatica;
        this.transiciones = transiciones;
        this.esFinal = esFinal;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public Gramatica getGramatica() {
        return gramatica;
    }

    public void setGramatica(Gramatica gramatica) {
        this.gramatica = gramatica;
    }

    public List<Trancision> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(List<Trancision> transiciones) {
        this.transiciones = transiciones;
    }

    public boolean isEsFinal() {
        return esFinal;
    }

    public void setEsFinal(boolean esFinal) {
        this.esFinal = esFinal;
    }
}
