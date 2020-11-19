package test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import automata.Automata;
import automata.AutomataService;
import automata.ListComparator;
import automata.Transicion;
import exceptions.BadFileException;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestEjercicio2a_2 {
	
	AutomataService automataService = new AutomataService();
	
	private Automata e_afnd = new Automata();
	private Automata afd = new Automata();

	String fileName = "automata_2.txt";

	@Test
	public void testAutomata() throws FileNotFoundException, BadFileException {

		boolean testExitoso = true;
		
		e_afnd = automataService.getAFNDFromTxtFile(fileName);
		afd = automataService.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());

		automataService.printAutomata(e_afnd, "E-AFND");
		automataService.printAutomata(afd, "AFD");
		
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
		
		e_afnd_check.setCantEstados(4);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("2");
		estadosFinales.add("3");
		estadosFinales.add("4");
		
		e_afnd_check.setEstadosFinales(estadosFinales);
		
		Transicion proyeccion1 = new Transicion();
		proyeccion1.setEstadoSalida("1");
		proyeccion1.setSimboloInput("E");
		proyeccion1.setEstadoLlegada("2");
		
		Transicion proyeccion2 = new Transicion();
		proyeccion2.setEstadoSalida("1");
		proyeccion2.setSimboloInput("E");
		proyeccion2.setEstadoLlegada("3");
		
		Transicion proyeccion3 = new Transicion();
		proyeccion3.setEstadoSalida("1");
		proyeccion3.setSimboloInput("E");
		proyeccion3.setEstadoLlegada("4");
		
		Transicion proyeccion4 = new Transicion();
		proyeccion4.setEstadoSalida("2");
		proyeccion4.setSimboloInput("a");
		proyeccion4.setEstadoLlegada("3");
		
		Transicion proyeccion5 = new Transicion();
		proyeccion5.setEstadoSalida("2");
		proyeccion5.setSimboloInput("b");
		proyeccion5.setEstadoLlegada("3");
		
		Transicion proyeccion6 = new Transicion();
		proyeccion6.setEstadoSalida("2");
		proyeccion6.setSimboloInput("c");
		proyeccion6.setEstadoLlegada("3");
		
		Transicion proyeccion7 = new Transicion();
		proyeccion7.setEstadoSalida("3");
		proyeccion7.setSimboloInput("a");
		proyeccion7.setEstadoLlegada("2");
		
		Transicion proyeccion8 = new Transicion();
		proyeccion8.setEstadoSalida("3");
		proyeccion8.setSimboloInput("b");
		proyeccion8.setEstadoLlegada("2");
		
		Transicion proyeccion9 = new Transicion();
		proyeccion9.setEstadoSalida("3");
		proyeccion9.setSimboloInput("c");
		proyeccion9.setEstadoLlegada("2");
		
		Transicion proyeccion10 = new Transicion();
		proyeccion10.setEstadoSalida("3");
		proyeccion10.setSimboloInput("a");
		proyeccion10.setEstadoLlegada("4");
		
		Transicion proyeccion11 = new Transicion();
		proyeccion11.setEstadoSalida("3");
		proyeccion11.setSimboloInput("b");
		proyeccion11.setEstadoLlegada("4");
		
		Transicion proyeccion12 = new Transicion();
		proyeccion12.setEstadoSalida("3");
		proyeccion12.setSimboloInput("c");
		proyeccion12.setEstadoLlegada("4");
		
		Transicion proyeccion13 = new Transicion();
		proyeccion13.setEstadoSalida("4");
		proyeccion13.setSimboloInput("a");
		proyeccion13.setEstadoLlegada("3");
		
		Transicion proyeccion14 = new Transicion();
		proyeccion14.setEstadoSalida("4");
		proyeccion14.setSimboloInput("b");
		proyeccion14.setEstadoLlegada("3");
		
		Transicion proyeccion15 = new Transicion();
		proyeccion15.setEstadoSalida("4");
		proyeccion15.setSimboloInput("c");
		proyeccion15.setEstadoLlegada("3");
		
		List<Transicion> proyecciones = new ArrayList<Transicion>();
		proyecciones.add(proyeccion1);
		proyecciones.add(proyeccion2);
		proyecciones.add(proyeccion3);
		proyecciones.add(proyeccion4);
		proyecciones.add(proyeccion5);
		proyecciones.add(proyeccion6);
		proyecciones.add(proyeccion7);
		proyecciones.add(proyeccion8);
		proyecciones.add(proyeccion9);
		proyecciones.add(proyeccion10);
		proyecciones.add(proyeccion11);
		proyecciones.add(proyeccion12);
		proyecciones.add(proyeccion13);
		proyecciones.add(proyeccion14);
		proyecciones.add(proyeccion15);
		
		e_afnd_check.setTransiciones(proyecciones);
		
		Collections.sort(e_afnd_check.getSimbolosInput());
		Collections.sort(e_afnd_check.getEstadosFinales());
		Collections.sort(e_afnd_check.getTransiciones());
		Collections.sort(e_afnd_check.getEstados());
		Collections.sort(e_afnd_check.getEstadosListado(), new ListComparator<>());
		
		return e_afnd_check;
	}
	
	private Automata getAFDToCheck() {
		
		Automata afd_check = new Automata();
		
		List<String> simbolosInput = new ArrayList<String>();
		simbolosInput.add("a");
		simbolosInput.add("b");
		simbolosInput.add("c");
		
		afd_check.setSimbolosInput(simbolosInput);
		
		afd_check.setCantEstados(2);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("1234");
		estadosFinales.add("234");
		
		afd_check.setEstadosFinales(estadosFinales);
		
		Transicion proyeccion1 = new Transicion();
		proyeccion1.setEstadoSalida("1234");
		proyeccion1.setSimboloInput("a");
		proyeccion1.setEstadoLlegada("234");
		
		Transicion proyeccion2 = new Transicion();
		proyeccion2.setEstadoSalida("1234");
		proyeccion2.setSimboloInput("b");
		proyeccion2.setEstadoLlegada("234");
		
		Transicion proyeccion3 = new Transicion();
		proyeccion3.setEstadoSalida("1234");
		proyeccion3.setSimboloInput("c");
		proyeccion3.setEstadoLlegada("234");
		
		Transicion proyeccion4 = new Transicion();
		proyeccion4.setEstadoSalida("234");
		proyeccion4.setSimboloInput("a");
		proyeccion4.setEstadoLlegada("234");
		
		Transicion proyeccion5 = new Transicion();
		proyeccion5.setEstadoSalida("234");
		proyeccion5.setSimboloInput("b");
		proyeccion5.setEstadoLlegada("234");
		
		Transicion proyeccion6 = new Transicion();
		proyeccion6.setEstadoSalida("234");
		proyeccion6.setSimboloInput("c");
		proyeccion6.setEstadoLlegada("234");
		
		List<Transicion> proyecciones = new ArrayList<Transicion>();
		proyecciones.add(proyeccion1);
		proyecciones.add(proyeccion2);
		proyecciones.add(proyeccion3);
		proyecciones.add(proyeccion4);
		proyecciones.add(proyeccion5);
		proyecciones.add(proyeccion6);
		
		afd_check.setTransiciones(proyecciones);
		
		afd_check.setEstadoInicial("1234");
		
		List<List<String>> estadosListado = new ArrayList<List<String>>();
		
		List<String> estado1 = new ArrayList<String>();
		estado1.add("1");
		estado1.add("2");
		estado1.add("3");
		estado1.add("4");
		
		List<String> estado2 = new ArrayList<String>();
		estado2.add("2");
		estado2.add("3");
		estado2.add("4");
		
		estadosListado.add(estado1);
		estadosListado.add(estado2);
		
		afd_check.setEstadosListado(estadosListado);
		
		Collections.sort(afd_check.getSimbolosInput());
		Collections.sort(afd_check.getEstadosFinales());
		Collections.sort(afd_check.getTransiciones());
		Collections.sort(afd_check.getEstados());
		Collections.sort(afd_check.getEstadosListado(), new ListComparator<>());
		
		return afd_check;
	}
}
