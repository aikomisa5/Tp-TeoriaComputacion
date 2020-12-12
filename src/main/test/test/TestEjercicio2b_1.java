package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;

import automata.Automata;
import automata.AutomataService;
import exceptions.BadAutomataException;
import exceptions.BadFileException;

public class TestEjercicio2b_1 {
	
	private static final String LINEA = "----------------------------------------";
	private static final String PERTENECE_AL_AFD= " pertenece al AFD? : ";

	private static AutomataService service = new AutomataService();

	private static Automata e_afnd = new Automata();
	private static Automata afd = new Automata();
	private static String fileName = "automata_1.txt";

	@BeforeClass
	public static void setup() throws FileNotFoundException, BadFileException {
		e_afnd = service.getAFNDFromTxtFile(fileName);
		afd = service.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());
		System.out.println(LINEA);
		System.out.println("TEST 1");
		System.out.println(LINEA);
		service.printAutomata(afd, "AFD");
	}
	
	@Test
	public void testProcesarCadenaAAAAAAAAAA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aaaaaaaaaa";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);

		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBBB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bbb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCCCCCCC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cccccccc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ab";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaABA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aba";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaABABA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ababa";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaABCABC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "abcabc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
}
