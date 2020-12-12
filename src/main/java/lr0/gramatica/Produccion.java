package lr0.gramatica;

import java.util.ArrayList;
import java.util.List;

public class Produccion {

    private String variable;
    private List<String> body;

    public Produccion() {
        this.body = new ArrayList<>();
    }

    public Produccion(String variable, List<String> body) {
        this.variable = variable;
        this.body = body;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }
}
