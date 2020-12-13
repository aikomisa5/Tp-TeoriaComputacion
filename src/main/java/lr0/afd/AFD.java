package lr0.afd;

import java.util.ArrayList;
import java.util.List;

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


        //obtengo todos los simbolos direccionados por pivotes


        // elimino repetidos

        //para los que quedan armo nuevo nodo y realizo clausura

        //para cada nodo creado anteriormente crear trancision y unir al nodo anterior "nodoOrigen"

        // this.aumentarNodos(paraCadaNodoAnteriorCreado)    aumentar nodos nuevamente para cada nodo nuevo anterior

        //hasta que pivote este en terminal o al final de todos los simbolos.


    }
}
