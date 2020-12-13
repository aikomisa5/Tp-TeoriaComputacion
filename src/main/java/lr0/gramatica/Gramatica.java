package lr0.gramatica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Gramatica {


    public static final  String SIGNO_DISTINGUIDO = "X_{1}";
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


    public void aumentarGramatica(int index,Produccion p){
        this.getProducciones().add(index,p);
    }


    public HashSet<Produccion> obtenerProduccionesDeVariableConPivote(String variable) {

        HashSet<Produccion> result = new HashSet<>();

        for(int i = 0 ; i < producciones.size() ; i++){
            if(producciones.get(i).getVariable().equals(variable)) {
                Produccion p = new Produccion(producciones.get(i).getVariable(),producciones.get(i).getBody());
                if (p.getBody().get(0).charAt(0) != Gramatica.PIVOTE.charAt(0)){
                    String newPrimerSimbolo = Gramatica.PIVOTE + p.getBody().get(0);
                    p.getBody().remove(0);
                    p.getBody().add(0, newPrimerSimbolo);

                    result.add(p);
                }

                /*for(int j = 0 ; j < p.getBody().size() ; j++) {

                }*/
            }
        }

        return result;

    }
}
