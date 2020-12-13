package test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import automata.Automata;
import automata.Transicion;
import exceptions.BadFileException;

public class TestEjercicio2a_3 extends TestAutomataBase{
	
	private Automata e_afnd = new Automata();
	private Automata afd = new Automata();

	String fileName = "automata_3.txt";

	@Test
	public void testAutomataSuccess() throws FileNotFoundException, BadFileException {

		boolean testExitoso = true;
		
		e_afnd = service.getAFNDFromTxtFile(fileName);
		afd = service.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());

		System.out.println(LINEA);
		System.out.println("TEST 2a_3 - Success");
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
		System.out.println("TEST 2a_3 - Fail AFND");
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
		System.out.println("TEST 2a_3 - Fail AFD");
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
		
		e_afnd_check.setCantEstados(4);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("2");
		estadosFinales.add("3");
		estadosFinales.add("4");
		
		e_afnd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("1");
		transicion1.setSimboloInput("E");
		transicion1.setEstadoLlegada("2");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("1");
		transicion2.setSimboloInput("E");
		transicion2.setEstadoLlegada("3");
		
		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("1");
		transicion3.setSimboloInput("E");
		transicion3.setEstadoLlegada("4");
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("2");
		transicion4.setSimboloInput("a");
		transicion4.setEstadoLlegada("3");
		
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("3");
		transicion5.setSimboloInput("b");
		transicion5.setEstadoLlegada("4");
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("3");
		transicion6.setSimboloInput("c");
		transicion6.setEstadoLlegada("2");
		
		Transicion transicion7 = new Transicion();
		transicion7.setEstadoSalida("4");
		transicion7.setSimboloInput("a");
		transicion7.setEstadoLlegada("3");
		
		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		transiciones.add(transicion4);
		transiciones.add(transicion5);
		transiciones.add(transicion6);
		transiciones.add(transicion7);
		
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
		
		afd_check.setCantEstados(5);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("1234");
		estadosFinales.add("2");
		estadosFinales.add("3");
		estadosFinales.add("4");
		
		afd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("1234");
		transicion1.setSimboloInput("c");
		transicion1.setEstadoLlegada("2");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("1234");
		transicion2.setSimboloInput("a");
		transicion2.setEstadoLlegada("3");
		
		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("1234");
		transicion3.setSimboloInput("b");
		transicion3.setEstadoLlegada("4");
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("2");
		transicion4.setSimboloInput("a");
		transicion4.setEstadoLlegada("3");
		
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("3");
		transicion5.setSimboloInput("b");
		transicion5.setEstadoLlegada("4");
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("4");
		transicion6.setSimboloInput("a");
		transicion6.setEstadoLlegada("3");
		
		Transicion transicion7 = new Transicion();
		transicion7.setEstadoSalida("2");
		transicion7.setSimboloInput("b");
		transicion7.setEstadoLlegada("T");
		
		Transicion transicion8 = new Transicion();
		transicion8.setEstadoSalida("2");
		transicion8.setSimboloInput("c");
		transicion8.setEstadoLlegada("T");
		
		Transicion transicion9 = new Transicion();
		transicion9.setEstadoSalida("3");
		transicion9.setSimboloInput("a");
		transicion9.setEstadoLlegada("T");
		
		Transicion transicion10 = new Transicion();
		transicion10.setEstadoSalida("3");
		transicion10.setSimboloInput("c");
		transicion10.setEstadoLlegada("2");
		
		Transicion transicion11 = new Transicion();
		transicion11.setEstadoSalida("4");
		transicion11.setSimboloInput("b");
		transicion11.setEstadoLlegada("T");
		
		Transicion transicion12 = new Transicion();
		transicion12.setEstadoSalida("4");
		transicion12.setSimboloInput("c");
		transicion12.setEstadoLlegada("T");
		
		Transicion transicion13 = new Transicion();
		transicion13.setEstadoSalida("T");
		transicion13.setSimboloInput("a");
		transicion13.setEstadoLlegada("T");
		
		Transicion transicion14 = new Transicion();
		transicion14.setEstadoSalida("T");
		transicion14.setSimboloInput("b");
		transicion14.setEstadoLlegada("T");
		
		Transicion transicion15 = new Transicion();
		transicion15.setEstadoSalida("T");
		transicion15.setSimboloInput("c");
		transicion15.setEstadoLlegada("T");
		
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
		transiciones.add(transicion13);
		transiciones.add(transicion14);
		transiciones.add(transicion15);
		
		afd_check.setTransiciones(transiciones);
		
		afd_check.setEstadoInicial("1234");
		
		List<List<String>> estadosListado = new ArrayList<List<String>>();
		
		List<String> estado1 = new ArrayList<String>();
		estado1.add("1");
		estado1.add("2");
		estado1.add("3");
		estado1.add("4");
		
		List<String> estado2 = new ArrayList<String>();
		estado2.add("2");
		
		List<String> estado3 = new ArrayList<String>();
		estado3.add("3");
		
		List<String> estado4 = new ArrayList<String>();
		estado4.add("4");
		
		List<String> estado5 = new ArrayList<String>();
		estado5.add("T");
		
		estadosListado.add(estado1);
		estadosListado.add(estado2);
		estadosListado.add(estado3);
		estadosListado.add(estado4);
		estadosListado.add(estado5);
		
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
		
