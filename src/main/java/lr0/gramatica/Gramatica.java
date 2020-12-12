package lr0.gramatica;

import java.util.List;

public class Gramatica {


    public static final  String SIGNO_DISTINGUIDO = "X_{1}";
    public static final String SIGNO_DISTINGUIDO_PRIMA = "X_{0}";
    public static final String PIVOTE = "*";

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


    public void aumentarGramatica(int index,Produccion p){
        this.getProducciones().add(index,p);
    }



}
