package test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import automata.Automata;
import automata.Transicion;
import exceptions.BadFileException;

public class TestEjercicio2a_4 extends TestAutomataBase{
	
	private Automata e_afnd = new Automata();
	private Automata afd = new Automata();

	String fileName = "automata_4.txt";

	@Test
	public void testAutomata() throws FileNotFoundException, BadFileException {

		boolean testExitoso = true;
		
		e_afnd = service.getAFNDFromTxtFile(fileName);
		afd = service.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());

		System.out.println(LINEA);
		System.out.println("TEST 4");
		System.out.println(LINEA);
		service.printAutomata(e_afnd, "E-AFND");
		service.printAutomata(afd, "AFD");
		
		Automata e_afnd_check = getEAFNDToCheck();
		
		if (e_afnd.equals(e_afnd_check) == false) {
			testExitoso = false;
		}
		
		Automata afd_check = getAFDToCheck();
		
		if (afd.equals(afd_check) == false) {
			testExitoso = false;
		}
		
		assertTrue(testExitoso);
	}

	private Automata getEAFNDToCheck() {
		
		Automata e_afnd_check = new Automata();
		
		List<String> simbolosInput = new ArrayList<String>();
		simbolosInput.add("a");
		simbolosInput.add("b");
		simbolosInput.add("c");
		
		e_afnd_check.setSimbolosInput(simbolosInput);
		
		e_afnd_check.setCantEstados(1);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("1");
		
		e_afnd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("1");
		transicion1.setSimboloInput("E");
		transicion1.setEstadoLlegada("1");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("1");
		transicion2.setSimboloInput("a");
		transicion2.setEstadoLlegada("1");

		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("1");
		transicion3.setSimboloInput("b");
		transicion3.setEstadoLlegada("1");
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("1");
		transicion4.setSimboloInput("c");
		transicion4.setEstadoLlegada("1");
		
		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		transiciones.add(transicion4);
		
		e_afnd_check.setTransiciones(transiciones);
		
		service.ordenarAutomata(e_afnd_check);
		
		return e_afnd_check;
	}
	
	private Automata getAFDToCheck() {
		
		Automata afd_check = new Automata();
		
		List<String> simbolosInput = new ArrayList<String>();
		simbolosInput.add("a");
		simbolosInput.add("b");
		simbolosInput.add("c");
		
		afd_check.setSimbolosInput(simbolosInput);
		
		afd_check.setCantEstados(1);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("1");
		
		afd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("1");
		transicion1.setSimboloInput("a");
		transicion1.setEstadoLlegada("1");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("1");
		transicion2.setSimboloInput("b");
		transicion2.setEstadoLlegada("1");

		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("1");
		transicion3.setSimboloInput("c");
		transicion3.setEstadoLlegada("1");
		
		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		
		afd_check.setTransiciones(transiciones);
		
		afd_check.setEstadoInicial("1");
		
		List<List<String>> estadosListado = new ArrayList<List<String>>();
		
		List<String> estado1 = new ArrayList<String>();
		estado1.add("1");
		
		estadosListado.add(estado1);
		
		afd_check.setEstadosListado(estadosListado);
		
		service.ordenarAutomata(afd_check);
		
		return afd_check;
	}
}
