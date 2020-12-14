package automata;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import exceptions.BadAutomataException;
import exceptions.BadFileException;

public class Automata {
	
	private AutomataService service;
	
	/**
	 * EstadosListado es una lista de listas, donde cada lista representa un estado del Automata
	 **/
	
	private List<String> simbolosInput;
	private int cantEstados;
	private List<String> estadosFinales;
	private List<Transicion> transiciones;
	private List<String> estados;
	private List<List<String>> estadosListado;
	private String estadoInicial;
	
	public Automata() {
		super();
		service = new AutomataService();
		simbolosInput = new ArrayList<String>();
		cantEstados = 0;
		estadosFinales = new ArrayList<String>();
		transiciones = new ArrayList<Transicion>();
		estados = new ArrayList<String>();
		estadosListado = new ArrayList<List<String>>();
		estadoInicial = new String();
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
	
	public List<Transicion> getTransiciones() {
		return transiciones;
	}
	
	public void setTransiciones(List<Transicion> transiciones) {
		this.transiciones = transiciones;
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
	
	public boolean procesar(String w) throws FileNotFoundException, BadFileException, BadAutomataException {

		boolean response = false;

//		Automata e_afnd = service.getAutomataFromTxtFile("automata.txt");
//		Automata afd = service.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());
		response = service.cadenaPerteneceAlAFD(w, this);

		return response;
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
        
        if (this.transiciones.equals(automata.getTransiciones()) == false) {
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
