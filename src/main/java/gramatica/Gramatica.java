package gramatica;

import java.util.List;

public class Gramatica {

	private List<Produccion> producciones;
	
	public Gramatica() {
		super();
	}
	
	public Gramatica(List<Produccion> producciones) {
		this.producciones = producciones;
	}

	public List<Produccion> getProducciones() {
		return producciones;
	}

	public void setProducciones(List<Produccion> producciones) {
		this.producciones = producciones;
	}
}
