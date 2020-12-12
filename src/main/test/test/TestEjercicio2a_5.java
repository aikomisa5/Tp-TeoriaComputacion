package test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import automata.Automata;
import automata.Transicion;
import exceptions.BadFileException;

public class TestEjercicio2a_5 extends TestAutomataBase {
	
	private Automata e_afnd = new Automata();
	private Automata afd = new Automata();

	String fileName = "automata_5.txt";

	@Test
	public void testAutomataSuccess() throws FileNotFoundException, BadFileException {

		boolean testExitoso = true;
		
		e_afnd = service.getAFNDFromTxtFile(fileName);
		afd = service.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());

		System.out.println(LINEA);
		System.out.println("TEST 2a_5 - Success");
		System.out.println(LINEA);
		service.printAutomata(e_afnd, "E-AFND");
		service.printAutomata(afd, "AFD");
		
		Automata e_afnd_check = getEAFNDForSuccess();
		
		if (e_afnd.equals(e_afnd_check) == false) {
			testExitoso = false;
		}
		
		Automata afd_check = getAFDForSuccess();
		
		if (afd.equals(afd_check) == false) {
			testExitoso = false;
		}
		
		assertTrue(testExitoso);
	}
	
	@Test
	public void testAutomataFailAFND() throws FileNotFoundException, BadFileException {

		boolean testFallo = false;
		
		e_afnd = service.getAFNDFromTxtFile(fileName);

		System.out.println(LINEA);
		System.out.println("TEST 2a_5 - Fail AFND");
		System.out.println(LINEA);
		service.printAutomata(e_afnd, "E-AFND");
		
		Automata e_afnd_check = getEAFNDForFail();
		
		if (e_afnd.equals(e_afnd_check) == false) {
			testFallo = true;
		}
		
		assertTrue(testFallo);
	}
	
	@Test
	public void testAutomataFailAFD() throws FileNotFoundException, BadFileException {

		boolean testFallo = false;
		
		afd = service.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());

		System.out.println(LINEA);
		System.out.println("TEST 2a_5 - Fail AFD");
		System.out.println(LINEA);
		service.printAutomata(afd, "AFD");
		
		Automata afd_check = getAFDForFail();
		
		if (afd.equals(afd_check) == false) {
			testFallo = true;
		}
		
		assertTrue(testFallo);
	}

	private Automata getEAFNDForSuccess() {
		
		Automata e_afnd_check = new Automata();
		
		List<String> simbolosInput = new ArrayList<String>();
		simbolosInput.add("a");
		simbolosInput.add("b");
		simbolosInput.add("c");
		
		e_afnd_check.setSimbolosInput(simbolosInput);
		
		e_afnd_check.setCantEstados(2);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("2");
		
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
		
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("1");
		transicion5.setSimboloInput("b");
		transicion5.setEstadoLlegada("2");
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("2");
		transicion6.setSimboloInput("a");
		transicion6.setEstadoLlegada("2");
		
		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		transiciones.add(transicion4);
		transiciones.add(transicion5);
		transiciones.add(transicion6);
		
		e_afnd_check.setTransiciones(transiciones);
		
		service.ordenarAutomata(e_afnd_check);
		
		return e_afnd_check;
	}
	
	private Automata getAFDForSuccess() {
		
		Automata afd_check = new Automata();
		
		List<String> simbolosInput = new ArrayList<String>();
		simbolosInput.add("a");
		simbolosInput.add("b");
		simbolosInput.add("c");
		
		afd_check.setSimbolosInput(simbolosInput);
		
		afd_check.setCantEstados(2);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("12");
		
		afd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("1");
		transicion1.setSimboloInput("a");
		transicion1.setEstadoLlegada("1");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("1");
		transicion2.setSimboloInput("c");
		transicion2.setEstadoLlegada("1");
		
		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("1");
		transicion3.setSimboloInput("b");
		transicion3.setEstadoLlegada("12");
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("12");
		transicion4.setSimboloInput("a");
		transicion4.setEstadoLlegada("12");
		
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("12");
		transicion5.setSimboloInput("b");
		transicion5.setEstadoLlegada("12");
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("12");
		transicion6.setSimboloInput("c");
		transicion6.setEstadoLlegada("1");
		
		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		transiciones.add(transicion4);
		transiciones.add(transicion5);
		transiciones.add(transicion6);
		
		afd_check.setTransiciones(transiciones);
		
		afd_check.setEstadoInicial("1");
		
		List<List<String>> estadosListado = new ArrayList<List<String>>();
		
		List<String> estado1 = new ArrayList<String>();
		estado1.add("1");
		
		List<String> estado2 = new ArrayList<String>();
		estado2.add("1");
		estado2.add("2");
		
		estadosListado.add(estado1);
		estadosListado.add(estado2);
		
		afd_check.setEstadosListado(estadosListado);
		
		service.ordenarAutomata(afd_check);
		
		return afd_check;
	}
	
	private Automata getEAFNDForFail() {
		
		Automata e_afnd_check = new Automata();
		
		List<String> simbolosInput = new ArrayList<String>();
		simbolosInput.add("a");
		simbolosInput.add("b");
		simbolosInput.add("c");
		
		e_afnd_check.setSimbolosInput(simbolosInput);
		
		e_afnd_check.setCantEstados(2);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("2");
		
		e_afnd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("1");
		transicion1.setSimboloInput("E");
		transicion1.setEstadoLlegada("1");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("1");
		transicion2.setSimboloInput("a");
		transicion2.setEstadoLlegada("1");

//		Transicion transicion3 = new Transicion();
//		transicion3.setEstadoSalida("1");
//		transicion3.setSimboloInput("b");
//		transicion3.setEstadoLlegada("1");
		
		/**
		 * Solo hago este pequeño cambio para probar la falla
		 **/
		
		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("2");
		transicion3.setSimboloInput("b");
		transicion3.setEstadoLlegada("1");
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("1");
		transicion4.setSimboloInput("c");
		transicion4.setEstadoLlegada("1");
		
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("1");
		transicion5.setSimboloInput("b");
		transicion5.setEstadoLlegada("2");
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("2");
		transicion6.setSimboloInput("a");
		transicion6.setEstadoLlegada("2");
		
		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		transiciones.add(transicion4);
		transiciones.add(transicion5);
		transiciones.add(transicion6);
		
		e_afnd_check.setTransiciones(transiciones);
		
		service.ordenarAutomata(e_afnd_check);
		
		return e_afnd_check;
	}
	
	private Automata getAFDForFail() {
		
		Automata afd_check = new Automata();
		
		List<String> simbolosInput = new ArrayList<String>();
		simbolosInput.add("a");
		simbolosInput.add("b");
		simbolosInput.add("c");
		
		afd_check.setSimbolosInput(simbolosInput);
		
		afd_check.setCantEstados(2);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("12");
		
		afd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("1");
		transicion1.setSimboloInput("a");
		transicion1.setEstadoLlegada("1");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("1");
		transicion2.setSimboloInput("c");
		transicion2.setEstadoLlegada("1");
		
		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("1");
		transicion3.setSimboloInput("b");
		transicion3.setEstadoLlegada("12");
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("12");
		transicion4.setSimboloInput("a");
		transicion4.setEstadoLlegada("12");
		
//		Transicion transicion5 = new Transicion();
//		transicion5.setEstadoSalida("12");
//		transicion5.setSimboloInput("b");
//		transicion5.setEstadoLlegada("12");
		
		/**
		 * Solo hago este pequeño cambio para probar la falla
		 **/
		
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("12");
		transicion5.setSimboloInput("b");
		transicion5.setEstadoLlegada("13");
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("12");
		transicion6.setSimboloInput("c");
		transicion6.setEstadoLlegada("1");

		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		transiciones.add(transicion4);
		transiciones.add(transicion5);
		transiciones.add(transicion6);
		
		afd_check.setTransiciones(transiciones);
		
		afd_check.setEstadoInicial("1");
		
		List<List<String>> estadosListado = new ArrayList<List<String>>();
		
		List<String> estado1 = new ArrayList<String>();
		estado1.add("1");
		
		List<String> estado2 = new ArrayList<String>();
		estado2.add("1");
		estado2.add("2");
		
		estadosListado.add(estado1);
		estadosListado.add(estado2);
		
		afd_check.setEstadosListado(estadosListado);
		
		service.ordenarAutomata(afd_check);
		
		return afd_check;
	}
}
