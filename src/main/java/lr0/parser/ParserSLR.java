package lr0.parser;

import lr0.afd.AFD;
import lr0.afd.Nodo;
import lr0.gramatica.Gramatica;
import lr0.gramatica.Produccion;

import java.util.ArrayList;

public class ParserSLR {

    private Gramatica gramatica;
    private AFD afd;

    public ParserSLR(Gramatica gramatica) {
        this.gramatica = gramatica;
    }

    public ParserSLR(Gramatica gramatica, AFD afd) {
        this.gramatica = gramatica;
        this.afd = afd;
    }

    public Gramatica getGramatica() {
        return gramatica;
    }

    public void setGramatica(Gramatica gramatica) {
        this.gramatica = gramatica;
    }

    public AFD getAfd() {
        return afd;
    }

    public void setAfd(AFD afd) {
        this.afd = afd;
    }

    public AFD construirAFD(){
        AFD afd = new AFD();

        //Aumento la gramatica
        Gramatica gramaticaAFD = this.gramatica;

        ArrayList<String> simbolos = new ArrayList<>();
        simbolos.add(Gramatica.PIVOTE + Gramatica.SIGNO_DISTINGUIDO);

        //gramaticaAFD.aumentarGramatica(0,new Produccion(Gramatica.SIGNO_DISTINGUIDO_PRIMA,simbolos));

        //Construccion realizando la clausura y generando nodos para c/estado.

        ArrayList<Produccion> produccionesN0 = new ArrayList<>();

        Produccion p = new Produccion();
        p.setVariable(Gramatica.SIGNO_DISTINGUIDO_PRIMA);
        p.setBody(simbolos);
        produccionesN0.add(p);


        Gramatica gramaticaAFD_I0 = new Gramatica();
        gramaticaAFD_I0.setProducciones(produccionesN0);

        //Agrego nodo estado inicial
        Nodo n0 = new Nodo("0",gramaticaAFD_I0);

        n0.clausura(gramatica);

        afd.getNodos().add(n0);


        afd.aumentarNodos(n0);






        return  afd;
    }

}
