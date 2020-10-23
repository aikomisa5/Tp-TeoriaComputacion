package automata;

import java.util.ArrayList;
import java.util.List;

public class Automata {
	
	private List<String> simbolosInput;
	private int cantEstados;
	private List<String> estadosFinales;
	private List<Proyeccion> proyecciones;
	private List<String> estados;
	private List<List<String>> estadosListado;
	
	public Automata() {
		super();
		simbolosInput = new ArrayList<String>();
		estadosFinales = new ArrayList<String>();
		proyecciones = new ArrayList<Proyeccion>();
		estados = new ArrayList<String>();
		estadosListado = new ArrayList<List<String>>();
	}
	
	public Automata(List<String> simbolosInput, int cantEstados, List<String> estadosFinales,
			List<Proyeccion> proyecciones) {
		super();
		this.simbolosInput = simbolosInput;
		this.cantEstados = cantEstados;
		this.estadosFinales = estadosFinales;
		this.proyecciones = proyecciones;
	}
	
	public Automata(List<String> simbolosInput, int cantEstados, List<String> estadosFinales,
			List<Proyeccion> proyecciones, List<String> estados, List<List<String>> estadosListado) {
		super();
		this.simbolosInput = simbolosInput;
		this.cantEstados = cantEstados;
		this.estadosFinales = estadosFinales;
		this.proyecciones = proyecciones;
		this.estados = estados;
		this.estadosListado = estadosListado;
	}

	public List<String> getSimbolosInput() {
		return simbolosInput;
	}
	
	public void setSimbolosInput(List<String> simbolosInput) {
		this.simbolosInput = simbolosInput;
	}
	
	public int getCantEstados() {
		return cantEstados;
	}
	
	public void setCantEstados(int cantEstados) {
		this.cantEstados = cantEstados;
	}
	
	public List<String> getEstadosFinales() {
		return estadosFinales;
	}
	
	public void setEstadosFinales(List<String> estadosFinales) {
		this.estadosFinales = estadosFinales;
	}
	
	public List<Proyeccion> getProyecciones() {
		return proyecciones;
	}
	
	public void setProyecciones(List<Proyeccion> proyecciones) {
		this.proyecciones = proyecciones;
	}

	public List<String> getEstados() {
		return estados;
	}

	public void setEstados(List<String> estados) {
		this.estados = estados;
	}

	public List<List<String>> getEstadosListado() {
		return estadosListado;
	}

	public void setEstadosListado(List<List<String>> estadosListado) {
		this.estadosListado = estadosListado;
	}
}
