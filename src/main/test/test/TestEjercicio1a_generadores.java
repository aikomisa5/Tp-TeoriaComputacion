package test;

import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;
import gramatica.Produccion;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEjercicio1a_generadores {

    GramaticaService gramaticaService = new GramaticaService();

    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
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
    
    @org.junit.jupiter.api.Test
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
}
