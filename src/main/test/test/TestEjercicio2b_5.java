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

public class TestEjercicio2b_5 {
	
	private static final String LINEA = "----------------------------------------";
	private static final String PERTENECE_AL_AFD= " pertenece al AFD? : ";

	private static AutomataService service = new AutomataService();

	private static Automata e_afnd = new Automata();
	private static Automata afd = new Automata();
	private static String fileName = "automata_5.txt";

	@BeforeClass
	public static void setup() throws FileNotFoundException, BadFileException {
		e_afnd = service.getAFNDFromTxtFile(fileName);
		afd = service.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());
		System.out.println(LINEA);
		System.out.println("TEST 5");
		System.out.println(LINEA);
		service.printAutomata(afd, "AFD");
	}
	
	@Test
	public void testProcesarCadenaB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "b";
		
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
	public void testProcesarCadenaCB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ba";
		
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
	public void testProcesarCadenaBBCAB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bbcab";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBBCCB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bbccb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBBCB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bbcb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAAAB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aaab";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCCCB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ccccb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAAAAABAAAA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aaaaabaaaa";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCCCCCBAAAAA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ccccccbaaaaa";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCCCCBBBBBBAAAAB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cccccbbbbbbaaaab";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAAAABBBBAAAAABBBBBCAAAB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aaaabbbbaaaaabbbbbcaaab";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaVacia() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "";
		
		boolean resultado = afd.procesar(w);
		System.out.println("El vacio" + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "a";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "c";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaBC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaBAC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bac";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaAAABC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aaabc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCCCBC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ccccbc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
}

