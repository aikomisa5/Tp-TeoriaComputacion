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

public class TestEjercicio2b_2 {
	
	private static final String LINEA = "----------------------------------------";
	private static final String PERTENECE_AL_AFD= " pertenece al AFD? : ";

	private static AutomataService service = new AutomataService();

	private static Automata e_afnd = new Automata();
	private static Automata afd = new Automata();
	private static String fileName = "automata_2.txt";

	@BeforeClass
	public static void setup() throws FileNotFoundException, BadFileException {
		e_afnd = service.getAFNDFromTxtFile(fileName);
		afd = service.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());
		System.out.println(LINEA);
		System.out.println("TEST 2b_2");
		System.out.println(LINEA);
		service.printAutomata(afd, "AFD");
	}
	
	@Test
	public void testProcesarCadenaVacia() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "";
		
		boolean resultado = afd.procesar(w);
		System.out.println("El vacio" + PERTENECE_AL_AFD + resultado);

		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "a";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "b";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "c";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aa";
		
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
	public void testProcesarCadenaCC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cc";
		
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
	public void testProcesarCadenaAC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ac";
		
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
	public void testProcesarCadenaBC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ca";
		
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
	public void testProcesarCadenaABC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "abc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaACC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "acc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaABB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "abb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBAA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "baa";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCAA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "caa";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCBC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cbc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ccc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAAABBACA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aaabbaca";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAAAABBBBCA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aaaabbbbca";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAAABBBBCCCCAAA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aaabbbbccccaaa";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBBBBAAAACCCC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bbbbaaaacccc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBBBBAAAABBBCCCAAA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bbbbaaaabbbcccaaa";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBBBBAAABBBCCCCAAC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bbbbaaabbbccccaac";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCCCCAAAAABBBBB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cccccaaaaabbbbb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCCCCCBBAAAAB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ccccccbbaaaab";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCCCCCAAABBBAACC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ccccccaaabbbaacc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCCCCCABAAAACCCA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ccccccabaaaaccca";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAZ() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "az";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaBX() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bx";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaCY() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cy";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
}
