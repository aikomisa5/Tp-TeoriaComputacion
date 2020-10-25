package test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import automata.Automata;
import automata.AutomataService;
import exceptions.BadFileException;
import org.junit.jupiter.api.Test;

class TestEjercicio2a_2 {
	
	AutomataService automataService = new AutomataService();
	
	Automata e_afnd = new Automata();
	Automata afd = new Automata();

	@Test
	public void testAutomata() throws FileNotFoundException, BadFileException {

		//TODO
		assertTrue(true);
	}
}
