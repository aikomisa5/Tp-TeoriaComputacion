package test;

import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;
import gramatica.Produccion;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestEjercicio1a_1 {
	
	GramaticaService gramaticaService = new GramaticaService();


	@Test
	public void testGramatica_0_NoMatchea() throws FileNotFoundException, BadFileException {

		System.out.println("--------------------------");
		System.out.println("testGramatica_0_NoMatchea");
		System.out.println("--------------------------");

		boolean result = true;

		try {
			gramaticaService.getGramaticaFromTxtFile("gramatica_0.txt");
		}catch (FileNotFoundException e){
			result = false;
		}catch (BadFileException e2){
			result = false;
		}

		assertFalse(result);
	}

	@Test
	public void testGramatica_1() throws FileNotFoundException, BadFileException {

		System.out.println("--------------------------");
		System.out.println("testGramatica_1");
		System.out.println("--------------------------");

		Gramatica gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_1.txt");
		boolean result = true;

		ArrayList<Character> simbolos = new ArrayList<>();
		simbolos.add('A');
		simbolos.add('B');
		simbolos.add('C');

		if(gramatica.getProducciones().size() != 4)
			result = false;

		else if (!gramatica.getProducciones().get(0).equals(new Produccion("S",simbolos)))
			result = false;

		assertTrue(result);
	}



	@Test
	public void testGramatica_2_EliminarE() throws FileNotFoundException, BadFileException {

		System.out.println("--------------------------");
		System.out.println("testGramatica_2_EliminarE");
		System.out.println("--------------------------");

		Gramatica gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_2.txt");

		System.out.println("Gramatica =>" + gramatica);

		gramatica.eliminarE();

		boolean result = true;

		for (Produccion p : gramatica.getProducciones()) {
			for (Character c : p.getSimbolos())
				if (c.equals('E'))
					result = false;
		}

		System.out.println("Nueva gramática =>" + gramatica);

		assertTrue(result);
	}

	@Test
	public void testGramatica_2_EliminarProduccionesUnitarias() throws FileNotFoundException, BadFileException {

		/*System.out.println("--------------------------");
		System.out.println("testGramatica_2_EliminarProduccionesUnitarias");
		System.out.println("--------------------------");

		Gramatica gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_2.txt");


		System.out.println("Gramatica =>" + gramatica);

		gramatica.eliminarProduccionesUnitarias();

		boolean result = true;

		for (Produccion p : gramatica.getProducciones()) {
			for (Character c : p.getSimbolos())
				if (c.equals('E'))
					result = false;
		}

		System.out.println("Nueva gramática =>" + gramatica);*/

		//assertTrue(result);
	}
}
