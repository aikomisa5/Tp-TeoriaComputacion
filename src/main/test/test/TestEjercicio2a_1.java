package test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import automata.Automata;
import automata.AutomataService;
import automata.Proyeccion;
import exceptions.BadFileException;
import org.junit.jupiter.api.Test;

class TestEjercicio2a_1 {
	
	AutomataService automataService = new AutomataService();
	
	private Automata e_afnd = new Automata();
	private Automata afd = new Automata();

	@Test
	public void testAutomata() throws FileNotFoundException, BadFileException {

		boolean testExitoso = true;
		
		e_afnd = automataService.getAutomataFromTxtFile();
		afd = automataService.getAFD(e_afnd.getProyecciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());

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
		
		e_afnd_check.setCantEstados(3);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("3");
		
		e_afnd_check.setEstadosFinales(estadosFinales);
		
		Proyeccion proyeccion1 = new Proyeccion();
		proyeccion1.setEstadoSalida("1");
		proyeccion1.setSimboloInput("E");
		proyeccion1.setEstadoLlegada("2");
		
		Proyeccion proyeccion2 = new Proyeccion();
		proyeccion2.setEstadoSalida("1");
		proyeccion2.setSimboloInput("b");
		proyeccion2.setEstadoLlegada("2");
		
		Proyeccion proyeccion3 = new Proyeccion();
		proyeccion3.setEstadoSalida("1");
		proyeccion3.setSimboloInput("E");
		proyeccion3.setEstadoLlegada("3");
		
		Proyeccion proyeccion4 = new Proyeccion();
		proyeccion4.setEstadoSalida("1");
		proyeccion4.setSimboloInput("c");
		proyeccion4.setEstadoLlegada("3");
		
		Proyeccion proyeccion5 = new Proyeccion();
		proyeccion5.setEstadoSalida("2");
		proyeccion5.setSimboloInput("c");
		proyeccion5.setEstadoLlegada("2");
		
		Proyeccion proyeccion6 = new Proyeccion();
		proyeccion6.setEstadoSalida("2");
		proyeccion6.setSimboloInput("a");
		proyeccion6.setEstadoLlegada("1");
		
		Proyeccion proyeccion7 = new Proyeccion();
		proyeccion7.setEstadoSalida("2");
		proyeccion7.setSimboloInput("c");
		proyeccion7.setEstadoLlegada("1");
		
		Proyeccion proyeccion8 = new Proyeccion();
		proyeccion8.setEstadoSalida("2");
		proyeccion8.setSimboloInput("b");
		proyeccion8.setEstadoLlegada("3");
		
		List<Proyeccion> proyecciones = new ArrayList<Proyeccion>();
		proyecciones.add(proyeccion1);
		proyecciones.add(proyeccion2);
		proyecciones.add(proyeccion3);
		proyecciones.add(proyeccion4);
		proyecciones.add(proyeccion5);
		proyecciones.add(proyeccion6);
		proyecciones.add(proyeccion7);
		proyecciones.add(proyeccion8);
		
		e_afnd_check.setProyecciones(proyecciones);
		return e_afnd_check;
	}
	
	private Automata getAFDToCheck() {
		
		Automata afd_check = new Automata();
		
		List<String> simbolosInput = new ArrayList<String>();
		simbolosInput.add("a");
		simbolosInput.add("b");
		simbolosInput.add("c");
		
		afd_check.setSimbolosInput(simbolosInput);
		
		afd_check.setCantEstados(4);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("123");
		estadosFinales.add("23");
		estadosFinales.add("3");
		
		afd_check.setEstadosFinales(estadosFinales);
		
		Proyeccion proyeccion1 = new Proyeccion();
		proyeccion1.setEstadoSalida("123");
		proyeccion1.setSimboloInput("a");
		proyeccion1.setEstadoLlegada("123");
		
		Proyeccion proyeccion2 = new Proyeccion();
		proyeccion2.setEstadoSalida("123");
		proyeccion2.setSimboloInput("b");
		proyeccion2.setEstadoLlegada("23");
		
		Proyeccion proyeccion3 = new Proyeccion();
		proyeccion3.setEstadoSalida("123");
		proyeccion3.setSimboloInput("c");
		proyeccion3.setEstadoLlegada("123");
		
		Proyeccion proyeccion4 = new Proyeccion();
		proyeccion4.setEstadoSalida("23");
		proyeccion4.setSimboloInput("a");
		proyeccion4.setEstadoLlegada("123");
		
		Proyeccion proyeccion5 = new Proyeccion();
		proyeccion5.setEstadoSalida("23");
		proyeccion5.setSimboloInput("b");
		proyeccion5.setEstadoLlegada("3");
		
		Proyeccion proyeccion6 = new Proyeccion();
		proyeccion6.setEstadoSalida("23");
		proyeccion6.setSimboloInput("c");
		proyeccion6.setEstadoLlegada("123");
		
		Proyeccion proyeccion7 = new Proyeccion();
		proyeccion7.setEstadoSalida("3");
		proyeccion7.setSimboloInput("a");
		proyeccion7.setEstadoLlegada("T");
		
		Proyeccion proyeccion8 = new Proyeccion();
		proyeccion8.setEstadoSalida("3");
		proyeccion8.setSimboloInput("b");
		proyeccion8.setEstadoLlegada("T");
		
		Proyeccion proyeccion9 = new Proyeccion();
		proyeccion9.setEstadoSalida("3");
		proyeccion9.setSimboloInput("c");
		proyeccion9.setEstadoLlegada("T");
		
		Proyeccion proyeccion10 = new Proyeccion();
		proyeccion10.setEstadoSalida("T");
		proyeccion10.setSimboloInput("a");
		proyeccion10.setEstadoLlegada("T");
		
		Proyeccion proyeccion11 = new Proyeccion();
		proyeccion11.setEstadoSalida("T");
		proyeccion11.setSimboloInput("b");
		proyeccion11.setEstadoLlegada("T");
		
		Proyeccion proyeccion12 = new Proyeccion();
		proyeccion12.setEstadoSalida("T");
		proyeccion12.setSimboloInput("c");
		proyeccion12.setEstadoLlegada("T");
		
		List<Proyeccion> proyecciones = new ArrayList<Proyeccion>();
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
		
		afd_check.setProyecciones(proyecciones);
		
		afd_check.setEstadoInicial("123");
		
		List<List<String>> estadosListado = new ArrayList<List<String>>();
		
		List<String> estado1 = new ArrayList<String>();
		estado1.add("1");
		estado1.add("2");
		estado1.add("3");
		
		List<String> estado2 = new ArrayList<String>();
		estado2.add("2");
		estado2.add("3");
		
		List<String> estado3 = new ArrayList<String>();
		estado3.add("3");
		
		List<String> estado4 = new ArrayList<String>();
		estado4.add("T");
		
		estadosListado.add(estado1);
		estadosListado.add(estado2);
		estadosListado.add(estado3);
		estadosListado.add(estado4);
		
		afd_check.setEstadosListado(estadosListado);
		
		return afd_check;
	}
}
