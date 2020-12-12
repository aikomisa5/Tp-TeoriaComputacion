package lr0;

import java.util.ArrayList;
import java.util.List;

public class Produccion {

    private String variable;
    private List<String> body;

    public Produccion() {
        this.body = new ArrayList<>();
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
