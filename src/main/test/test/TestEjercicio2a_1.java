package test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import automata.Automata;
import automata.ListComparator;
import automata.Transicion;
import exceptions.BadFileException;

public class TestEjercicio2a_1 extends TestAutomataBase{
	
	private Automata e_afnd = new Automata();
	private Automata afd = new Automata();

	String fileName = "automata_1.txt";

	@Test
	public void testAutomataSuccess() throws FileNotFoundException, BadFileException {

		boolean testExitoso = true;
		
		e_afnd = service.getAFNDFromTxtFile(fileName);
		afd = service.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());

		System.out.println(LINEA);
		System.out.println("TEST 2a_1 - Success");
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
		System.out.println("TEST 2a_1 - Fail AFND");
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
		System.out.println("TEST 2a_1 - Fail AFD");
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
		
		e_afnd_check.setCantEstados(3);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("3");
		
		e_afnd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("1");
		transicion1.setSimboloInput("E");
		transicion1.setEstadoLlegada("2");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("1");
		transicion2.setSimboloInput("b");
		transicion2.setEstadoLlegada("2");
		
		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("1");
		transicion3.setSimboloInput("E");
		transicion3.setEstadoLlegada("3");
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("1");
		transicion4.setSimboloInput("c");
		transicion4.setEstadoLlegada("3");
		
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("2");
		transicion5.setSimboloInput("c");
		transicion5.setEstadoLlegada("2");
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("2");
		transicion6.setSimboloInput("a");
		transicion6.setEstadoLlegada("1");
		
		Transicion transicion7 = new Transicion();
		transicion7.setEstadoSalida("2");
		transicion7.setSimboloInput("c");
		transicion7.setEstadoLlegada("1");
		
		Transicion transicion8 = new Transicion();
		transicion8.setEstadoSalida("2");
		transicion8.setSimboloInput("b");
		transicion8.setEstadoLlegada("3");
		
		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		transiciones.add(transicion4);
		transiciones.add(transicion5);
		transiciones.add(transicion6);
		transiciones.add(transicion7);
		transiciones.add(transicion8);
		
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
		
		afd_check.setCantEstados(4);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("123");
		estadosFinales.add("23");
		estadosFinales.add("3");
		
		afd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("123");
		transicion1.setSimboloInput("a");
		transicion1.setEstadoLlegada("123");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("123");
		transicion2.setSimboloInput("b");
		transicion2.setEstadoLlegada("23");
		
		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("123");
		transicion3.setSimboloInput("c");
		transicion3.setEstadoLlegada("123");
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("23");
		transicion4.setSimboloInput("a");
		transicion4.setEstadoLlegada("123");
		
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("23");
		transicion5.setSimboloInput("b");
		transicion5.setEstadoLlegada("3");
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("23");
		transicion6.setSimboloInput("c");
		transicion6.setEstadoLlegada("123");
		
		Transicion transicion7 = new Transicion();
		transicion7.setEstadoSalida("3");
		transicion7.setSimboloInput("a");
		transicion7.setEstadoLlegada("T");
		
		Transicion transicion8 = new Transicion();
		transicion8.setEstadoSalida("3");
		transicion8.setSimboloInput("b");
		transicion8.setEstadoLlegada("T");
		
		Transicion transicion9 = new Transicion();
		transicion9.setEstadoSalida("3");
		transicion9.setSimboloInput("c");
		transicion9.setEstadoLlegada("T");
		
		Transicion transicion10 = new Transicion();
		transicion10.setEstadoSalida("T");
		transicion10.setSimboloInput("a");
		transicion10.setEstadoLlegada("T");
		
		Transicion transicion11 = new Transicion();
		transicion11.setEstadoSalida("T");
		transicion11.setSimboloInput("b");
		transicion11.setEstadoLlegada("T");
		
		Transicion transicion12 = new Transicion();
		transicion12.setEstadoSalida("T");
		transicion12.setSimboloInput("c");
		transicion12.setEstadoLlegada("T");
		
		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		transiciones.add(transicion4);
		transiciones.add(transicion5);
		transiciones.add(transicion6);
		transiciones.add(transicion7);
		transiciones.add(transicion8);
		transiciones.add(transicion9);
		transiciones.add(transicion10);
		transiciones.add(transicion11);
		transiciones.add(transicion12);
		
		afd_check.setTransiciones(transiciones);
		
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
		
		Collections.sort(afd_check.getSimbolosInput());
		Collections.sort(afd_check.getEstadosFinales());
		Collections.sort(afd_check.getTransiciones());
		Collections.sort(afd_check.getEstados());
		Collections.sort(afd_check.getEstadosListado(), new ListComparator<>());
		
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
		
