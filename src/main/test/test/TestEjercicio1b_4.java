package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.FileNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;
import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;

public class TestEjercicio1b_4 {
	private static GramaticaService gramaticaService = new GramaticaService();
	private static Gramatica gramatica = new Gramatica();
	private static String file = "gramatica_cyk4.txt";

	private static final String LA_GRAMATICA_GENERA_EL_STRING = "La gramatica genera el string ";
	private static final String LINEA = "----------------------------------------";

		
	@BeforeClass
	public static void setup() throws FileNotFoundException, BadFileException {
		gramatica = gramaticaService.getGramaticaFromTxtFile(file);
		System.out.println(LINEA);
		System.out.println("TEST 4");
		gramaticaService.printGramatica(gramatica);
	}
	
	@Test
	 public void CYK4aabbbTest(){
	 
		 String w = "aabbb";
		 boolean resultado = gramaticaService.algoritmoCYK(gramatica, w);
		System.out.println(LA_GRAMATICA_GENERA_EL_STRING + w  +" ? : "+ resultado);
		assertTrue(resultado);
	} 
	
	@Test
	public void CYK4ccTest(){
	 
		 String w = "cc";
		 boolean resultado = gramaticaService.algoritmoCYK(gramatica, w);
		 System.out.println(LA_GRAMATICA_GENERA_EL_STRING + w  +" ? : "+ resultado);
		 assertFalse(resultado);
	} 
	
	@Test
	public void CYK4BBBBBTest(){
	 
		 String w = "BBBBB";
		 boolean resultado = gramaticaService.algoritmoCYK(gramatica, w);
		 System.out.println(LA_GRAMATICA_GENERA_EL_STRING + w  +" ? : "+ resultado);
		 assertFalse(resultado);
	} 
	
	@Test
	public void CYK4123123Test(){
	 
		 String w = "123123";
		 boolean resultado = gramaticaService.algoritmoCYK(gramatica, w);
		 System.out.println(LA_GRAMATICA_GENERA_EL_STRING + w  +" ? : "+ resultado);
		 assertFalse(resultado);
	} 
}