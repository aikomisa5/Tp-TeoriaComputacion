
package test;

import exceptions.BadFileException;
import lr0.ParserLR0Service;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertTrue;

public class TestEjercicio3_1 {

    ParserLR0Service parserLR0Service = new ParserLR0Service();

    @Test
    public void testGramatica_0_Matchea() throws FileNotFoundException, BadFileException {

        System.out.println("--------------------------");
        System.out.println("testLR_0_Matchea");
        System.out.println("--------------------------");

        boolean result = true;

        try {
            parserLR0Service.getGramaticaFromTxtFile("gramatica_0.txt");
        } catch (FileNotFoundException e) {
            result = false;
        } catch (BadFileException e2) {
            result = false;
        }

        assertTrue(result);
    }

}
