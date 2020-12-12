package lr0;

import java.util.List;

public class Gramatica {


    public final static String SIGNO_DISTINGUIDO = "X_{1}";
    public static final String SIGNO_DISTINGUIDO_PRIMA = "X_{0}";

    private List<Produccion> producciones;

    public Gramatica() {
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
}
