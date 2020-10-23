import java.io.FileNotFoundException;

import automata.Automata;
import automata.AutomataService;
import exceptions.BadFileException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException, BadFileException {
		
		//TODO quitar de aca
		AutomataService automataService = new AutomataService();
		Automata automata = automataService.getAutomataFromFile();
		Automata response = automataService.getAFD(automata.getProyecciones(), automata.getSimbolosInput(), automata.getCantEstados());
		
		System.out.println("-----------------------");
		System.out.println("AFD");
		response.getProyecciones().forEach(a ->{
			System.out.println(a.getEstadoSalida() + ", " + a.getSimboloInput() + " -> " + a.getEstadoLlegada());
		});
	} 
}
