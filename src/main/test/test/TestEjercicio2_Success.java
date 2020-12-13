package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import automata.Automata;
import automata.Transicion;
import exceptions.BadTransicionException;

public class TestEjercicio2_Success extends TestAutomataBase{
	
	@Test
	public void testAutomataTransicion() throws BadTransicionException {

		System.out.println(LINEA);
		System.out.println("TEST - Success - Transicion");
		System.out.println(LINEA);
		
		Transicion transicion = new Transicion("1","a","2");
		assertTrue(transicion.getEstadoSalida().equals("1"));
		assertTrue(transicion.getSimboloInput().equals("a"));
		assertTrue(transicion.getEstadoLlegada().equals("2"));
	}
	
	@Test
	public void testAutomataEstados() throws BadTransicionException {

		System.out.println(LINEA);
		System.out.println("TEST - Success - Estados");
		System.out.println(LINEA);
		
		List<String> estados = new ArrayList<String>();
		
		estados.add("123");
		estados.add("1234");
		estados.add("12345");
		
		Automata automata = new Automata();
		
		automata.setEstados(estados);
		
		assertTrue(automata.getEstados().equals(estados));
	}
}
