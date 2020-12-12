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
    public void aumentarNodos() {



    }
}
