package test;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;
import gramatica.Produccion;

public class TestEjercicio1a_sinSimbolosInutiles {

	 GramaticaService gramaticaService = new GramaticaService();

	 @Test
	 public void sinSimbolosInutiles1Test(){
		 boolean result = true;
		 Gramatica gramatica = null;
	     try {
	    	 gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_simbolosInutiles.txt");
	     }catch (FileNotFoundException e){
	         result = false;
	     }catch (BadFileException e2){
	         result = false;
	     }
	     
	     gramatica.eliminarSimbolosNoGeneradores();
	     gramatica.eliminarSimbolosNoAlcanzables();

	     assertTrue( result && gramatica.getProducciones().size() == 1);
	     assertTrue(assertTrueProd(gramatica,0,"S",new ArrayList<>(Arrays.asList('a'))));
	  }
	 
	 @Test
	 public void sinSimbolosInutiles2Test(){
		 boolean result = true;
		 Gramatica gramatica = null;
	     try {
	    	 gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_simbolosInutiles2.txt");
	     }catch (FileNotFoundException e){
	         result = false;
	     }catch (BadFileException e2){
	         result = false;
	     }
	     
	     gramatica.eliminarSimbolosNoGeneradores();
	     gramatica.eliminarSimbolosNoAlcanzables();

	     assertTrue(result && gramatica.getProducciones().size() == 3);
	     assertTrue(assertTrueProd(gramatica,0,"S",new ArrayList<>(Arrays.asList('A'))));
	     assertTrue(assertTrueProd(gramatica,1,"S",new ArrayList<>(Arrays.asList('b'))));
	     assertTrue(assertTrueProd(gramatica,2,"A",new ArrayList<>(Arrays.asList('a'))));	 
	 }
	 
	  private boolean assertTrueProd(Gramatica gramatica, int index, String simbolo, List<Character> simbolos){
	        return gramatica.getProducciones().get(index).equals
	                (new Produccion(simbolo,simbolos));
	  }
}