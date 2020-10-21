package automata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.BadFileException;

public class AutomataService {
	
	private static final String patternString = "[1-9]+,\\s[a-zA-Z]+\\s->\\s[1-9]+";
	private static final String formatoProyeccion = "NumeroEstadoSalida, SimboloInput -> NumeroEstadoLlegada. Ej: 4, E -> 4";

	public Automata getAutomataFromFile() throws BadFileException, FileNotFoundException {

		Automata response = new Automata();

		Scanner myReader = null;
		int indice = 0;

		try {
			File myObj = new File("src/main/resources/automata.txt");
			myReader = new Scanner(myObj);

			if (myReader.hasNextLine() == false) {
				String msj = "Ocurrio un error al intentar obtener los datos del automata del archivo, debido a que el archivo esta vacio";
				System.out.println(msj);
				throw new BadFileException(msj);
			}
			
			List<Proyeccion> proyecciones = new ArrayList<Proyeccion>();
			
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();

				if (indice == 0) {
					try {
						List<String> simbolosInput = new ArrayList<String>(Arrays.asList(data.split(",")));
						response.setSimbolosInput(simbolosInput);
					}
					catch(Exception e) {
						String msj = "Ocurrio un error al intentar obtener los datos de los simbolos de input del archivo: " + e.getMessage();
						System.out.println(msj);
						throw new BadFileException(msj, e);
					}
				}
				else if (indice == 1) {
					try {
						response.setCantEstados(Integer.valueOf(data));
					}
					catch(Exception e) {
						String msj = "Ocurrio un error al intentar obtener el dato de la cantidad de estados del archivo: " + e.getMessage();
						System.out.println(msj);
						throw new BadFileException(msj, e);
					}
				}

				else if (indice == 2) {
					try {
						List<String> estadosFinales = new ArrayList<String>(Arrays.asList(data.split(",")));
						response.setEstadosFinales(estadosFinales);
					}
					catch(Exception e) {
						String msj = "Ocurrio un error al intentar obtener los datos de los estados finales del archivo: " + e.getMessage();
						System.out.println(msj);
						throw new BadFileException(msj, e);
					}
				}
				else {
					Pattern pattern = Pattern.compile(patternString);

					Matcher matcher = pattern.matcher(data);
					boolean matches = matcher.matches();
					
					if (matches == false) {
						String msj = "Ocurrio un error, una de las transiciones no cumple con el formato necesario en el archivo. La misma debe ser de la forma: " + formatoProyeccion + ", y la misma es: " + data;
						System.out.println(msj);
						throw new BadFileException(msj);
					}
					
					Proyeccion proyeccion = new Proyeccion();
					
					/***Ejemplo: 4, E -> 4***/
					
					String[] parts = data.split(",");
					
					proyeccion.setEstadoLlegada(parts[0]);
					
					String[] parts2 = parts[1].split("->");
					
					proyeccion.setSimboloInput(parts2[0].trim());
					proyeccion.setEstadoSalida(parts2[1].trim());
				
					proyecciones.add(proyeccion);
				}

				System.out.println(data);

				indice++;
			}
			
			if (indice < 3) {
				String msj = "Ocurrio un error, el archivo no contenia proyecciones";
				System.out.println(msj);
				throw new BadFileException(msj);
			}
			else {
				response.setProyecciones(proyecciones);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Ocurrio un error, debido a que no se encontro el archivo: " + e.getMessage());
			throw e;
		}
		finally {
			if (myReader != null) {
				myReader.close();
			}
		}

		return response;
	}
}
