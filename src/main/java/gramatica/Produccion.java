package gramatica;

public class Produccion {

	private String simboloInicial;
	private String simbolosLlegada;
	
	public Produccion() {
		super();
	}
	
	public Produccion(String simboloInicial, String simbolosLlegada) {
		this.simboloInicial = simboloInicial;
		this.simbolosLlegada = simbolosLlegada;
	}
	
	public String getSimboloInicial() {
		return simboloInicial;
	}

	public void setSimboloInicial(String simboloInicial) {
		this.simboloInicial = simboloInicial;
	}

	public String getSimbolosLlegada() {
		return simbolosLlegada;
	}

	public void setSimbolosLlegada(String simbolosLlegada) {
		this.simbolosLlegada = simbolosLlegada;
	}
}