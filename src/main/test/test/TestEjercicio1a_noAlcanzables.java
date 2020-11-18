package test;

import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;
import gramatica.Produccion;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestEjercicio1a_noAlcanzables {

    GramaticaService gramaticaService = new GramaticaService();

    @Test
    public void alcanzables1Test(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_noalcanzables_1.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }
        gramatica.eliminarSimbolosNoAlcanzables();

        assertTrue( result && gramatica.getProducciones().size() == 1);
        Produccion produccionACheckear = gramatica.getProducciones().get(0);
        assertTrue(assertTrueProd(gramatica,0,"S",new ArrayList<>(Arrays.asList('a'))));

    }

    @Test
    public void alcanzablesSinCambios1Test(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_inutiles_ambasSinCambio.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }


        gramatica.eliminarSimbolosNoAlcanzables();

        assertTrue( result && gramatica.getProducciones().size() == 13);

        assertTrue(assertTrueProd(gramatica,0,"S",new ArrayList<>(Arrays.asList('A', 'S', 'B'))));
        assertTrue(assertTrueProd(gramatica,1,"S",new ArrayList<>(Arrays.asList('A', 'B'))));
        assertTrue(assertTrueProd(gramatica,2,"A",new ArrayList<>(Arrays.asList('a','A','S'))));
        assertTrue(assertTrueProd(gramatica,3,"A",new ArrayList<>(Arrays.asList('a','A'))));
        assertTrue(assertTrueProd(gramatica,4,"B",new ArrayList<>(Arrays.asList('a','A','S'))));
        assertTrue(assertTrueProd(gramatica,5,"B",new ArrayList<>(Arrays.asList('a','A'))));
        assertTrue(assertTrueProd(gramatica,6,"A",new ArrayList<>(Arrays.asList('a'))));
        assertTrue(assertTrueProd(gramatica,7,"B",new ArrayList<>(Arrays.asList('a'))));
        assertTrue(assertTrueProd(gramatica,8,"B",new ArrayList<>(Arrays.asList('b','b'))));
        assertTrue(assertTrueProd(gramatica,9,"B",new ArrayList<>(Arrays.asList('S','b','S'))));
        assertTrue(assertTrueProd(gramatica,10,"B",new ArrayList<>(Arrays.asList('S','b'))));
        assertTrue(assertTrueProd(gramatica,11,"B",new ArrayList<>(Arrays.asList('b','S'))));
        assertTrue(assertTrueProd(gramatica,12,"B",new ArrayList<>(Arrays.asList('b'))));
    }

    @Test
    public void alcanzables2Test(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_noalcanzables_2.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        gramatica.eliminarSimbolosNoAlcanzables();

        assertTrue( result && gramatica.getProducciones().size() == 2);

        assertTrue(assertTrueProd(gramatica,0,"S",new ArrayList<>(Arrays.asList('b','X'))));
        assertTrue(assertTrueProd(gramatica,1,"X",new ArrayList<>(Arrays.asList('a', 'd'))));
    }

    private boolean assertTrueProd(Gramatica gramatica, int index, String simbolo, List<Character> simbolos){
        return gramatica.getProducciones().get(index).equals
                (new Produccion(simbolo,simbolos));
    }
}
