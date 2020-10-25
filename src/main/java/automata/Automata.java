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
	private String estadoInicial;
	
	public Automata() {
		super();
		simbolosInput = new ArrayList<String>();
		cantEstados = 0;
		estadosFinales = new ArrayList<String>();
		proyecciones = new ArrayList<Proyeccion>();
		estados = new ArrayList<String>();
		estadosListado = new ArrayList<List<String>>();
		estadoInicial = new String();
	}
	
	public Automata(List<String> simbolosInput, int cantEstados, List<String> estadosFinales,
			List<Proyeccion> proyecciones) {
		super();
		this.simbolosInput = simbolosInput;
		this.cantEstados = cantEstados;
		this.estadosFinales = estadosFinales;
		this.proyecciones = proyecciones;
		this.estados = new ArrayList<String>();
		this.estadosListado = new ArrayList<List<String>>();
		this.estadoInicial = new String();
	}
	
	public Automata(List<String> simbolosInput, int cantEstados, List<String> estadosFinales,
			List<Proyeccion> proyecciones, List<String> estados, List<List<String>> estadosListado,
			String estadoInicial) {
		super();
		this.simbolosInput = simbolosInput;
		this.cantEstados = cantEstados;
		this.estadosFinales = estadosFinales;
		this.proyecciones = proyecciones;
		this.estados = estados;
		this.estadosListado = estadosListado;
		this.estadoInicial = estadoInicial;
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

	public String getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(String estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        
        final Automata automata = (Automata) obj;
        
        if (this.simbolosInput.equals(automata.getSimbolosInput()) == false) {
        	return false;
        }
        
        if (this.cantEstados != automata.getCantEstados()) {
        	return false;
        }
        
        if (this.estadosFinales.equals(automata.getEstadosFinales()) == false) {
        	return false;
        }
        
        if (this.proyecciones.equals(automata.getProyecciones()) == false) {
        	return false;
        }
        
        if (this.estados.equals(automata.getEstados()) == false) {
        	return false;
        }
        
        if (this.estadosListado.equals(automata.getEstadosListado()) == false) {
        	return false;
        }
        
        if (this.estadoInicial.equals(automata.getEstadoInicial()) == false) {
        	return false;
        }
		
		return true;
	}
	
	
}
