package lr0.parser;

import lr0.afd.AFD;
import lr0.afd.Nodo;
import lr0.afd.Transicion;
import lr0.gramatica.Gramatica;
import lr0.gramatica.Produccion;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserLR0 {

    private Gramatica gramatica;
    private AFD afd;
    private int contador;

    public ParserLR0(Gramatica gramatica) {
        this.gramatica = gramatica;
    }

    public ParserLR0(Gramatica gramatica, AFD afd) {
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

    public void generarParserLR0()  {
        this.afd = construirAFD();
        Set<String> simbolosTerminales = new HashSet<>();



        for (Produccion produccion : gramatica.getProducciones()) {
            for (String string : produccion.getBody()) {
                if (esSimboolNoTerminal(string)) {
                    simbolosTerminales.add(string);
                }
            }
        }
        simbolosTerminales.add("$");

        String[][] tablaGoTo = new String[afd.getNodos().size()][2]; //
        String[][] tablaAction = new String[afd.getNodos().size() + 1][simbolosTerminales.size() + 1];


        armarTablaGoto(afd, tablaGoTo);

        System.out.println("Numeracion de las produciones para los reduce.");
        for (int j = 0; j < gramatica.getProducciones().size(); j++) {
            System.out.println("("+j+"): " + gramatica.getProducciones().get(j));
        }

        tablaAction[0][0] = "Estado";
        int i = 1;
        for (String string : simbolosTerminales) {
            tablaAction[0][i++] = string;
        }
        i = 1;
        for (Nodo nodo : afd.getNodos()) {
            tablaAction[i++][0] = nodo.getNombreEstado();
        }
        armarTablaAction(tablaAction);

        leerTabla(tablaGoTo);
        leerTabla(tablaAction);

    }

    private void armarTablaAction(String[][] tablaAction) {
        for (Nodo nodo : afd.getNodos()) {
            for (Produccion produccion : nodo.getGramatica().getProducciones()) {
                // si no está en el final
                int indexDelPivote = indexDelPivoteEnBody(produccion);
                // no es el ultimo simbolo.
                if (!produccion.getBody().get(produccion.getBody().size()-1).equals(Gramatica.PIVOTE)) {
                    asignarShift(tablaAction, nodo, produccion, indexDelPivote);
                }
                if (produccion.getBody().get(produccion.getBody().size()-1).equals(Gramatica.PIVOTE) &&
                    !produccion.getVariable().equals(Gramatica.SIGNO_DISTINGUIDO_PRIMA)){
                    asignarReduce(tablaAction, nodo, produccion);
                }
                //hay que aceptar.
                if(produccion.getVariable().equals(Gramatica.SIGNO_DISTINGUIDO_PRIMA) && produccion.getBody().size() >1){
                    asignarAccept(tablaAction, nodo, produccion);
                }
            }

        }
    }

    private void asignarShift(String[][] tablaAction, Nodo nodo, Produccion produccion, int indexDelPivote) {
        boolean hayTransicionAlProximoSimbolo = false;
        String simboloTransicionDeTransicion;
        simboloTransicionDeTransicion = produccion.getBody().get(indexDelPivote).substring(1);
        if (esSimboolNoTerminal(simboloTransicionDeTransicion))
        {
            String nodoDestinoNombre = null;
            for (Transicion transicion : nodo.getTransiciones()) {
                hayTransicionAlProximoSimbolo = hayTransicionAlProximoSimbolo || transicion.getSimbolo().equals(simboloTransicionDeTransicion);
                if (hayTransicionAlProximoSimbolo) {
                    nodoDestinoNombre = transicion.getNodoDestino().getNombreEstado();
                    break;
                }
            }
            if (hayTransicionAlProximoSimbolo) {
                if (tablaAction[Integer.parseInt(nodo.getNombreEstado()) + 1]
                        [indexDelSimbolo(simboloTransicionDeTransicion, tablaAction)] == null)
                    tablaAction[Integer.parseInt(nodo.getNombreEstado()) + 1]
                            [indexDelSimbolo(simboloTransicionDeTransicion, tablaAction)] = "s" + nodoDestinoNombre;
                else
                    throw new IllegalArgumentException("Ya habia una action, hay conflicto. la Gramática no es LR(0).");
            }
        }
    }

    private void asignarReduce(String[][] tablaAction, Nodo nodo, Produccion produccion) {
        // queremos el simbolo que viene antes del pivote.
        List<String> bodyABuscar = new ArrayList<>(produccion.getBody());
        bodyABuscar.remove(bodyABuscar.size()-1); //quito el pivote.
        int indexDeLaProduccionQueReduce = -1;
        /*
         *  Esto funciona porque es una lista, y van a estar ordenados durante ejecuccion a menos que los toquemos.
         */
        for (int j = 0; j < gramatica.getProducciones().size(); j++) {
            Produccion produccionAReducir = gramatica.getProducciones().get(j);
            if (produccionAReducir.getBody().equals(bodyABuscar)){
                indexDeLaProduccionQueReduce = j;
                break;
            }
        }
        for (int k = 1; k <  tablaAction[Integer.parseInt(nodo.getNombreEstado()) + 1].length; k++) {
            if (tablaAction[Integer.parseInt(nodo.getNombreEstado()) + 1][k] == null)
                tablaAction[Integer.parseInt(nodo.getNombreEstado()) + 1][k] = "r"+indexDeLaProduccionQueReduce;
            else
                throw new IllegalArgumentException("Ya habia una action, hay conflicto. la Gramática no es LR(0).\n la action era" + tablaAction[Integer.parseInt(nodo.getNombreEstado()) + 1][k] );
        }
    }

    private void asignarAccept(String[][] tablaAction, Nodo nodo, Produccion produccion) {
        if (produccion.getBody().get(produccion.getBody().size()-2).equals(Gramatica.SIGNO_DISTINGUIDO) &&
                produccion.getBody().get(produccion.getBody().size()-1).equals(Gramatica.PIVOTE))
            if (tablaAction[Integer.parseInt(nodo.getNombreEstado()) + 1][tablaAction[0].length-1] == null)
                tablaAction[Integer.parseInt(nodo.getNombreEstado()) + 1][tablaAction[0].length-1] = "ACCEPT";
            else
                throw new IllegalArgumentException("Ya habia una action, hay conflicto. la Gramática no es LR(0).\n la action era " + tablaAction[Integer.parseInt(nodo.getNombreEstado()) + 1][tablaAction[0].length-1] );
    }

    private boolean esSimboolNoTerminal(String str){
        Pattern patternNoTerminal = Pattern.compile("[a-z]+");
        Matcher matcher = patternNoTerminal.matcher(str);
        return matcher.matches();
    }

    private int indexDelSimbolo(String simboloTransicionDeTransicion, String[][] tablaAction) {
        int index = -1;
        for (int i = 0; i < tablaAction[0].length; i++) {
            if (tablaAction[0][i].equals(simboloTransicionDeTransicion))
                return i;
        }
        return index;
    }

    private void armarTablaGoto(AFD afd,String[][] tablaGoTo) {
        Pattern patternSimbolo = Pattern.compile("X_\\{[0-9]+\\}");
        for (int i = 0; i < afd.getNodos().size(); i++) {
            Nodo nodo = afd.getNodos().get(i);
            tablaGoTo[i][0] = nodo.getNombreEstado();
            tablaGoTo[i][1] = "|--|";
                if (nodo.getTransiciones().size() !=0){
                    for (Transicion transicion:nodo.getTransiciones()) {
                        Matcher matcher = patternSimbolo.matcher(transicion.getSimbolo());
                        if (matcher.matches())
                            tablaGoTo[i][1] = tablaGoTo[i][1].concat(transicion.getNodoDestino().getNombreEstado() + " ");
                    }
                }

        }
    }

    private void leerTabla(String[][] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            String str = tabla[i][0] +"\t|\t";
            for (int j = 1; j < tabla[i].length; j++) {
                str = str.concat(tabla[i][j] == null? "|--|" : "|"+tabla[i][j] + "|\t");
            }
            System.out.println("[\t" + str + "]");
        }
    }

    public AFD construirAFD(){
        AFD afd = new AFD();

        //Aumento la gramatica
        Gramatica gramaticaAFD = this.gramatica;

        ArrayList<String> simbolos = new ArrayList<>();
        simbolos.add(Gramatica.PIVOTE + Gramatica.SIGNO_DISTINGUIDO);

        ArrayList<Produccion> produccionesN0 = new ArrayList<>();

        Produccion p = new Produccion();
        p.setVariable(Gramatica.SIGNO_DISTINGUIDO_PRIMA);
        p.setBody(simbolos);
        produccionesN0.add(p);

        Gramatica gramaticaAFD_I0 = new Gramatica();
        gramaticaAFD_I0.setProducciones(produccionesN0);

        //Agrego nodo estado inicial
        creacionClausuraYAgregadoalAFDPrimerNodo(afd, gramaticaAFD_I0);

        Set<String> setDeSimbolosConPivote;
        int i = 1;
        while( i < afd.getNodos().size()){
            Nodo nodoAlQueVamosAHacerleGoTo = afd.getNodos().get(i);
            goTo(nodoAlQueVamosAHacerleGoTo);
            if (hayDuplicados(afd,nodoAlQueVamosAHacerleGoTo)){
                afd.getNodos().remove(nodoAlQueVamosAHacerleGoTo);
                Nodo nodoQueSigueEnLaLista = getNodoPorGramaticaIdentica(afd,nodoAlQueVamosAHacerleGoTo.getGramatica());
                //hacemos que el nodo se apunte a si mismo, porque si aparecio este nodo es porque era necesario "expandir" de nuevo.
                for (Transicion transicion:nodoQueSigueEnLaLista.getTransiciones()) {
                    if (transicion.getNodoDestino().equals(nodoAlQueVamosAHacerleGoTo))
                        transicion.setNodoDestino(nodoQueSigueEnLaLista);
                }
                //redireccionamos las transiciones al nodo que ya estaba en la lista y tiene la misms "gramatica"
                for (Nodo nodo:afd.getNodos()) {
                    for (int j = nodo.getTransiciones().size()-1; j >=0 ; j--) {
                        Transicion transicion = nodo.getTransiciones().get(j);
                        if (transicion.getNodoDestino().equals(nodoAlQueVamosAHacerleGoTo))
                           transicion.setNodoDestino(nodoQueSigueEnLaLista);
                    }
                }
            }
            else{
                if (algunSimboloSinClausurar(nodoAlQueVamosAHacerleGoTo)){
                    setDeSimbolosConPivote = getSimbolosConPivote(nodoAlQueVamosAHacerleGoTo);
                    crearNodosYTransiciones(afd, setDeSimbolosConPivote, nodoAlQueVamosAHacerleGoTo);
                }
                else
                {
                    boolean esFinal = true;
                    for (Produccion produccion: nodoAlQueVamosAHacerleGoTo.getGramatica().getProducciones()) {
                        esFinal = esFinal && produccion.getVariable().equals(Gramatica.SIGNO_DISTINGUIDO_PRIMA);
                    }
                    if (esFinal)
                        nodoAlQueVamosAHacerleGoTo.setEsFinal(true);
                }
                i++;
            }
        }

        contador = 0;
        for (Nodo nodo: afd.getNodos()) {
            if (Integer.parseInt(nodo.getNombreEstado()) != contador ){
                nodo.setNombreEstado(""+contador);
            }
            contador++;
        }

        imprimirAFD(afd);

        return  afd;
    }

    private void creacionClausuraYAgregadoalAFDPrimerNodo(AFD afd, Gramatica gramaticaAFD_I0) {
        Nodo n0 = new Nodo("0", gramaticaAFD_I0);

        n0.clausura(gramatica);

        afd.getNodos().add(n0);
        Set<String> setDeSimbolosConPivote = getSimbolosConPivote(n0);

        contador = (Integer.parseInt(n0.getNombreEstado()));

        crearNodosYTransiciones(afd, setDeSimbolosConPivote, n0);
    }

    private void imprimirAFD(AFD afd) {
        System.out.println("AFD: [");
        for (Nodo nodo: afd.getNodos()) {
            String str = "Nodo: I"+ nodo.getNombreEstado() + ".";
            str = str.concat(nodo.isEsFinal() ? " Es nodo final." : " No es nodo final.");
            str =  str.concat(" Tiene las siguientes producciones:");
            System.out.println(str);
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
    }

    private Nodo getNodoPorGramaticaIdentica(AFD afd, Gramatica gramatica) {
        for (Nodo nodo: afd.getNodos()) {
            if (nodo.getGramatica().equals(gramatica))
                return nodo;
        }
        return null;
    }

    private void crearNodosYTransiciones(AFD afd, Set<String> setDeSimbolosConPivote, Nodo nodoAlQueVamosAHacerleGoTo) {
        for (String simboloParaCrearNodo: setDeSimbolosConPivote) {
            Nodo nuevoNodo = new Nodo();
            nuevoNodo.setNombreEstado("" + ++contador);

            Gramatica gramaticaDelNodo = new Gramatica();

            for (Produccion produccion : nodoAlQueVamosAHacerleGoTo.getGramatica().getProducciones()){
                int indexDelPivoteEnBody = indexDelPivoteEnBody(produccion);
                if (produccion.getBody().get(indexDelPivoteEnBody).equals(simboloParaCrearNodo)){
                    Produccion produccionToAdd = new Produccion();
                    produccionToAdd.setVariable(produccion.getVariable());
                    List<String> toAddBody = new ArrayList<>(produccion.getBody());
                    produccionToAdd.setBody(toAddBody);
                    toAddBody = new ArrayList<>(produccion.getBody());
                    gramaticaDelNodo.getProducciones().add(produccionToAdd);
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

    private boolean hayDuplicados(AFD afd, Nodo nodoARevisar){
        int count = 0;
        for (Nodo nodo: afd.getNodos()) {
            if (nodo.getGramatica().equals(nodoARevisar.getGramatica()))
                count++;
        }
        return count >1;
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