		e_afnd_check.setCantEstados(4);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("2");
		estadosFinales.add("3");
		estadosFinales.add("4");
		
		e_afnd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("1");
		transicion1.setSimboloInput("E");
		transicion1.setEstadoLlegada("2");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("1");
		transicion2.setSimboloInput("E");
		transicion2.setEstadoLlegada("3");
		
		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("1");
		transicion3.setSimboloInput("E");
		transicion3.setEstadoLlegada("4");
		
//		Transicion transicion4 = new Transicion();
//		transicion4.setEstadoSalida("2");
//		transicion4.setSimboloInput("a");
//		transicion4.setEstadoLlegada("3");
		
		/**
		 * Solo hago este pequeño cambio para probar la falla
		 **/
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("2");
		transicion4.setSimboloInput("a");
		transicion4.setEstadoLlegada("5");
		
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("3");
		transicion5.setSimboloInput("b");
		transicion5.setEstadoLlegada("4");
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("3");
		transicion6.setSimboloInput("c");
		transicion6.setEstadoLlegada("2");
		
		Transicion transicion7 = new Transicion();
		transicion7.setEstadoSalida("4");
		transicion7.setSimboloInput("a");
		transicion7.setEstadoLlegada("3");
		
		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		transiciones.add(transicion4);
		transiciones.add(transicion5);
		transiciones.add(transicion6);
		transiciones.add(transicion7);
		
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
		
		afd_check.setCantEstados(5);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("1234");
		estadosFinales.add("2");
		estadosFinales.add("3");
		estadosFinales.add("4");
		
		afd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("1234");
		transicion1.setSimboloInput("c");
		transicion1.setEstadoLlegada("2");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("1234");
		transicion2.setSimboloInput("a");
		transicion2.setEstadoLlegada("3");
		
		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("1234");
		transicion3.setSimboloInput("b");
		transicion3.setEstadoLlegada("4");
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("2");
		transicion4.setSimboloInput("a");
		transicion4.setEstadoLlegada("3");
		
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("3");
		transicion5.setSimboloInput("b");
		transicion5.setEstadoLlegada("4");
		
//		Transicion transicion6 = new Transicion();
//		transicion6.setEstadoSalida("4");
//		transicion6.setSimboloInput("a");
//		transicion6.setEstadoLlegada("3");
		
		/**
		 * Solo hago este pequeño cambio para probar la falla
		 **/
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("4");
		transicion6.setSimboloInput("a");
		transicion6.setEstadoLlegada("5");
		
		Transicion transicion7 = new Transicion();
		transicion7.setEstadoSalida("2");
		transicion7.setSimboloInput("b");
		transicion7.setEstadoLlegada("T");
		
		Transicion transicion8 = new Transicion();
		transicion8.setEstadoSalida("2");
		transicion8.setSimboloInput("c");
		transicion8.setEstadoLlegada("T");
		
		Transicion transicion9 = new Transicion();
		transicion9.setEstadoSalida("3");
		transicion9.setSimboloInput("a");
		transicion9.setEstadoLlegada("T");
		
		Transicion transicion10 = new Transicion();
		transicion10.setEstadoSalida("3");
		transicion10.setSimboloInput("c");
		transicion10.setEstadoLlegada("2");
		
		Transicion transicion11 = new Transicion();
		transicion11.setEstadoSalida("4");
		transicion11.setSimboloInput("b");
		transicion11.setEstadoLlegada("T");
		
		Transicion transicion12 = new Transicion();
		transicion12.setEstadoSalida("4");
		transicion12.setSimboloInput("c");
		transicion12.setEstadoLlegada("T");
		
		Transicion transicion13 = new Transicion();
		transicion13.setEstadoSalida("T");
		transicion13.setSimboloInput("a");
		transicion13.setEstadoLlegada("T");
		
		Transicion transicion14 = new Transicion();
		transicion14.setEstadoSalida("T");
		transicion14.setSimboloInput("b");
		transicion14.setEstadoLlegada("T");
		
		Transicion transicion15 = new Transicion();
		transicion15.setEstadoSalida("T");
		transicion15.setSimboloInput("c");
		transicion15.setEstadoLlegada("T");
		
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
		transiciones.add(transicion13);
		transiciones.add(transicion14);
		transiciones.add(transicion15);
		
		afd_check.setTransiciones(transiciones);
		
		afd_check.setEstadoInicial("1234");
		
		List<List<String>> estadosListado = new ArrayList<List<String>>();
		
		List<String> estado1 = new ArrayList<String>();
		estado1.add("1");
		estado1.add("2");
		estado1.add("3");
		estado1.add("4");
		
		List<String> estado2 = new ArrayList<String>();
		estado2.add("2");
		
		List<String> estado3 = new ArrayList<String>();
		estado3.add("3");
		
		List<String> estado4 = new ArrayList<String>();
		estado4.add("4");
		
		List<String> estado5 = new ArrayList<String>();
		estado5.add("T");
		
		estadosListado.add(estado1);
		estadosListado.add(estado2);
		estadosListado.add(estado3);
		estadosListado.add(estado4);
		estadosListado.add(estado5);
		
		afd_check.setEstadosListado(estadosListado);
		
		service.ordenarAutomata(afd_check);
		
		return afd_check;
	}
	
	
}
