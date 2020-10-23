import java.io.FileNotFoundException;

import automata.Automata;
import automata.AutomataService;
import exceptions.BadFileException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException, BadFileException {
//		
//		//TODO quitar de aca
		AutomataService automataService = new AutomataService();
		Automata automata = automataService.getAutomataFromFile();
	}
}
