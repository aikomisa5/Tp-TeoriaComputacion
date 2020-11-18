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

public class TestEjercicio1a_generadores {

    GramaticaService gramaticaService = new GramaticaService();

    @Test
    public void sUsadoMultiplesVeces(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_multipleUsodeS.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }
        assertTrue(result);
    }

    @Test
    public void sNoDerivaNada1Test(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_sNoDerivaNada_1.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        gramatica.eliminarSimbolosNoGeneradores();

        assertTrue( result && gramatica.getProducciones().size() == 0);

    }

    @Test
    public void sNoDerivaNada2Test(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_sNoDerivaNada_2.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        gramatica.eliminarSimbolosNoGeneradores();
        

        assertTrue( result && gramatica.getProducciones().size() == 0);

    }

    @Test
    public void generadores1Test(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_generadores_1.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        gramatica.eliminarSimbolosNoGeneradores();

        assertTrue( result && gramatica.getProducciones().size() == 4);

        Produccion produccionACheckear = gramatica.getProducciones().get(0);

        assertTrue( produccionACheckear.getSimboloInput().equals("S")
            && produccionACheckear.getSimbolos().get(0).equals('C'));

        produccionACheckear = gramatica.getProducciones().get(1);

        assertTrue( produccionACheckear.getSimboloInput().equals("A")
                && produccionACheckear.getSimbolos().get(0).equals('a')
                && produccionACheckear.getSimbolos().get(1).equals('A'));

        produccionACheckear = gramatica.getProducciones().get(2);

        assertTrue( produccionACheckear.getSimboloInput().equals("A")
                && produccionACheckear.getSimbolos().get(0).equals('a'));

        produccionACheckear = gramatica.getProducciones().get(3);

        assertTrue( produccionACheckear.getSimboloInput().equals("C")
                && produccionACheckear.getSimbolos().get(0).equals('c'));
    }

    @Test
    public void generadores2Test(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_generadores_2.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        gramatica.eliminarSimbolosNoGeneradores();

        assertTrue( result && gramatica.getProducciones().size() == 3);

        Produccion produccionACheckear = gramatica.getProducciones().get(0);

        assertTrue( produccionACheckear.getSimboloInput().equals("S")
                && produccionACheckear.getSimbolos().get(0).equals('A'));

        produccionACheckear = gramatica.getProducciones().get(1);

        assertTrue( produccionACheckear.getSimboloInput().equals("A")
                && produccionACheckear.getSimbolos().get(0).equals('a')
                && produccionACheckear.getSimbolos().get(1).equals('A'));

        produccionACheckear = gramatica.getProducciones().get(2);

        assertTrue( produccionACheckear.getSimboloInput().equals("A")
                && produccionACheckear.getSimbolos().get(0).equals('a'));
    }

    @Test
    public void generadoresEraUnBugTest(){
        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_generadores_bugHunt1.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        gramatica.eliminarSimbolosNoGeneradores();

        assertTrue( result && gramatica.getProducciones().size() == 3);

       /* for(Produccion produccion : gramatica.getProducciones()) {
            System.out.println(produccion);
        }*/

        Produccion produccionACheckear = gramatica.getProducciones().get(0);
        assertTrue( produccionACheckear.getSimboloInput().equals("S")
        		&& produccionACheckear.getSimbolos().get(0).equals('C')
                && produccionACheckear.getSimbolos().get(1).equals('A'));

        produccionACheckear = gramatica.getProducciones().get(1);

        assertTrue( produccionACheckear.getSimboloInput().equals("A")
                && produccionACheckear.getSimbolos().get(0).equals('a'));

        produccionACheckear = gramatica.getProducciones().get(2);

        assertTrue( produccionACheckear.getSimboloInput().equals("C")
                && produccionACheckear.getSimbolos().get(0).equals('b'));
    }

    @Test
    public void generadoressSinCambios1Test(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_inutiles_ambasSinCambio.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }


        gramatica.eliminarSimbolosNoGeneradores();

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
    public void generadores3Test(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_generadores_3.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }


        gramatica.eliminarSimbolosNoGeneradores();

        assertTrue( result && gramatica.getProducciones().size() == 4);

        assertTrue(assertTrueProd(gramatica,0,"S",new ArrayList<>(Arrays.asList('b','X'))));
        assertTrue(assertTrueProd(gramatica,1,"A",new ArrayList<>(Arrays.asList('b','S','X'))));
        assertTrue(assertTrueProd(gramatica,2,"A",new ArrayList<>(Arrays.asList('a'))));
        assertTrue(assertTrueProd(gramatica,3,"X",new ArrayList<>(Arrays.asList('a','d'))));

    }

    private boolean assertTrueProd(Gramatica gramatica, int index, String simbolo, List<Character> simbolos){
        return gramatica.getProducciones().get(index).equals
                (new Produccion(simbolo,simbolos));
    }
}
