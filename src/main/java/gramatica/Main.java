package gramatica;

public class Main {

	public static void main (String [] args) {
		GramaticaService gs= new GramaticaService();
		Gramatica gramatica = gs.readTxtGramatica("src/main/resources/gramatica.txt");
		System.out.println(gramatica.getProducciones().size());
		
//		gs.eliminarProduccionesEpsilon(gramatica.getProducciones());
//		System.out.println(gramatica.getProducciones().size());
	}
}
