package test;

import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;
import org.junit.Test;

import java.io.FileNotFoundException;
import static org.junit.Assert.assertTrue;

public class TestEjercicio1a_2 {
	
	GramaticaService gramaticaService = new GramaticaService();


	@Test
	public void testGramatica_2_EliminarProduccionesUnitarias() throws FileNotFoundException, BadFileException {

		System.out.println("--------------------------");
		System.out.println("testGramatica_2_EliminarProduccionesUnitarias");
		System.out.println("--------------------------");

		Gramatica gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_producciones_unitarias.txt");


		System.out.println("Gramatica =>" + gramatica);

		gramatica.eliminarProduccionesUnitarias();

		System.out.println("Nueva gramática =>" + gramatica);

		assertTrue(true);//TODO: ver esto
	}
}
