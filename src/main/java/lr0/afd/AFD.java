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
}
