package main;
import java.io.FileNotFoundException;

import automata.Automata;
import automata.AutomataService;
import exceptions.BadFileException;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException, BadFileException {
		
		//TODO llevar esto a un test
		AutomataService automataService = new AutomataService();
		
		Automata e_afnd = automataService.getAutomataFromTxtFile();
		Automata afd = automataService.getAFD(e_afnd.getProyecciones(), e_afnd.getSimbolosInput(), e_afnd.getEstadosFinales(), e_afnd.getCantEstados());
		
		automataService.printAutomata(e_afnd, "E-AFND");
		automataService.printAutomata(afd, "AFD");
		
		System.out.println("--------------------");
		System.out.println("Procesado de Strings");
		System.out.println("--------------------");
		
		boolean aaaaaaaaaa = automataService.procesar("aaaaaaaaaa");
		System.out.println("aaaaaaaaaa pertenece? : " + aaaaaaaaaa);
		
		boolean cccccccc = automataService.procesar("cccccccc");
		System.out.println("cccccccc pertenece? : " + cccccccc);
		
		boolean aba = automataService.procesar("aba");
		System.out.println("aba pertenece? : " + aba);
		
		boolean ababa = automataService.procesar("ababa");
		System.out.println("ababa pertenece? : " + ababa);
		
		boolean abcabc = automataService.procesar("abcabc");
		System.out.println("abcabc pertenece? : " + abcabc);
		
		boolean bb = automataService.procesar("bb");
		System.out.println("bb pertenece? : " + bb);
		
		boolean bbb = automataService.procesar("bbb");
		System.out.println("bbb pertenece? : " + bbb);
		
		boolean ab = automataService.procesar("ab");
		System.out.println("ab pertenece? : " + ab);
	} 
}
