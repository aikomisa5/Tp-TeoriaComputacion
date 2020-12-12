package lr0.gramatica;

import java.util.ArrayList;
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

    public void agregarPivoteATodo(){
        List<Produccion> newProducciones = new ArrayList<>();

        for(int i = 0; i < producciones.size() ; i++){
            Produccion newProduccion = new Produccion();
            newProduccion.setVariable(producciones.get(i).getVariable());

            List<String> simbolosProduccionI = producciones.get(i).getBody();
            List<String> newSimbolos = new ArrayList<>();

            for(int j = 0 ; j < simbolosProduccionI.size() ; j++){
                String newSimboloJ = PIVOTE + simbolosProduccionI.get(j);
                newSimbolos.add(newSimboloJ);
            }

            newProduccion.setBody(newSimbolos);

            newProducciones.add(newProduccion);

        }

        this.producciones = newProducciones;
    }



}
