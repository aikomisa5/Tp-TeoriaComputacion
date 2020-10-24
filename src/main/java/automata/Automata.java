package automata;

import java.util.ArrayList;
import java.util.List;

public class Automata {
	
	List<String> simbolosInput;
	int cantEstados;
	List<String> estadosFinales;
	List<Proyeccion> proyecciones;
	
	public Automata() {
		super();
		simbolosInput = new ArrayList<String>();
		estadosFinales = new ArrayList<String>();
		proyecciones = new ArrayList<Proyeccion>();
	}
	
	public Automata(List<String> simbolosInput, int cantEstados, List<String> estadosFinales,
			List<Proyeccion> proyecciones) {
		super();
		this.simbolosInput = simbolosInput;
		this.cantEstados = cantEstados;
		this.estadosFinales = estadosFinales;
		this.proyecciones = proyecciones;
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
}
