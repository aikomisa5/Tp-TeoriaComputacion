package lr0.afd;

import lr0.gramatica.Gramatica;
import lr0.gramatica.Produccion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AFD {

    private List<Nodo> nodos;

    public AFD() {
        this.nodos = new ArrayList<>();
    }

    public AFD(List<Nodo> nodos) {
        this.nodos = nodos;
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(List<Nodo> nodos) {
        this.nodos = nodos;
    }

    // Crea nuevos nodos a partir de los pivotes
    public void aumentarNodos(Nodo nodoOrigen) {


        //obtengo todos los simbolos direccionados por pivotes y elimino repetidos

        Set<String> setDeSimbolosConPivote = new HashSet<>();
        int contador = (Integer.parseInt(nodoOrigen.getNombreEstado()));

        for (Produccion produccion : nodoOrigen.getGramatica().getProducciones()) {
            setDeSimbolosConPivote.add(produccion.getBody().get(0));

            if(setDeSimbolosConPivote.contains(produccion.getBody().get(0))){
                String newNombre =  "" + (contador++);

               // nodoOrigen.getTransiciones().add();
               // Nodo n = new Nodo();


                Transicion newTransicion = new Transicion();
            }


        }


        //para los que quedan armo nuevo nodo y realizo clausura




        //para cada nodo creado anteriormente crear trancision y unir al nodo anterior "nodoOrigen"

        // this.aumentarNodos(paraCadaNodoAnteriorCreado)    aumentar nodos nuevamente para cada nodo nuevo anterior

        //hasta que pivote este en terminal o al final de todos los simbolos.


    }


    private ArrayList<String> getVariablesPivote(List<String> body) {

        ArrayList<String> result = new ArrayList<>();
        for(int i = 0 ; i < body.size() ; i++){

            if(body.get(i).contains(Gramatica.PIVOTE + "X")){
                String bodyProduccionI = body.get(i);
                result.add(bodyProduccionI.substring(bodyProduccionI.indexOf("X"), bodyProduccionI.indexOf("}")+1));
            }else{
                String bodyProduccionI = body.get(i);
                result.add(bodyProduccionI);
            }
        }
        return result;
    }




}
