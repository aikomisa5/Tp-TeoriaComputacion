package test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import automata.Automata;
import automata.AutomataService;
import exceptions.BadFileException;

class Test {
	
	AutomataService automataService = new AutomataService();
	
	Automata e_afnd = new Automata();
	Automata afd = new Automata();
	String nombreAutomata = "automata.txt";

	@org.junit.jupiter.api.Test
	public void testAutomata() throws FileNotFoundException, BadFileException {

		e_afnd = automataService.getAutomataFromTxtFile(nombreAutomata);
		afd = automataService.getAFD(e_afnd.getProyecciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());

		automataService.printAutomata(e_afnd, "E-AFND");
		automataService.printAutomata(afd, "AFD");

		//TODO chequear el resultado final para definir por qu� esto deberia dar exito
		assertTrue(true);
	}
	
	@org.junit.jupiter.api.Test
	public void testProcesarCadenaA() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean aaaaaaaaaa = automataService.procesar("aaaaaaaaaa",nombreAutomata);
		System.out.println("aaaaaaaaaa pertenece? : " + aaaaaaaaaa);

		assertTrue(aaaaaaaaaa);
	}
	
	@org.junit.jupiter.api.Test
	public void testProcesarCadenaC() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean cccccccc = automataService.procesar("cccccccc",nombreAutomata);
		System.out.println("cccccccc pertenece? : " + cccccccc);
		
		assertTrue(cccccccc);
	}
	
	@org.junit.jupiter.api.Test
	public void testProcesarCadenaABA() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean aba = automataService.procesar("aba",nombreAutomata);
		System.out.println("aba pertenece? : " + aba);
		
		assertTrue(aba);
	}
	
	@org.junit.jupiter.api.Test
	public void testProcesarCadenaABABA() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean ababa = automataService.procesar("ababa",nombreAutomata);
		System.out.println("ababa pertenece? : " + ababa);
		
		assertTrue(ababa);
	}
	
	@org.junit.jupiter.api.Test
	public void testProcesarCadenaABCABC() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean abcabc = automataService.procesar("abcabc",nombreAutomata);
		System.out.println("abcabc pertenece? : " + abcabc);
		
		assertTrue(abcabc);
	}
	
	@org.junit.jupiter.api.Test
	public void testProcesarCadenaBB() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean bb = automataService.procesar("bb",nombreAutomata);
		System.out.println("bb pertenece? : " + bb);
		
		assertTrue(bb);
	}
	
	@org.junit.jupiter.api.Test
	public void testProcesarCadenaBBB() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean bbb = automataService.procesar("bbb",nombreAutomata);
		System.out.println("bbb pertenece? : " + bbb);
		
		assertFalse(bbb);
	}
	
	@org.junit.jupiter.api.Test
	public void testProcesarCadenaAB() throws FileNotFoundException, BadFileException {
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");

		boolean ab = automataService.procesar("ab",nombreAutomata);
		System.out.println("ab pertenece? : " + ab);
		
		assertTrue(ab);
	}
}
