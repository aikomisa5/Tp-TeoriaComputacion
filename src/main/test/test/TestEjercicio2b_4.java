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

public class TestEjercicio2b_4 {
	
	private static final String LINEA = "----------------------------------------";
	private static final String PERTENECE_AL_AFD= " pertenece al AFD? : ";

	private static AutomataService service = new AutomataService();

	private static Automata e_afnd = new Automata();
	private static Automata afd = new Automata();
	private static String fileName = "automata_4.txt";

	@BeforeClass
	public static void setup() throws FileNotFoundException, BadFileException {
		e_afnd = service.getAFNDFromTxtFile(fileName);
		afd = service.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());
		System.out.println(LINEA);
		System.out.println("TEST 2b_4");
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
	public void testProcesarCadenaABC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "abc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBAC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bac";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCBA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cba";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAACCBB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aaccbb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAABBCC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aabbcc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCAABB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ccaabb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaACBCABCABA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "acbcabcaba";
		
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

