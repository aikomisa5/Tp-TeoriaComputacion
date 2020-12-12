package lr0.afd;

import lr0.gramatica.Produccion;

import java.util.List;

public class Nodo {

    private String nombreEstado;
    private List<Produccion> producciones;
    private List<Trancision> transiciones;
    private boolean esFinal;


    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void setProducciones(List<Produccion> producciones) {
        this.producciones = producciones;
    }

    public List<Trancision> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(List<Trancision> transiciones) {
        this.transiciones = transiciones;
    }
}
