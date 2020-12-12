package test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Test;

import automata.Automata;
import exceptions.BadFileException;

public class TestEjercicio2_Fails extends TestAutomataBase{
	
	private Automata e_afnd = null;

	String fileName1 = "automata_fail_1_empty.txt";
	String fileName2 = "automata_fail_2_bad_simbolos_input.txt";
	String fileName3 = "automata_fail_3_bad_cantidad_estados.txt";
	String fileName4 = "automata_fail_4_bad_estados_finales.txt";
	String fileName5 = "automata_fail_5_bad_transicion.txt";
	
	@Test
	public void testAutomataEmptyFile() throws FileNotFoundException, BadFileException {
		
		boolean testFallo = false;
		
		System.out.println(LINEA);
		System.out.println("TEST - Fail - Empty File");
		System.out.println(LINEA);
		
		try {
			e_afnd = service.getAFNDFromTxtFile(fileName1);
			
			if (e_afnd != null) {
				System.out.println("Error en el test. Se obtuvieron datos del automata cuando no deberia");
				testFallo = false;
			}			
		}
		catch(Exception e) {
			testFallo = true;
		}
		
		assertTrue(testFallo);
	}
	
	@Test
	public void testAutomataBadSimbolosInput() throws FileNotFoundException, BadFileException {
		
		boolean testFallo = false;
		
		System.out.println(LINEA);
		System.out.println("TEST - Fail - Bad Simbolos Input");
		System.out.println(LINEA);
		
		try {
			e_afnd = service.getAFNDFromTxtFile(fileName2);
			
			if (e_afnd != null && e_afnd.getSimbolosInput() != null && e_afnd.getSimbolosInput().size() != 0) {
				System.out.println("Error en el test. Se obtuvo el dato de simbolos input cuando no deberia");
				testFallo = false;
			}
		}
		catch(Exception e) {
			testFallo = true;
		}
		
		assertTrue(testFallo);
	}
	
	@Test
	public void testAutomataBadCantidadEstados() throws FileNotFoundException, BadFileException {
		
		boolean testFallo = false;
		
		System.out.println(LINEA);
		System.out.println("TEST - Fail - Bad Cantidad Estados");
		System.out.println(LINEA);
		
		try {
			e_afnd = service.getAFNDFromTxtFile(fileName3);
			
			if (e_afnd != null && e_afnd.getCantEstados() != 0) {
				System.out.println("Error en el test. Se obtuvo el dato cantidad de estados cuando no deberia");
				testFallo = false;
			}
		}
		catch(Exception e) {
			testFallo = true;
		}
		
		assertTrue(testFallo);
	}
	
	@Test
	public void testAutomataBadEstadosFinales() throws FileNotFoundException, BadFileException {
		
		boolean testFallo = false;
		
		System.out.println(LINEA);
		System.out.println("TEST - Fail - Bad Estados Finales");
		System.out.println(LINEA);
		
		try {
			e_afnd = service.getAFNDFromTxtFile(fileName4);
			
			if (e_afnd != null && e_afnd.getEstadosFinales() != null && e_afnd.getEstadosFinales().size() != 0) {
				System.out.println("Error en el test. Se obtuvo el dato de estados finales cuando no deberia");
				testFallo = false;
			}
		}
		catch(Exception e) {
			testFallo = true;
		}
		
		assertTrue(testFallo);
	}

	@Test
	public void testAutomataBadTransiciones() throws FileNotFoundException, BadFileException {

		boolean testFallo = false;
		
		System.out.println(LINEA);
		System.out.println("TEST - Fail - Bad Transiciones");
		System.out.println(LINEA);
		
		try {
			e_afnd = service.getAFNDFromTxtFile(fileName5);
			
			if (e_afnd != null && e_afnd.getTransiciones() != null && e_afnd.getTransiciones().size() != 0) {
				System.out.println("Error en el test. Se obtuvo el dato de transiciones cuando no deberia");
				testFallo = false;
			}
		}
		catch(Exception e) {
			testFallo = true;
		}
		
		assertTrue(testFallo);
	}
}
