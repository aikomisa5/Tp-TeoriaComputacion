package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import automata.Automata;
import automata.AutomataService;
import automata.Transicion;
import exceptions.BadFileException;
import exceptions.BadTransicionException;

public class TestEjercicio2_Fails extends TestAutomataBase{

	String fileName1 = "automata_fail_1_empty.txt";
	String fileName2 = "automata_fail_2_bad_simbolos_input.txt";
	String fileName3 = "automata_fail_3_bad_cantidad_estados.txt";
	String fileName4 = "automata_fail_4_bad_estados_finales.txt";
	String fileName5 = "automata_fail_5_bad_transicion.txt";
	String fileNameNotFound = "automata_fail_file_not_found.txt";

	@Test(expected = BadFileException.class)
	public void testAutomataEmptyFile() throws FileNotFoundException, BadFileException {

		Automata afnd = new Automata();
		Automata afnd2 = new Automata();

		System.out.println(LINEA);
		System.out.println("TEST - Fail - Empty File");
		System.out.println(LINEA);

		try {
			afnd = service.getAFNDFromTxtFile(fileName1);
		}
		catch(BadFileException e) {
			assertFalse(e.getClass().getSimpleName().equals(FileNotFoundException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(NullPointerException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(IOException.class.getSimpleName()));
			assertTrue(e.getClass().getSimpleName().equals(BadFileException.class.getSimpleName()));
			assertFalse(fileName1.isEmpty());
			assertFalse(afnd.getCantEstados()>0);
			assertTrue(afnd.getSimbolosInput().isEmpty());
			assertTrue(afnd.getEstadosFinales().isEmpty());
			assertTrue(afnd.getTransiciones().isEmpty());
			assertTrue(afnd.getEstados().isEmpty());
			assertTrue(afnd.getEstadosListado().isEmpty());
			assertTrue(afnd.getEstadoInicial().isEmpty());
			assertTrue(afnd.equals(afnd2));
			assertTrue(e.getLocalizedMessage().equals("Ocurrio un error al intentar obtener los datos del automata del archivo, debido a que el archivo esta vacio"));
			throw e;
		}
	}

	@Test(expected = FileNotFoundException.class)
	public void testAutomataFileNotFound() throws FileNotFoundException, BadFileException {

		Automata afnd = new Automata();
		Automata afnd2 = new Automata();

		System.out.println(LINEA);
		System.out.println("TEST - Fail - File Not Found");
		System.out.println(LINEA);

		try {
			afnd = service.getAFNDFromTxtFile(fileNameNotFound);
		}
		catch(FileNotFoundException e) {
			assertTrue(e.getClass().getSimpleName().equals(FileNotFoundException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(NullPointerException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(IOException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(BadFileException.class.getSimpleName()));
			assertFalse(fileNameNotFound.isEmpty());
			assertFalse(afnd.getCantEstados()>0);
			assertTrue(afnd.getSimbolosInput().isEmpty());
			assertTrue(afnd.getEstadosFinales().isEmpty());
			assertTrue(afnd.getTransiciones().isEmpty());
			assertTrue(afnd.getEstados().isEmpty());
			assertTrue(afnd.getEstadosListado().isEmpty());
			assertTrue(afnd.getEstadoInicial().isEmpty());
			assertTrue(afnd.equals(afnd2));
			assertTrue(e.getLocalizedMessage().equals("src\\main\\resources\\automata_fail_file_not_found.txt (El sistema no puede encontrar el archivo especificado)"));
			throw e;
		}

	}

	@Test(expected = BadFileException.class)
	public void testAutomataBadSimbolosInput() throws FileNotFoundException, BadFileException {

		Automata afnd = new Automata();
		Automata afnd2 = new Automata();

		System.out.println(LINEA);
		System.out.println("TEST - Fail - Bad Simbolos Input");
		System.out.println(LINEA);

		try {
			afnd = service.getAFNDFromTxtFile(fileName2);
		}
		catch(BadFileException e) {
			assertFalse(e.getClass().getSimpleName().equals(FileNotFoundException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(NullPointerException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(IOException.class.getSimpleName()));
			assertTrue(e.getClass().getSimpleName().equals(BadFileException.class.getSimpleName()));
			assertFalse(fileName2.isEmpty());
			assertFalse(afnd.getCantEstados()>0);
			assertTrue(afnd.getSimbolosInput().isEmpty());
			assertTrue(afnd.getEstadosFinales().isEmpty());
			assertTrue(afnd.getTransiciones().isEmpty());
			assertTrue(afnd.getEstados().isEmpty());
			assertTrue(afnd.getEstadosListado().isEmpty());
			assertTrue(afnd.getEstadoInicial().isEmpty());
			assertTrue(afnd.equals(afnd2));
			assertTrue(e.getLocalizedMessage().contains(AutomataService.formatoSimbolosInput));
			throw e;
		}
	}

	@Test(expected = BadFileException.class)
	public void testAutomataBadCantidadEstados() throws FileNotFoundException, BadFileException {

		Automata afnd = new Automata();
		Automata afnd2 = new Automata();

		System.out.println(LINEA);
		System.out.println("TEST - Fail - Bad Cantidad Estados");
		System.out.println(LINEA);

		try {
			afnd = service.getAFNDFromTxtFile(fileName3);
		}
		catch(BadFileException e) {
			assertFalse(e.getClass().getSimpleName().equals(FileNotFoundException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(NullPointerException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(IOException.class.getSimpleName()));
			assertTrue(e.getClass().getSimpleName().equals(BadFileException.class.getSimpleName()));
			assertFalse(fileName3.isEmpty());
			assertFalse(afnd.getCantEstados()>0);
			assertTrue(afnd.getSimbolosInput().isEmpty());
			assertTrue(afnd.getEstadosFinales().isEmpty());
			assertTrue(afnd.getTransiciones().isEmpty());
			assertTrue(afnd.getEstados().isEmpty());
			assertTrue(afnd.getEstadosListado().isEmpty());
			assertTrue(afnd.getEstadoInicial().isEmpty());
			assertTrue(afnd.equals(afnd2));
			assertTrue(e.getLocalizedMessage().contains(AutomataService.ERROR_CANT_ESTADOS));
			throw e;
		}
	}

	@Test(expected = BadFileException.class)
	public void testAutomataBadEstadosFinales() throws FileNotFoundException, BadFileException {

		Automata afnd = new Automata();
		Automata afnd2 = new Automata();

		System.out.println(LINEA);
		System.out.println("TEST - Fail - Bad Estados Finales");
		System.out.println(LINEA);

		try {
			afnd = service.getAFNDFromTxtFile(fileName4);
		}
		catch(BadFileException e) {
			assertFalse(e.getClass().getSimpleName().equals(FileNotFoundException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(NullPointerException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(IOException.class.getSimpleName()));
			assertTrue(e.getClass().getSimpleName().equals(BadFileException.class.getSimpleName()));
			assertFalse(fileName4.isEmpty());
			assertFalse(afnd.getCantEstados()>0);
			assertTrue(afnd.getSimbolosInput().isEmpty());
			assertTrue(afnd.getEstadosFinales().isEmpty());
			assertTrue(afnd.getTransiciones().isEmpty());
			assertTrue(afnd.getEstados().isEmpty());
			assertTrue(afnd.getEstadosListado().isEmpty());
			assertTrue(afnd.getEstadoInicial().isEmpty());
			assertTrue(afnd.equals(afnd2));
			assertTrue(e.getLocalizedMessage().contains(AutomataService.formatoEstadosFinales));
			throw e;
		}
	}

	@Test(expected = BadFileException.class)
	public void testAutomataBadTransiciones() throws FileNotFoundException, BadFileException {

		Automata afnd = new Automata();
		Automata afnd2 = new Automata();

		System.out.println(LINEA);
		System.out.println("TEST - Fail - Bad Transiciones");
		System.out.println(LINEA);

		try {
			afnd = service.getAFNDFromTxtFile(fileName5);
		}
		catch(BadFileException e) {
			assertFalse(e.getClass().getSimpleName().equals(FileNotFoundException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(NullPointerException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(IOException.class.getSimpleName()));
			assertTrue(e.getClass().getSimpleName().equals(BadFileException.class.getSimpleName()));
			assertFalse(fileName5.isEmpty());
			assertFalse(afnd.getCantEstados()>0);
			assertTrue(afnd.getSimbolosInput().isEmpty());
			assertTrue(afnd.getEstadosFinales().isEmpty());
			assertTrue(afnd.getTransiciones().isEmpty());
			assertTrue(afnd.getEstados().isEmpty());
			assertTrue(afnd.getEstadosListado().isEmpty());
			assertTrue(afnd.getEstadoInicial().isEmpty());
			assertTrue(afnd.equals(afnd2));
			assertTrue(e.getLocalizedMessage().contains(AutomataService.formatoTransicion));
			throw e;
		}
	}

	@Test(expected = BadTransicionException.class)
	public void testAutomataBadTransicion() throws FileNotFoundException, BadFileException, BadTransicionException {

		System.out.println(LINEA);
		System.out.println("TEST - Fail - Bad Transicion");
		System.out.println(LINEA);
		
		Transicion transicion = null;
		Transicion transicion2 = new Transicion("1","a","2");
		Automata automata = new Automata();
		
		assertFalse(transicion2.equals(transicion));
		assertFalse(transicion2.equals(automata));
		
		assertTrue(transicion2.getEstadoSalida().equals("1"));
		assertTrue(transicion2.getSimboloInput().equals("a"));
		assertTrue(transicion2.getEstadoLlegada().equals("2"));
		
		try {
			transicion = new Transicion(null,"a","2");
		}
		catch(BadTransicionException e) {
			assertFalse(e.getClass().getSimpleName().equals(FileNotFoundException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(NullPointerException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(IOException.class.getSimpleName()));
			assertFalse(e.getClass().getSimpleName().equals(BadFileException.class.getSimpleName()));
			assertTrue(e.getClass().getSimpleName().equals(BadTransicionException.class.getSimpleName()));
			assertTrue(e.getLocalizedMessage().equals("Error. No se puede enviar como parametro del constructor un null"));
			assertTrue(transicion == null);
			throw e;
		}
	}
}
