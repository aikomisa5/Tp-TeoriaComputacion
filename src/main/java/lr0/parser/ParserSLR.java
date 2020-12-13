package lr0.parser;

import lr0.afd.AFD;
import lr0.afd.Nodo;
import lr0.afd.Transicion;
import lr0.gramatica.Gramatica;
import lr0.gramatica.Produccion;

import java.util.*;

public class ParserSLR {

    private Gramatica gramatica;
    private AFD afd;
    private int contador;

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

    public void generarParserLR0()
    {
        AFD afd = construirAFD();


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
        Set<String> setDeSimbolosConPivote = getSimbolosConPivote(n0);

        contador = (Integer.parseInt(n0.getNombreEstado()));

        crearNodosYTransiciones(afd, setDeSimbolosConPivote, n0);


        for (int i = 1; i < afd.getNodos().size(); i++){
            Nodo nodoAlQueVamosAHacerleGoTo = afd.getNodos().get(i);
            System.out.println("Hagamosle goto al nodo " + nodoAlQueVamosAHacerleGoTo);
            goTo(nodoAlQueVamosAHacerleGoTo);
            if (algunSimboloSinClausurar(nodoAlQueVamosAHacerleGoTo)){

                setDeSimbolosConPivote = getSimbolosConPivote(nodoAlQueVamosAHacerleGoTo);

                crearNodosYTransiciones(afd, setDeSimbolosConPivote, nodoAlQueVamosAHacerleGoTo);
            }


        }
        System.out.println("AFD: [");
        for (Nodo nodo: afd.getNodos()) {
            System.out.println("Nodo: I"+ nodo.getNombreEstado() + " tiene las siguientes producciones:");
            for (Produccion produccion: nodo.getGramatica().getProducciones()) {
                System.out.println(produccion);
            }
            System.out.println("Con las siguientes transiciones:");
            if (nodo.getTransiciones().size() == 0)
                System.out.println("No tiene transiciones.");
            else
                for (Transicion transicion : nodo.getTransiciones())
                    System.out.println("Simbolo de la transición : " + transicion.getSimbolo() + ", nodo destino: I"+ transicion.getNodoDestino().getNombreEstado());
            System.out.println();
        }
        System.out.println("]");
        return  afd;
    }

    private void crearNodosYTransiciones(AFD afd, Set<String> setDeSimbolosConPivote, Nodo nodoAlQueVamosAHacerleGoTo) {
        for (String simboloParaCrearNodo: setDeSimbolosConPivote) {

            System.out.println(simboloParaCrearNodo);
            Nodo nuevoNodo = new Nodo();
            nuevoNodo.setNombreEstado("" + ++contador);
            Gramatica gramaticaDelNodo = new Gramatica();

            for (Produccion produccion : nodoAlQueVamosAHacerleGoTo.getGramatica().getProducciones()){
                int indexDelPivoteEnBody = indexDelPivoteEnBody(produccion);
                // Tiene hardcodeado el 0 porque estamos haciendolo para el nodo inicial.
                if (produccion.getBody().get(indexDelPivoteEnBody).equals(simboloParaCrearNodo)){
                    Produccion toAdd = new Produccion();
                    toAdd.setVariable(produccion.getVariable());
                    List<String> toAddBody = new ArrayList<>(produccion.getBody());
                    toAdd.setBody(toAddBody);
                    gramaticaDelNodo.getProducciones().add(toAdd);
                }
            }
            nuevoNodo.setGramatica(gramaticaDelNodo);
            Transicion transicionANodo = new Transicion();
            // Tiene hardcodeado el 1 porque estamos haciendolo para el nodo inicial.
            // Estamos sacando el pivote de la primera posición
            transicionANodo.setSimbolo(simboloParaCrearNodo.substring(1));
            transicionANodo.setNodoDestino(nuevoNodo);
            nodoAlQueVamosAHacerleGoTo.getTransiciones().add(transicionANodo);
            afd.getNodos().add(nuevoNodo);
        }
    }

    private Set<String> getSimbolosConPivote(Nodo nodo) {
        Set<String> setDeSimbolosConPivote = new HashSet<>();
        for (Produccion produccion : nodo.getGramatica().getProducciones()) {
            String simbolo = produccion.getBody().get(indexDelPivoteEnBody(produccion));
            if (!simbolo.equals(Gramatica.PIVOTE))
                setDeSimbolosConPivote.add(simbolo);
        }
        return setDeSimbolosConPivote;
    }

    private boolean algunSimboloSinClausurar(Nodo nodoAlQueVamosAHacerleGoTo) {
        boolean ret = false;
        for (Produccion produccion: nodoAlQueVamosAHacerleGoTo.getGramatica().getProducciones())
            ret = ret || !produccion.getBody().get(produccion.getBody().size()-1).equals(Gramatica.PIVOTE);
        return ret;
    }

    private void goTo(Nodo nodo){
        for (Produccion produccion: nodo.getGramatica().getProducciones()) {
            moverPivote(produccion);
        }
        nodo.clausura(gramatica);
    }

    private void moverPivote(Produccion produccion){
        int indexDelPivote = indexDelPivoteEnBody(produccion);
        List<String> body = produccion.getBody();
        //quito el pivote de donde estaba.


        if (indexDelPivote < body.size()-1){
            String stringAlQueLeQuitoElPivote = body.get(indexDelPivote).replace(Gramatica.PIVOTE,"");
            body.remove(indexDelPivote);
            body.add(indexDelPivote,stringAlQueLeQuitoElPivote);
            String stringAlQueLeAgregoElPivote = body.get(indexDelPivote+1).replace(body.get(indexDelPivote+1),Gramatica.PIVOTE + body.get(indexDelPivote+1));
            body.remove(indexDelPivote+1);
            body.add(indexDelPivote+1,stringAlQueLeAgregoElPivote);

        }
        else{
            if (!body.get(body.size()-1).equals(Gramatica.PIVOTE)){
                String stringAlQueLeQuitoElPivote = body.get(indexDelPivote).replace(Gramatica.PIVOTE,"");
                body.remove(indexDelPivote);
                body.add(indexDelPivote,stringAlQueLeQuitoElPivote);
                body.add(Gramatica.PIVOTE);
            }
        }

    }

    private int indexDelPivoteEnBody(Produccion produccion){
        for (int i = 0; i < produccion.getBody().size(); i++) {
            String variableDelBody = produccion.getBody().get(i);
            if (variableDelBody.contains(Gramatica.PIVOTE))
                return i;
        }
        return -1;
    }
}
