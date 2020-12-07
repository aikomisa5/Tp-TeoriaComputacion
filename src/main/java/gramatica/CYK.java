package gramatica;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.BadFileException;

public class CYK {

	private Gramatica gramatica;
    private static final String terminalesPatternString = "[a-df-z]";
	
	public CYK(Gramatica gramatica) {
		this.gramatica = gramatica;
	}

	
	/*
	Let the input string consist of n letters, a1... an.
	Let the grammar contain r terminal and nonterminal symbols R1... Rr. 
	This grammar contains the subset Rs which is the set of start symbols.
	Let P[n,n,r] be an array of booleans. Initialize all elements of P to false.
	For each i = 1 to n
	  For each unit production Rj -> ai, set P[i,1,j] = true.
	For each i = 2 to n -- Length of span
	  For each j = 1 to n-i+1 -- Start of span
	    For each k = 1 to i-1 -- Partition of span
	      For each production RA -> RB RC
	        If P[j,k,B] and P[j+k,i-k,C] then set P[j,i,A] = true
	If any of P[1,n,x] is true (x is iterated over the set s, where s are all the indices for Rs)
	  Then string is member of language
	  Else string is not member of language
	  */

	public void parsingCYK(String palabra) {
		if(gramatica.isInFNC()) {
			if(algoritmoCYK(palabra)) {
				System.out.println("S pertenece a X_1"+ palabra.length() + ", la gramática genera el string "+ palabra);
			}else {
				System.out.println("S no pertenece a X_1"+ palabra.length() + ", la gramática no genera el string "+ palabra);
			}
		}else {
			System.out.println("La gramática no está en Forma Normal de Chomsky");
		}
	}
	
	public boolean algoritmoCYK(String palabra) {
		int n = palabra.length();
		int r = gramatica.getProducciones().size();
		
//		P[n,n,r] es un array of booleans. Inicializo todos los elementos de P en false. 
		boolean P[][][] =  new boolean[n][n][r];
		for(int i = 0 ; i < n ; i++) {
			for (int j = 0 ; j < n ; j++) {
				for (int k = 0 ; k < r ; k++) {
					P[i][j][k] = false;
				}
			}
		}
			
//		Para cada produccion del tipo Rj -> ai , colocar P[i,0,j] en true. 
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < r ; j++) {
				Produccion produccion = gramatica.getProducciones().get(j);
				if(produccion.getSimbolos().size() == 1) {
					for(Character c : produccion.getSimbolos()) {
						if(c.equals(palabra.charAt(i)) && esTerminal(c)) {
							int k = buscarProduccionPorIndice(produccion.getSimboloInput());
//								P[i][0][k] = true;
								P[0][i][k] = true;
//								System.out.println(P[0][i][k] +" " + 0 + " " + i +" "+ k);
						}
					}
				}
			}
		}	
					
////		For each i = 2 to n -- Length of span
////			For each j = 1 to n-i+1 -- Start of span
////				For each k = 1 to i-1 -- Partition of span
		for(int i = 1 ; i < n ; i++) {  
			for (int j = 0; j < n - i ; j++) {
				for(int k = 0 ; k < i ; k++) {
					for (int p = 0 ; p < r ; p++) {
						Produccion produccion = gramatica.getProducciones().get(p);
						if (produccion.getSimbolos().size() == 2) {
							
							int A = buscarProduccionPorIndice(produccion.getSimboloInput());
							int B = buscarProduccionPorIndice(produccion.getSimbolos().get(0).toString());
							int C = buscarProduccionPorIndice(produccion.getSimbolos().get(1).toString());
								
							if (P[k][j][B] == true && P[i - k - 1][j + k + 1][C] == true) {
								P[i][j][A] = true;
//								System.out.println(P[i][j][A] + " "+ i+ " " +j + " " + A);
							}
//							if (P[j][k][B] == true && P[j + k + 1][i - k - 1][C] == true) {
//								P[j][i][A] = true;
//							System.out.println(P[j][i][A] + " "+ j+ " " +i + " " + A);
//
//							}
						}
					}
				}
			}
		}
//			
		int s = buscarProduccionPorIndice(gramatica.getEstadoInicial());
		if (P[n - 1][0][s]) {
//		if(P[0][n-1][s]) {
			return true;
		}
		return false;	
	}
	
		
	private int buscarProduccionPorIndice(String simbolo) {
		int indice = -1;
		for(int i = 0 ; i < gramatica.getProducciones().size() ; i++) {
			if(gramatica.getProducciones().get(i).getSimboloInput().equals(simbolo)) {
				indice = i;
			}
		}
		return indice;
	}
	
	 private boolean esTerminal(Character simbolo){
		 Pattern pattern = Pattern.compile(terminalesPatternString);
		 Matcher matcher = pattern.matcher("" + simbolo);
	     return matcher.matches();
	 }

	public static void main(String [] args) throws FileNotFoundException, BadFileException {
		GramaticaService gramaticaService = new GramaticaService();
		Gramatica gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_cyk.txt");
		
		CYK cyk = new CYK(gramatica);
//		System.out.println(cyk.algoritmoCYK("baaba"));
		cyk.parsingCYK("baaba");
		cyk.parsingCYK("baaab");
		cyk.parsingCYK("aabab");
		cyk.parsingCYK("abac");
	}	
}