package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.FileNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;
import exceptions.BadFileException;
import gramatica.Gramatica;
import gramatica.GramaticaService;

public class TestEjercicio1b_1 {

    private static GramaticaService gramaticaService = new GramaticaService();
	private static Gramatica gramatica = new Gramatica();
	private static String file = "gramatica_cyk.txt";

	private static final String LA_GRAMATICA_GENERA_EL_STRING = "La gramatica genera el string ";
	private static final String LINEA = "----------------------------------------";

	@BeforeClass
	public static void setup() throws FileNotFoundException, BadFileException {
		System.out.println(LINEA);
		System.out.println("*                 CYK                   *");
		gramatica = gramaticaService.getGramaticaFromTxtFile(file);
		System.out.println(LINEA);
		System.out.println("TEST 1");
		gramaticaService.printGramatica(gramatica);
	}
	
    @Test(expected = IllegalArgumentException.class)
	public void CYKstringVacioTest() {
    	String w = "";
     	gramaticaService.algoritmoCYK(gramatica, w);
    }

    @Test(expected = NullPointerException.class)
	public void CYK1NullTest(){
    	
    	String w = null;
    	gramaticaService.algoritmoCYK(gramatica, w);
    }
  
    @Test
    public void CYK1baabaTest() throws FileNotFoundException, BadFileException{
    	
    	String w = "baaba";
    	boolean resultado = gramaticaService.algoritmoCYK(gramatica, w);
		System.out.println(LA_GRAMATICA_GENERA_EL_STRING + w  +" ? : "+ resultado);
        assertTrue(resultado);
    }
    
    @Test
    public void CYK1baaabTest() throws FileNotFoundException, BadFileException{
    	
    	String w = "baaab";
    	boolean resultado =  gramaticaService.algoritmoCYK(gramatica, w);
		System.out.println(LA_GRAMATICA_GENERA_EL_STRING + w  +" ? : "+ resultado);
        assertTrue(resultado);
    }
    
    @Test
    public void CYK1aababTest() throws FileNotFoundException, BadFileException{
    	
    	String w = "aabab";
    	boolean resultado = gramaticaService.algoritmoCYK(gramatica, w);
		System.out.println(LA_GRAMATICA_GENERA_EL_STRING + w  +" ? : "+ resultado);
        assertTrue(resultado);
     }
    
    
    @Test
    public void CYK1baabacTest() throws FileNotFoundException, BadFileException{
    	
    	String w = "baabac";
    	boolean resultado = gramaticaService.algoritmoCYK(gramatica, w);
		System.out.println(LA_GRAMATICA_GENERA_EL_STRING + w  +" ? : "+ resultado);
        assertFalse(resultado);
     }
    
    @Test
    public void CYK1babbbbaaaaaaasTest() throws FileNotFoundException, BadFileException{
    	
    	String w = "babbbbaaaaaaas";
    	boolean resultado = gramaticaService.algoritmoCYK(gramatica, w);
		System.out.println(LA_GRAMATICA_GENERA_EL_STRING + w  +" ? : "+ resultado);
        assertFalse(resultado);
    }
    
    @Test
    public void CYK11111Test() throws FileNotFoundException, BadFileException{
    	
    	String w = "11111";
    	boolean resultado = gramaticaService.algoritmoCYK(gramatica, w);
		System.out.println(LA_GRAMATICA_GENERA_EL_STRING + w  +" ? : "+ resultado);
        assertFalse(resultado);
    }
}