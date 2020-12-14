package test;

import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;
import gramatica.Produccion;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestEjercicio1a_producciones_e {

	GramaticaService gramaticaService = new GramaticaService();


	@Test
	public void testGramatica_0_NoMatchea() throws FileNotFoundException, BadFileException {

		System.out.println("--------------------------");
		System.out.println("testGramatica_0_NoMatchea");
		System.out.println("--------------------------");

		boolean result = true;

		try {
			gramaticaService.getGramaticaFromTxtFile("gramatica_0_error_gramatica.txt");
		}catch (FileNotFoundException e){
			result = false;
		}catch (BadFileException e2){
			result = false;
		}

		assertFalse(result);
	}

	@Test
	public void testGramatica_1_CantProduccionesModificadas() throws FileNotFoundException, BadFileException {

		System.out.println("--------------------------");
		System.out.println("testGramatica_1_CantProduccionesModificadas");
		System.out.println("--------------------------");

		Gramatica gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_1_producciones_e.txt");
		boolean result = true;

		ArrayList<Character> simbolos = new ArrayList<>();
		simbolos.add('A');
		simbolos.add('B');
		simbolos.add('C');

		if(gramatica.getProducciones().size() != 6)
			result = false;

		assertTrue(result);
	}


	@Test
	public void testGramatica_1_nuevasProduccionesSinE() throws FileNotFoundException, BadFileException {

		System.out.println("--------------------------");
		System.out.println("testGramatica_1_nuevasProduccionesSinE");
		System.out.println("--------------------------");

		Gramatica gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_1_producciones_e.txt");

		System.out.println("Gramatica =>" + gramatica);

		gramatica.eliminarE();

		System.out.println("Nueva gramática =>" + gramatica);

		assertTrue(checkProduccionesGramatica1(gramatica.getProducciones()));
	}

	private boolean checkProduccionesGramatica1(List<Produccion> producciones) {
		boolean valida = true;

		List<Produccion> produccionesSolucion = getProduccionesGramatica1();

		for(int i = 0 ; i < producciones.size(); i++){
			for(int j = 0 ; j < producciones.size(); j++) {
				if (i==j && !producciones.get(i).equals(produccionesSolucion.get(j)))
					valida = false;
			}
		}

		return valida;

	}

	private List<Produccion> getProduccionesGramatica1() {

		List<Produccion> producciones = new ArrayList<>();

		//[Produccion{simboloInput='S', simbolos=[C]}
		Produccion p1 = new Produccion();
		p1.setSimboloInput("S");
		List<Character> simbolos = new ArrayList<>();
		simbolos.add('C');
		p1.setSimbolos(simbolos);
		producciones.add(p1);

		//Produccion{simboloInput='S', simbolos=[B]}
		Produccion p2 = new Produccion();
		p2.setSimboloInput("S");
		List<Character> simbolos2 = new ArrayList<>();
		simbolos2.add('B');
		p2.setSimbolos(simbolos2);
		producciones.add(p2);

		//Produccion{simboloInput='S', simbolos=[B, C]}
		Produccion p3 = new Produccion();
		p3.setSimboloInput("S");

		List<Character> simbolos3 = new ArrayList<>();
		simbolos3.add('B');
		simbolos3.add('C');

		p3.setSimbolos(simbolos3);
		producciones.add(p3);

		//Produccion{simboloInput='S', simbolos=[A]}
		Produccion p4 = new Produccion();
		p4.setSimboloInput("S");

		List<Character> simbolos4 = new ArrayList<>();
		simbolos4.add('A');

		p4.setSimbolos(simbolos4);
		producciones.add(p4);

		//Produccion{simboloInput='S', simbolos=[A, C]}
		Produccion p5 = new Produccion();
		p5.setSimboloInput("S");

		List<Character> simbolos5 = new ArrayList<>();
		simbolos5.add('A');
		simbolos5.add('C');

		p5.setSimbolos(simbolos5);
		producciones.add(p5);

		//Produccion{simboloInput='S', simbolos=[A, B]}
		Produccion p6 = new Produccion();
		p6.setSimboloInput("S");

		List<Character> simbolos6 = new ArrayList<>();
		simbolos6.add('A');
		simbolos6.add('B');

		p6.setSimbolos(simbolos6);
		producciones.add(p6);

		//Produccion{simboloInput='S', simbolos=[A, B, C]}
		Produccion p7 = new Produccion();
		p7.setSimboloInput("S");

		List<Character> simbolos7 = new ArrayList<>();
		simbolos7.add('A');
		simbolos7.add('B');
		simbolos7.add('C');

		p7.setSimbolos(simbolos7);
		producciones.add(p7);

		//Produccion{simboloInput='A', simbolos=[a]}
		Produccion p8 = new Produccion();
		p8.setSimboloInput("A");

		List<Character> simbolos8 = new ArrayList<>();
		simbolos8.add('a');

		p8.setSimbolos(simbolos8);
		producciones.add(p8);

		//Produccion{simboloInput='A', simbolos=[a, A]}
		Produccion p9 = new Produccion();
		p9.setSimboloInput("A");

		List<Character> simbolos9 = new ArrayList<>();
		simbolos9.add('a');
		simbolos9.add('A');

		p9.setSimbolos(simbolos9);
		producciones.add(p9);

		//Produccion{simboloInput='B', simbolos=[b]}
		Produccion p10 = new Produccion();
		p10.setSimboloInput("B");

		List<Character> simbolos10 = new ArrayList<>();
		simbolos10.add('b');

		p10.setSimbolos(simbolos10);
		producciones.add(p10);

		//Produccion{simboloInput='B', simbolos=[b, B]}
		Produccion p11 = new Produccion();
		p11.setSimboloInput("B");

		List<Character> simbolos11 = new ArrayList<>();
		simbolos11.add('b');
		simbolos11.add('B');

		p11.setSimbolos(simbolos11);
		producciones.add(p11);

		//ordeno elementos
		SortedSet conjuntoOrdenado = new TreeSet(producciones);

		return new ArrayList(conjuntoOrdenado);
	}



}