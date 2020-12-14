package test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;
import gramatica.Produccion;

public class TestEjercicio1a_LimpiezaGramatica_1 {

	private static GramaticaService gramaticaService = new GramaticaService();
	private static Gramatica gramatica = new Gramatica();
	private static String file = "gramatica_limpiezaGramatica_1.txt";

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
	public void limpiezaGramatica1Test() throws FileNotFoundException, BadFileException {
		System.out.println(LINEA);
		System.out.println("Nueva gramática");
		
		gramatica.eliminarE();
		gramatica.eliminarProduccionesUnitarias();
		gramatica.eliminarSimbolosNoGeneradores();
		gramatica.eliminarSimbolosNoAlcanzables();
		gramaticaService.printGramatica(gramatica);
	
		assertTrue(assertTrueProd(gramatica,0,"S",new ArrayList<>(Arrays.asList('a','A'))));
		assertTrue(assertTrueProd(gramatica,1,"S",new ArrayList<>(Arrays.asList('a'))));
		assertTrue(assertTrueProd(gramatica,2,"S",new ArrayList<>(Arrays.asList('b'))));
		assertTrue(assertTrueProd(gramatica,3,"B",new ArrayList<>(Arrays.asList('b','B'))));
		assertTrue(assertTrueProd(gramatica,4,"A",new ArrayList<>(Arrays.asList('a'))));
		assertTrue(assertTrueProd(gramatica,5,"B",new ArrayList<>(Arrays.asList('b'))));
		assertTrue(assertTrueProd(gramatica,6,"A",new ArrayList<>(Arrays.asList('a','A'))));
		assertTrue(assertTrueProd(gramatica,7,"S",new ArrayList<>(Arrays.asList('b','B'))));
		assertTrue(assertTrueProd(gramatica,8,"S",new ArrayList<>(Arrays.asList('A','B'))));
	 }
	
	
	private boolean assertTrueProd(Gramatica gramatica, int index, String simbolo, List<Character> simbolos){
        return gramatica.getProducciones().get(index).equals
                (new Produccion(simbolo,simbolos));
    }
}