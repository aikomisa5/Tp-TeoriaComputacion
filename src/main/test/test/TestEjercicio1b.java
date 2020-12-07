package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import org.junit.Test;

import exceptions.BadFileException;
import gramatica.CYK;
import gramatica.Gramatica;
import gramatica.GramaticaService;

public class TestEjercicio1b {

    GramaticaService gramaticaService = new GramaticaService();

    @Test
    public void CYK1Test(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_cyk.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        CYK cyk = new CYK(gramatica);
        assertTrue(result && cyk.algoritmoCYK("baaba"));
        assertTrue(result && cyk.algoritmoCYK("baaab"));
        assertTrue(result && cyk.algoritmoCYK("aabab"));
    }
    
    @Test
    public void CYK2Test(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_cyk2.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        CYK cyk = new CYK(gramatica);
        assertTrue(result && cyk.algoritmoCYK("abaaa"));
//        assert(result && cyk.algoritmoCYK("aabb"));
        //TODO: hacer mas tests
    } 
    
    
    @Test
    public void stringNoPerteneceCYKTest(){

        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_cyk.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        CYK cyk = new CYK(gramatica);
        assertFalse(result && cyk.algoritmoCYK("baabac"));
        assertFalse(result && cyk.algoritmoCYK("babbbbaaaaaaas"));
    }
}