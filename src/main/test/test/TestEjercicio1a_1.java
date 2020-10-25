package test;

import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;
import gramatica.Produccion;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestEjercicio1a_1 {
	
	GramaticaService gramaticaService = new GramaticaService();


	@org.junit.jupiter.api.Test
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

	@org.junit.jupiter.api.Test
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



	@org.junit.jupiter.api.Test
	public void testGramatica_1_EliminarE() throws FileNotFoundException, BadFileException {

		System.out.println("--------------------------");
		System.out.println("testGramatica_1_EliminarE");
		System.out.println("--------------------------");

		Gramatica gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_1.txt");


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

	/*@org.junit.jupiter.api.Test
	public void testGramatica_1_EliminarProdUnitarias() throws FileNotFoundException, BadFileException {

		Gramatica gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_1.txt");

		gramatica.eliminarProdUnitarias();

		boolean result = true;

		for (Produccion p : gramatica.getProducciones())
			for (Character c : p.getSimbolos() )
				if(c.equals('E'))
					result = false;

		assertTrue(result);
	}

	@org.junit.jupiter.api.Test
	public void testGramatica_1_EliminarSimbolosNoGeneradores() throws FileNotFoundException, BadFileException {

		Gramatica gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_1.txt");

		gramatica.eliminarProdUnitarias();

		boolean result = true;

		for (Produccion p : gramatica.getProducciones())
			for (Character c : p.getSimbolos() )
				if(c.equals('E'))
					result = false;

		assertTrue(result);
	}

	@org.junit.jupiter.api.Test
	public void testGramatica_1_EliminarSimbolosNoAlcanzables() throws FileNotFoundException, BadFileException {

		Gramatica gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_1.txt");

		gramatica.eliminarSimbolosNoAlcanzables();

		boolean result = true;

		for (Produccion p : gramatica.getProducciones())
			for (Character c : p.getSimbolos() )
				if(c.equals('E'))
					result = false;

		assertTrue(result);
	}
	*/
}
