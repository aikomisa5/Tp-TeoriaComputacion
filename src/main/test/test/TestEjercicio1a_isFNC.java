package test;

import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class TestEjercicio1a_isFNC {

	GramaticaService gramaticaService = new GramaticaService();

	@Test
	public void isInFnc1Test() throws FileNotFoundException, BadFileException
	{
		boolean result = true;
		Gramatica gramatica = null;

		gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_fnctest_1.txt");

		assertTrue(result);
		assertTrue(gramatica.isInFNC());
	}

	@Test
	public void isInFnc2Test() throws FileNotFoundException, BadFileException
	{
		boolean result = true;
		Gramatica gramatica = null;

		gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_fnctest_2.txt");

		assertTrue(result);
		assertTrue(gramatica.isInFNC());
	}

	@Test
	public void isInFnc3Test() throws FileNotFoundException, BadFileException
	{
		boolean result = true;
		Gramatica gramatica = null;

		gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_fnctest_3.txt");

		assertTrue(result);
		assertTrue(gramatica.isInFNC());
	}

	@Test
	public void isNotInFnc1Test() throws FileNotFoundException, BadFileException
	{
		boolean result = true;
		Gramatica gramatica = null;

		gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_isnotfnctest_1.txt");

		assertTrue(result);
		assertFalse(gramatica.isInFNC());
	}

	@Test
	public void isNotInFnc2Test() throws FileNotFoundException, BadFileException
	{
		boolean result = true;
		Gramatica gramatica = null;

		gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_isnotfnctest_2.txt");

		assertTrue(result);
		assertFalse(gramatica.isInFNC());
	}
}
