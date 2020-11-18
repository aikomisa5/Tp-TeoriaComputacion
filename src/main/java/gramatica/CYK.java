package gramatica;

import java.io.FileNotFoundException;
import exceptions.BadFileException;

public class CYK {

	private Gramatica gramatica;
	
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
	
	public boolean algoritmoCYK(String palabra) {
//		Let the input string consist of n letters, a1... an.
//		Let the grammar contain r terminal and nonterminal symbols R1... Rr. 
		
		int n = palabra.length();
		int r = gramatica.getProducciones().size();

		// Let P[n,n,r] be an array of booleans. Initialize all elements of P to false.
		boolean P[][][] =  new boolean[n][n][r];
		for(int i = 0; i < n ; i++) {
			for (int j = 0; j < n ; j++) {
				for (int k = 0; k < r ; k++) {
					P[i][j][k] = false;
				}
			}
		}
		//For each unit production Rj -> ai, set P[i,1,j] = true.
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < r ; j++) {
				Produccion produccion = gramatica.getProducciones().get(j);
				for(Character c : produccion.getSimbolos()) {
					if(c.equals(palabra.charAt(i))) {
						int k = buscarProduccionPorIndice(produccion.getSimboloInput());
						P[i][0][k] = true;
					}
				}
			}
		}	
		
//		For each i = 2 to n -- Length of span
//			For each j = 1 to n-i+1 -- Start of span
//				For each k = 1 to i-1 -- Partition of span
		
		
//		for(int i = 1; i < n ; i++) {
//			for (int j = 0; j < n - i ; j++) {
//				for(int k = 0 ; k < i ; k++) { 
					//For each production RA -> RB RC
					// If P[j,k,B] and P[j+k,i-k,C] then set P[j,i,A] = true
						
//				}
//			}
//		}
//		If any of P[1,n,x] is true (x is iterated over the set s, where s are all the indices for Rs)
//		  Then string is member of language
//		  Else string is not member of language			
		return false;
	}
		
	private int buscarProduccionPorIndice(String simboloInput) {
		int indice = -1;
		for(int i = 0 ; i < gramatica.getProducciones().size() ; i++) {
			if(gramatica.getProducciones().get(i).getSimboloInput().equals(simboloInput)) {
				indice = i;
			}
		}
		return indice;
	}
	
	public static void main(String [] args) throws FileNotFoundException, BadFileException {
		GramaticaService gramaticaService = new GramaticaService();
		Gramatica gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_cyk.txt");
		
		CYK cyk = new CYK(gramatica);
		cyk.algoritmoCYK("baaba");
	}	
}