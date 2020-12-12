package gramatica;


public class Par{
    private Character izquierdo;
    private Character derecho;

    public Par(Character izquierdo, Character derecho) {
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public Character getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Character izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Character getDerecho() {
        return derecho;
    }

    public void setDerecho(Character derecho) {
        this.derecho = derecho;
    }

    @Override
    public boolean equals(Object o) {
        Par par = (Par) o;
        return (izquierdo == par.izquierdo) &&
                (derecho == par.derecho);
    }

    @Override
    public int hashCode() {
        return ((this.izquierdo.toString() + this.derecho.toString())).hashCode();
    }

    @Override
    public String toString() {
        return "Par{" +
                "izquierdo=" + izquierdo +
                ", derecho=" + derecho +
                '}';
    }


}
