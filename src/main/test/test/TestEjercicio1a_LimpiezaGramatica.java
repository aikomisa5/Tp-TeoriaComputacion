package test;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;

public class TestEjercicio1a_LimpiezaGramatica {

	private static GramaticaService gramaticaService = new GramaticaService();
	private static Gramatica gramatica = new Gramatica();
	private static String file = "gramatica1_LimpiezaGramatica.txt";
	private static final String LINEA = "----------------------------------------";
	
	@BeforeClass
	public static void setup() throws FileNotFoundException, BadFileException {
		System.out.println(LINEA);
		System.out.println("*     Limpieza de Gramática             *");
		gramatica = gramaticaService.getGramaticaFromTxtFile(file);
		System.out.println(LINEA);
		System.out.println("TEST 1");
		gramaticaService.printGramatica(gramatica);
	}
	
	@Test
	public void limpiezaGramatica1Test() {
		System.out.println(LINEA);
		System.out.println("Nueva gramática");
		gramatica.eliminarE();
//		gramatica.eliminarProduccionesUnitarias();
		gramaticaService.printGramatica(gramatica);
//		gramatica.eliminarProduccionesUnitarias();
//		gramatica.eliminarSimbolosNoGeneradores();
//		gramatica.eliminarSimbolosNoAlcanzables();
		
	}
}