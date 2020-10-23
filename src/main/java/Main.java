import java.io.FileNotFoundException;

import automata.Automata;
import automata.AutomataService;
import exceptions.BadFileException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException, BadFileException {
		
		//TODO quitar de aca
		AutomataService automataService = new AutomataService();
		Automata automata = automataService.getAutomataFromTxtFile();
		Automata response = automataService.getAFD(automata.getProyecciones(), automata.getSimbolosInput(), automata.getEstadosFinales(), automata.getCantEstados());
		
		System.out.println("-----------------------");
		System.out.println("AFD");
		
		String simbolosInput = "";
		
		int indice = 1;
		
		for (String simbolo : response.getSimbolosInput()) {
			
			if (indice == response.getSimbolosInput().size()) {
				simbolosInput = simbolosInput + simbolo;
			}
			else {
				simbolosInput = simbolosInput + simbolo + ", ";
			}
			
			indice++;
		}
		
		String estadosFinales = "";
		
		int indiceEstadosFinales = 1;
		
		for (String estado : response.getEstadosFinales()) {
			
			if (indiceEstadosFinales == response.getEstadosFinales().size()) {
				estadosFinales = estadosFinales + estado;
			}
			else {
				estadosFinales = estadosFinales + estado + ", ";
			}
			
			indiceEstadosFinales++;
		}
		
		
		System.out.println(simbolosInput);
		System.out.println(response.getCantEstados());
		System.out.println(estadosFinales);
		
		response.getProyecciones().forEach(a ->{
			System.out.println(a.getEstadoSalida() + ", " + a.getSimboloInput() + " -> " + a.getEstadoLlegada());
		});
	} 
}
