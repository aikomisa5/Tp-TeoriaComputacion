package test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.Test;

import automata.Automata;
import automata.AutomataService;
import exceptions.BadFileException;

@TestInstance(Lifecycle.PER_CLASS)
class TestEjercicio2b_1 {
	
	AutomataService automataService = new AutomataService();
	
	private Automata e_afnd = new Automata();
	private Automata afd = new Automata();

	@BeforeAll
	public void setup() throws FileNotFoundException, BadFileException {
		//TODO la idea para mi seria usar esto.. el tema es que como esta definido el metodo procesar (por el profe) no se puede usarlo..
		e_afnd = automataService.getAutomataFromTxtFile();
		afd = automataService.getAFD(e_afnd.getProyecciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());
	}
	
	@Test
	public void testProcesarCadenaA() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean aaaaaaaaaa = automataService.procesar("aaaaaaaaaa");
		System.out.println("aaaaaaaaaa pertenece? : " + aaaaaaaaaa);

		assertTrue(aaaaaaaaaa);
	}
	
	@Test
	public void testProcesarCadenaC() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean cccccccc = automataService.procesar("cccccccc");
		System.out.println("cccccccc pertenece? : " + cccccccc);
		
		assertTrue(cccccccc);
	}
	
	@Test
	public void testProcesarCadenaABA() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean aba = automataService.procesar("aba");
		System.out.println("aba pertenece? : " + aba);
		
		assertTrue(aba);
	}
	
	@Test
	public void testProcesarCadenaABABA() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean ababa = automataService.procesar("ababa");
		System.out.println("ababa pertenece? : " + ababa);
		
		assertTrue(ababa);
	}
	
	@Test
	public void testProcesarCadenaABCABC() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean abcabc = automataService.procesar("abcabc");
		System.out.println("abcabc pertenece? : " + abcabc);
		
		assertTrue(abcabc);
	}
	
	@Test
	public void testProcesarCadenaBB() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean bb = automataService.procesar("bb");
		System.out.println("bb pertenece? : " + bb);
		
		assertTrue(bb);
	}
	
	@Test
	public void testProcesarCadenaBBB() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean bbb = automataService.procesar("bbb");
		System.out.println("bbb pertenece? : " + bbb);
		
		assertFalse(bbb);
	}
	
	@Test
	public void testProcesarCadenaAB() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");

		boolean ab = automataService.procesar("ab");
		System.out.println("ab pertenece? : " + ab);
		
		assertTrue(ab);
	}
}
