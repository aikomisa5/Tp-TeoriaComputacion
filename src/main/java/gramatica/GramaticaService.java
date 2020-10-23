package gramatica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GramaticaService {
	private static final String patternString = "^[a-zA-Z&&[^E]]\\s->\\s[a-zA-Z]+"; //TODO: "<simbolo> es un caracter en mayuscula o minuscula. VER: No deberia ser solo mayuscula¿?
	private static final String formatoGramatica = "simbolo -> simbolos"; 			
	private static final String simboloInicial = "S";
	private static final String epsilon = "E";   //TODO: supongo que tomamos E como epsilon
	
		
	public Gramatica readTxtGramatica(String path) {
		Gramatica gramatica = new Gramatica();
		FileReader fr = null;	
	    BufferedReader br = null;
	
		try {
			File archivo = new File(path);
//			File archivo = new File("src/main/resources/gramatica.txt");
			fr = new FileReader(archivo);
		    br = new BufferedReader(fr);
			List<Produccion> producciones = new ArrayList<Produccion>();
	
			//Lectura del fichero
			String linea;
			while((linea = br.readLine())!=null) {
				
				Pattern pattern = Pattern.compile(patternString);
				Matcher matcher = pattern.matcher(linea);
		
				if (matcher.find()) {
			        System.out.println("Matches");
				}else{
					System.out.println("No matches!");
				}
				boolean matches = matcher.matches();
		
				if(!matches) {
					String msj = "Ocurrio un error, una de las producciones no cumple con el formato necesario en el archivo.\nLa misma debe ser de la forma: " + formatoGramatica + ". Ejemplo: S -> aB.\nSe ingresó: " + linea;
					//TODO: edit!
					System.out.println(msj);
				}else {			
					System.out.println(linea);
					Produccion produccion = new Produccion();
				
					String [] parts = linea.split("->");
			
					produccion.setSimboloInicial(parts[0].trim());
					produccion.setSimbolosLlegada(parts[1].trim());
					producciones.add(produccion);
					
					gramatica.setProducciones(producciones);
				}	
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		return gramatica;
	}
	
	//	public void eliminarProduccionesEpsilon(List<Produccion> producciones){
	//		for(int i=0; i<producciones.size(); i++) {
	//			if(producciones.get(i).getSimbolosLlegada().equals(epsilon)) {
	//				producciones.remove(i);
	//			}
	//		}
	//		
		public void eliminarProduccionesEpsilon(List<Produccion> producciones){
			Iterator<Produccion> it = producciones.iterator();
			while(it.hasNext()) {
				if(it.next().getSimbolosLlegada().equals(epsilon)){
					it.remove();
				}
			}
		}
		
	//Limpieza de la gramática
		//Eliminar E-productions.
		//Eliminar producciones unitarias.
		//Eliminar símbolos no generadores.
		//Eliminar símbolos no alcanzables.

}