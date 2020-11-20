package test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import automata.Automata;
import automata.Transicion;
import exceptions.BadFileException;

public class TestEjercicio2a_2 extends TestAutomataBase{
	
	private Automata e_afnd = new Automata();
	private Automata afd = new Automata();

	String fileName = "automata_2.txt";

	@Test
	public void testAutomata() throws FileNotFoundException, BadFileException {

		boolean testExitoso = true;
		
		e_afnd = service.getAFNDFromTxtFile(fileName);
		afd = service.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());

		System.out.println(LINEA);
		System.out.println("TEST 2");
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
		transicion5.setEstadoSalida("2");
		transicion5.setSimboloInput("b");
		transicion5.setEstadoLlegada("3");
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("2");
		transicion6.setSimboloInput("c");
		transicion6.setEstadoLlegada("3");
		
		Transicion transicion7 = new Transicion();
		transicion7.setEstadoSalida("3");
		transicion7.setSimboloInput("a");
		transicion7.setEstadoLlegada("2");
		
		Transicion transicion8 = new Transicion();
		transicion8.setEstadoSalida("3");
		transicion8.setSimboloInput("b");
		transicion8.setEstadoLlegada("2");
		
		Transicion transicion9 = new Transicion();
		transicion9.setEstadoSalida("3");
		transicion9.setSimboloInput("c");
		transicion9.setEstadoLlegada("2");
		
		Transicion transicion10 = new Transicion();
		transicion10.setEstadoSalida("3");
		transicion10.setSimboloInput("a");
		transicion10.setEstadoLlegada("4");
		
		Transicion transicion11 = new Transicion();
		transicion11.setEstadoSalida("3");
		transicion11.setSimboloInput("b");
		transicion11.setEstadoLlegada("4");
		
		Transicion transicion12 = new Transicion();
		transicion12.setEstadoSalida("3");
		transicion12.setSimboloInput("c");
		transicion12.setEstadoLlegada("4");
		
		Transicion transicion13 = new Transicion();
		transicion13.setEstadoSalida("4");
		transicion13.setSimboloInput("a");
		transicion13.setEstadoLlegada("3");
		
		Transicion transicion14 = new Transicion();
		transicion14.setEstadoSalida("4");
		transicion14.setSimboloInput("b");
		transicion14.setEstadoLlegada("3");
		
		Transicion transicion15 = new Transicion();
		transicion15.setEstadoSalida("4");
		transicion15.setSimboloInput("c");
		transicion15.setEstadoLlegada("3");
		
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
		
		afd_check.setCantEstados(2);
		
		List<String> estadosFinales = new ArrayList<String>();
		estadosFinales.add("1234");
		estadosFinales.add("234");
		
		afd_check.setEstadosFinales(estadosFinales);
		
		Transicion transicion1 = new Transicion();
		transicion1.setEstadoSalida("1234");
		transicion1.setSimboloInput("a");
		transicion1.setEstadoLlegada("234");
		
		Transicion transicion2 = new Transicion();
		transicion2.setEstadoSalida("1234");
		transicion2.setSimboloInput("b");
		transicion2.setEstadoLlegada("234");
		
		Transicion transicion3 = new Transicion();
		transicion3.setEstadoSalida("1234");
		transicion3.setSimboloInput("c");
		transicion3.setEstadoLlegada("234");
		
		Transicion transicion4 = new Transicion();
		transicion4.setEstadoSalida("234");
		transicion4.setSimboloInput("a");
		transicion4.setEstadoLlegada("234");
		
		Transicion transicion5 = new Transicion();
		transicion5.setEstadoSalida("234");
		transicion5.setSimboloInput("b");
		transicion5.setEstadoLlegada("234");
		
		Transicion transicion6 = new Transicion();
		transicion6.setEstadoSalida("234");
		transicion6.setSimboloInput("c");
		transicion6.setEstadoLlegada("234");
		
		List<Transicion> transiciones = new ArrayList<Transicion>();
		transiciones.add(transicion1);
		transiciones.add(transicion2);
		transiciones.add(transicion3);
		transiciones.add(transicion4);
		transiciones.add(transicion5);
		transiciones.add(transicion6);
		
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
		estado2.add("3");
		estado2.add("4");
		
		estadosListado.add(estado1);
		estadosListado.add(estado2);
		
		afd_check.setEstadosListado(estadosListado);
		
		service.ordenarAutomata(afd_check);
		
		return afd_check;
	}
}
