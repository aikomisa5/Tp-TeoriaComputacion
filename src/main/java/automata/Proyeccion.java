package automata;

public class Proyeccion {

	String estadoSalida;
	String simboloInput;
	String estadoLlegada;
	
	public Proyeccion() {
		super();
	}

	public Proyeccion(String estadoSalida, String simboloInput, String estadoLlegada) {
		super();
		this.estadoSalida = estadoSalida;
		this.simboloInput = simboloInput;
		this.estadoLlegada = estadoLlegada;
	}

	public String getEstadoSalida() {
		return estadoSalida;
	}

	public void setEstadoSalida(String estadoSalida) {
		this.estadoSalida = estadoSalida;
	}

	public String getSimboloInput() {
		return simboloInput;
	}

	public void setSimboloInput(String simboloInput) {
		this.simboloInput = simboloInput;
	}

	public String getEstadoLlegada() {
		return estadoLlegada;
	}

	public void setEstadoLlegada(String estadoLlegada) {
		this.estadoLlegada = estadoLlegada;
	}
}