		e_afnd_check.setCantEstados(3);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("3");
		
		e_afnd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("1");
		transicion1.setSimboloInput("E");
		transicion1.setEstadoLlegada("2");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("1");
		transicion2.setSimboloInput("b");
		transicion2.setEstadoLlegada("2");
		
		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("1");
		transicion3.setSimboloInput("E");
		transicion3.setEstadoLlegada("3");
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("1");
		transicion4.setSimboloInput("c");
		transicion4.setEstadoLlegada("3");
		
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("2");
		transicion5.setSimboloInput("c");
		transicion5.setEstadoLlegada("2");
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("2");
		transicion6.setSimboloInput("a");
		transicion6.setEstadoLlegada("1");
		
		Transicion transicion7 = new Transicion();
		transicion7.setEstadoSalida("2");
		transicion7.setSimboloInput("c");
		transicion7.setEstadoLlegada("1");
		
//		Transicion transicion8 = new Transicion();
//		transicion8.setEstadoSalida("2");
//		transicion8.setSimboloInput("b");
//		transicion8.setEstadoLlegada("3");
		
		/**
		 * Solo hago este pequeño cambio para probar la falla
		 **/
		
		Transicion transicion8 = new Transicion();
		transicion8.setEstadoSalida("3");
		transicion8.setSimboloInput("b");
		transicion8.setEstadoLlegada("2");
		
		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		transiciones.add(transicion4);
		transiciones.add(transicion5);
		transiciones.add(transicion6);
		transiciones.add(transicion7);
		transiciones.add(transicion8);
		
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
		
		afd_check.setCantEstados(4);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("123");
		estadosFinales.add("23");
		estadosFinales.add("3");
		
		afd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("123");
		transicion1.setSimboloInput("a");
		transicion1.setEstadoLlegada("123");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("123");
		transicion2.setSimboloInput("b");
		transicion2.setEstadoLlegada("23");
		
		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("123");
		transicion3.setSimboloInput("c");
		transicion3.setEstadoLlegada("123");
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("23");
		transicion4.setSimboloInput("a");
		transicion4.setEstadoLlegada("123");
		
//		Transicion transicion5 = new Transicion();
//		transicion5.setEstadoSalida("23");
//		transicion5.setSimboloInput("b");
//		transicion5.setEstadoLlegada("3");
	
		/**
		 * Solo hago este pequeño cambio para probar la falla
		 **/
	
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("3");
		transicion5.setSimboloInput("b");
		transicion5.setEstadoLlegada("23");
			
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("23");
		transicion6.setSimboloInput("c");
		transicion6.setEstadoLlegada("123");
		
		Transicion transicion7 = new Transicion();
		transicion7.setEstadoSalida("3");
		transicion7.setSimboloInput("a");
		transicion7.setEstadoLlegada("T");
		
		Transicion transicion8 = new Transicion();
		transicion8.setEstadoSalida("3");
		transicion8.setSimboloInput("b");
		transicion8.setEstadoLlegada("T");
		
		Transicion transicion9 = new Transicion();
		transicion9.setEstadoSalida("3");
		transicion9.setSimboloInput("c");
		transicion9.setEstadoLlegada("T");
		
		Transicion transicion10 = new Transicion();
		transicion10.setEstadoSalida("T");
		transicion10.setSimboloInput("a");
		transicion10.setEstadoLlegada("T");
		
		Transicion transicion11 = new Transicion();
		transicion11.setEstadoSalida("T");
		transicion11.setSimboloInput("b");
		transicion11.setEstadoLlegada("T");
		
		Transicion transicion12 = new Transicion();
		transicion12.setEstadoSalida("T");
		transicion12.setSimboloInput("c");
		transicion12.setEstadoLlegada("T");
		
		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		transiciones.add(transicion4);
		transiciones.add(transicion5);
		transiciones.add(transicion6);
		transiciones.add(transicion7);
		transiciones.add(transicion8);
		transiciones.add(transicion9);
		transiciones.add(transicion10);
		transiciones.add(transicion11);
		transiciones.add(transicion12);
		
		afd_check.setTransiciones(transiciones);
		
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
		
		Collections.sort(afd_check.getSimbolosInput());
		Collections.sort(afd_check.getEstadosFinales());
		Collections.sort(afd_check.getTransiciones());
		Collections.sort(afd_check.getEstados());
		Collections.sort(afd_check.getEstadosListado(), new ListComparator<>());
		
		service.ordenarAutomata(afd_check);
		
		return afd_check;
	}
}
