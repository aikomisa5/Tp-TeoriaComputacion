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

public class TestEjercicio2b_3 {
	
	private static final String LINEA = "----------------------------------------";
	private static final String PERTENECE_AL_AFD= " pertenece al AFD? : ";

	private static AutomataService service = new AutomataService();

	private static Automata e_afnd = new Automata();
	private static Automata afd = new Automata();
	private static String fileName = "automata_3.txt";

	@BeforeClass
	public static void setup() throws FileNotFoundException, BadFileException {
		e_afnd = service.getAFNDFromTxtFile(fileName);
		afd = service.getAFD(e_afnd.getTransiciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());
		System.out.println(LINEA);
		System.out.println("TEST 3");
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
	public void testProcesarCadenaCA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ca";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaACA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aca";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBAB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bab";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCAC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cac";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaACAB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "acab";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBABA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "baba";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCAB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cab";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaACABABABABA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "acababababa";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBABACABA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "babacaba";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCABACABA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cabacaba";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaACABABABABAB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "acababababab";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaBABACABAB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "babacabab";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaCABACABAC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cabacabac";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertTrue(resultado);
	}
	
	@Test
	public void testProcesarCadenaAA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aa";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaAAA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "aaa";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaBB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaBBB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bbb";
		
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
	public void testProcesarCadenaBCC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bcc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaCB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaCBB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cbb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaCC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "cc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaCCC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "ccc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaACB() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "acb";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaBACC() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "bacc";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
	
	@Test
	public void testProcesarCadenaCAABA() throws FileNotFoundException, BadFileException, BadAutomataException {
		
		String w = "caaba";
		
		boolean resultado = afd.procesar(w);
		System.out.println(w + PERTENECE_AL_AFD + resultado);
		
		assertFalse(resultado);
	}
}

