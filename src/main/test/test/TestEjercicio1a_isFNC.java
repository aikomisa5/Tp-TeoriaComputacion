package test;

import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class TestEjercicio1a_isFNC {

    GramaticaService gramaticaService = new GramaticaService();

    @Test
    public void isInFnc1Test()
    {
        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_fnctest_1.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }


        assertTrue(result);
        assertTrue(gramatica.isInFNC());

    }

    @Test
    public void isInFnc2Test()
    {
        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_fnctest_2.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        assertTrue(result);
        assertTrue(gramatica.isInFNC());

    }

    @Test
    public void isInFnc3Test()
    {
        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_fnctest_3.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        assertTrue(result);
        assertTrue(gramatica.isInFNC());

    }

    @Test
    public void isNotInFnc1Test()
    {
        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_isnotfnctest_1.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        assertTrue(result);
        assertTrue(!gramatica.isInFNC());

    }

    @Test
    public void isNotInFnc2Test()
    {
        boolean result = true;
        Gramatica gramatica = null;
        try {
            gramatica = gramaticaService.getGramaticaFromTxtFile("gramatica_isnotfnctest_2.txt");
        }catch (FileNotFoundException e){
            result = false;
        }catch (BadFileException e2){
            result = false;
        }

        assertTrue(result);
        assertTrue(!gramatica.isInFNC());

    }
}
