package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import automata.Automata;
import automata.AutomataService;
import exceptions.BadAutomataException;
import exceptions.BadFileException;

public class TestEjercicio2b_1 {
	
	private static final String LINEA = "----------------------------------------";
	private static final String PROCESAR_TEST = "Procesar Test";
	private static final String PERTENECE_AL_AFD= " pertenece al AFD? : ";

	AutomataService automataService = new AutomataService();

	Automata e_afnd = new Automata();
	Automata afd = new Automata();
	String fileName = "automata_1.txt";

	@Before
	public void setup() throws FileNotFoundException, BadFileException {
		e_afnd = automataService.getAFNDFromTxtFile(fileName);
		afd = automataService.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());

	}
	
	@Test
	public void testProcesarCadenaAAAAAAAAAA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aaaaaaaaaa";
		
		System.out.println(LINEA);
		System.out.println(PROCESAR_TEST);
		System.out.println(LINEA);
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);

		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bb";
		
		System.out.println(LINEA);
		System.out.println(PROCESAR_TEST);
		System.out.println(LINEA);
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBBB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bbb";
		
		System.out.println(LINEA);
		System.out.println(PROCESAR_TEST);
		System.out.println(LINEA);
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCCCCCCC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cccccccc";
		
		System.out.println(LINEA);
		System.out.println(PROCESAR_TEST);
		System.out.println(LINEA);
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ab";
		
		System.out.println(LINEA);
		System.out.println(PROCESAR_TEST);
		System.out.println(LINEA);

		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaABA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aba";
		
		System.out.println(LINEA);
		System.out.println(PROCESAR_TEST);
		System.out.println(LINEA);
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaABABA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ababa";
		
		System.out.println(LINEA);
		System.out.println(PROCESAR_TEST);
		System.out.println(LINEA);
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaABCABC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "abcabc";
		
		System.out.println(LINEA);
		System.out.println(PROCESAR_TEST);
		System.out.println(LINEA);
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
}
