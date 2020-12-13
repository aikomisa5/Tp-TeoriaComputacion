package lr0.afd;

import lr0.gramatica.Gramatica;
import lr0.gramatica.Produccion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    public void clausura(Gramatica gramaticaOriginal){

        List<Produccion> newProducciones = new ArrayList<>();

        for(int i = 0; i < gramatica.getProducciones().size() ; i++){

            if(tienePivoteNoTerminal(gramatica.getProducciones().get(i).getBody())) {
                //Obtengo las variables que tienen pivote
                ArrayList<String> variablesConPivote = getVariablesPivote(gramatica.getProducciones().get(i).getBody());


                for(int j = 0 ; j < variablesConPivote.size(); j++){

                    //busco la/s produccion/es de la variable  en la gramatica original
                    HashSet<Produccion> produccionesDeVariablePivote = gramaticaOriginal
                            .obtenerProduccionesDeVariableConPivote(variablesConPivote.get(j));

                    //si no existe la gramatica encontrada en la gramatica del nodo, entonces
                    // las agrego con un pivote al comienzo

                    //produccionesDeVariablePivote.addAll(this.getGramatica().getProducciones());
                    newProducciones.addAll(produccionesDeVariablePivote);

                }
            }

        }

        Set<Produccion> prods = new HashSet<>(gramatica.getProducciones());
        int gramaticaSinAdicionSize = prods.size();
        prods.addAll(newProducciones);
        if (gramaticaSinAdicionSize != prods.size()){
            List<Produccion> newProds = new ArrayList<>(prods);
            gramatica.setProducciones(newProds);
            clausura(gramaticaOriginal);
        }
        else
        {
            System.out.println("No hubo nuevas producciones, terminamos.");
        }

    }


    private ArrayList<String> getVariablesPivote(List<String> body) {

        ArrayList<String> result = new ArrayList<>();

        for(int i = 0 ; i < body.size() ; i++){


            if(body.get(i).contains(Gramatica.PIVOTE + "X")){
                String bodyProduccionI = body.get(i);
                result.add(bodyProduccionI.substring(bodyProduccionI.indexOf("X"), bodyProduccionI.indexOf("}")+1));
            }
        }
        return result;
    }

    private boolean tienePivoteNoTerminal(List<String> body) {

        for(int i = 0 ; i < body.size() ; i++){
            if(body.get(i).indexOf(Gramatica.PIVOTE + "X") > -1)
                return true;
        }
        return false;
    }

}
