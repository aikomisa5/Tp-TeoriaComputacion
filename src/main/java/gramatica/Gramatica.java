package gramatica;

import java.util.ArrayList;
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
     */

    public void eliminarE() {

        List<Produccion> newProducciones = new ArrayList<>();

        for(Produccion p : getProducciones()) {

            Produccion pCopy = new Produccion(p.getSimboloInput(), new ArrayList<>());

            for (Character c : p.getSimbolos()) {

                if (!c.equals('E')) {
                    pCopy.getSimbolos().add(c);
                }

            }
            if (!pCopy.getSimbolos().isEmpty())
                newProducciones.add(pCopy);

        }
        setProducciones(newProducciones);
    }
}
