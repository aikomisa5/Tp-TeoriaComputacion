package lr0.parser;

import lr0.afd.AFD;
import lr0.gramatica.Gramatica;

public class ParserSLR {

    private Gramatica gramatica;
    private AFD afd;

    public ParserSLR() {
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
}
