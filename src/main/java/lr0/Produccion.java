package lr0;

import java.util.ArrayList;
import java.util.List;

public class Produccion {

    private String variable;
    private List<Simbolo> body;

    public Produccion() {
        this.body = new ArrayList<>();
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public List<Simbolo> getBody() {
        return body;
    }

    public void setBody(List<Simbolo> body) {
        this.body = body;
    }
}
