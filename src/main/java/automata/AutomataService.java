package automata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import exceptions.BadFileException;

public class AutomataService {

	private static final String patternString = "[1-9]+,\\s[a-zA-Z]+\\s->\\s[1-9]+";
	private static final String formatoProyeccion = "NumeroEstadoSalida, SimboloInput -> NumeroEstadoLlegada. Ej: 4, E -> 4";
	private static final String EPSILON = "E";
	private static final String ESTADO_INICIAL = "1";

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
						List<String> trim = new ArrayList<String>(Arrays.asList(data.split(",")));
						List<String> simbolos = new ArrayList<String>();
						for(String simbolo : trim) {
							simbolos.add(simbolo.trim());
						}
						response.setSimbolosInput(simbolos);
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

					proyeccion.setEstadoSalida(parts[0]);

					String[] parts2 = parts[1].split("->");

					proyeccion.setSimboloInput(parts2[0].trim());
					proyeccion.setEstadoLlegada(parts2[1].trim());

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

	//	public Automata getAFD(Automata e_afnd){
	//
	//		String estadoInicial = "1";
	//
	//		if (poseeTransicionEpsilon(estadoInicial, e_afnd)) {
	//
	//		}
	//
	//		return new Automata();
	//	}

	public Automata getAFD(List<Proyeccion> proyeccionesOriginales, List<String> simbolosInput, int cantEstados) {

		/*** Nuevo ***/
		List<Proyeccion> nuevasProyecciones = new ArrayList<Proyeccion>();
		List<List<String>> nuevosEstados = new ArrayList<List<String>>();
		List<List<String>> ultimosNuevosEstados = new ArrayList<List<String>>();
		int nuevaCantEstados = 1;

		List<String> estadosALosQueLlego = new ArrayList<String>();
		estadosALosQueLlego.add(ESTADO_INICIAL);

		List<String> ultimosEstadosAgregados = new ArrayList<String>();
		ultimosEstadosAgregados.add(ESTADO_INICIAL);
		
		

		/**
		 * Estados a los que llego desde el estado inicial 
		 **/
		List<String> nuevoEstadoInicialListado = getEstadosALosQueLlegoConEpsilon(estadosALosQueLlego, ultimosEstadosAgregados, proyeccionesOriginales, cantEstados);
		Collections.sort(nuevoEstadoInicialListado);
		//		String nuevoEstadoInicial = getEstadoFromListado(nuevoEstadoInicialListado);
		ultimosNuevosEstados.add(nuevoEstadoInicialListado);
		nuevosEstados.add(nuevoEstadoInicialListado);

		/**
		 * Estados a los que llego con el nuevo estado inicial - Creacion de nuevas proyecciones
		 **/
		Automata response = getAutomata(nuevosEstados, ultimosNuevosEstados, proyeccionesOriginales, nuevasProyecciones, simbolosInput, cantEstados, nuevaCantEstados);

		return response;
	}

	//Recursivo
	private Automata getAutomata(List<List<String>> nuevosEstados, List<List<String>> ultimosNuevosEstados, 
			List<Proyeccion> proyeccionesOriginales, List<Proyeccion> nuevasProyecciones, List<String> simbolosInput, int cantEstados, int nuevaCantEstados){

		List<List<String>> nuevosEstadosTotales = new ArrayList<List<String>>(nuevosEstados);
		List<List<String>> nuevosEstadosAgregados = new ArrayList<List<String>>();
		//		List<Proyeccion> nuevasProyeccionesAgregadas = new ArrayList<Proyeccion>(nuevasProyecciones);

		for (String input : simbolosInput) {

			for (List<String> estadoListado : ultimosNuevosEstados) {

				List<String> nuevoEstadoListado = new ArrayList<String>();

				for (String estado : estadoListado) {

					List<String> estadoALosQueLlego = getEstadosALosQueLlego(estado, input, proyeccionesOriginales, cantEstados);

					if (estadoALosQueLlego != null) {
						for (String estadoNuevo : estadoALosQueLlego) {
							if (nuevoEstadoListado.contains(estadoNuevo) == false) {
								nuevoEstadoListado.add(estadoNuevo);
							}
						}
					}
				}

				Collections.sort(nuevoEstadoListado); 
				//String nuevoEstado = getEstadoFromListado(nuevoEstadoListado);

				if (nuevoEstadoListado != null && nuevoEstadoListado.size() > 0 && estadoYaExiste(nuevoEstadoListado, nuevosEstadosTotales) == false 
						&& estadoYaExiste(nuevoEstadoListado, nuevosEstadosAgregados) == false) {

					nuevosEstadosTotales.add(nuevoEstadoListado);
					nuevosEstadosAgregados.add(nuevoEstadoListado);
					nuevaCantEstados++;
					

					//					Proyeccion proyeccion = new Proyeccion();
					//					proyeccion.setEstadoSalida(getEstadoFromListado(estadoListado));
					//					proyeccion.setSimboloInput(input);
					//					proyeccion.setEstadoLlegada(getEstadoFromListado(nuevoEstadoListado));

					//					if (proyeccionYaExiste(proyeccion, nuevasProyecciones) == false) {
					//						nuevasProyecciones.add(proyeccion);
					//					}
				}

				Proyeccion proyeccion = new Proyeccion();

				if (estadoListado != null && estadoListado.size() > 0 && input != null && input.isEmpty() == false && nuevoEstadoListado != null && nuevoEstadoListado.size() > 0) {

					proyeccion.setEstadoSalida(getEstadoFromListado(estadoListado));
					proyeccion.setSimboloInput(input);
					proyeccion.setEstadoLlegada(getEstadoFromListado(nuevoEstadoListado));
					
					if (proyeccionYaExiste(proyeccion, nuevasProyecciones) == false) {
						nuevasProyecciones.add(proyeccion);	
					}
					
				}
			}
		}

		//		if (nuevosEstadosTotales.equals(nuevosEstados) == false) {
		//			Au
		//		}

		if (nuevosEstadosTotales.equals(nuevosEstados)) {
			Automata automata = new Automata();
			automata.setCantEstados(nuevaCantEstados);
			//			automata.setEstadosFinales(estadosFinales); //TODO
			automata.setProyecciones(nuevasProyecciones);
			automata.setCantEstados(nuevaCantEstados);
			automata.setSimbolosInput(getSimbolosInputSinEpsilon(simbolosInput));

			return automata;
		}
		else {
			return getAutomata(nuevosEstadosTotales, nuevosEstadosAgregados, proyeccionesOriginales, nuevasProyecciones, simbolosInput, cantEstados, nuevaCantEstados);
		}
	}

	private List<String> getEstadosALosQueLlego(String estadoSalida, String input, List<Proyeccion> proyecciones, int cantEstados){

		List<String> estadosALosQueLlego = new ArrayList<String>();
		List<String> ultimosEstadosAgregados = new ArrayList<String>();
		List<String> response = null;
		List<String> response2 = null;

		for (Proyeccion proyeccion : proyecciones) {
			if (estadoSalida.equals(proyeccion.getEstadoSalida()) && input.equals(proyeccion.getSimboloInput())) {
				ultimosEstadosAgregados.add(proyeccion.getEstadoLlegada());
			}
		}

		if (ultimosEstadosAgregados != null && ultimosEstadosAgregados.size() > 0) {
			response = getEstadosALosQueLlegoConEpsilon(estadosALosQueLlego, ultimosEstadosAgregados, proyecciones, cantEstados);

			for(String estado : ultimosEstadosAgregados) {
				if (response.contains(estado) == false) {
					response.add(estado);
				}
			}
		}

		return response;
	}

	/**
	 * En el primer llamado, estadosLlegada contiene el estado de salida
	 **/
	private List<String> getEstadosALosQueLlegoConEpsilon(List<String> estadosALosQueLlego, List<String> ultimosEstadosAgregados, List<Proyeccion> proyecciones, int cantEstados){

		List<String> nuevosEstadosALosQueLlego = new ArrayList<String>(estadosALosQueLlego);
		List<String> nuevosEstadosAgregados = new ArrayList<String>();
		//		nuevosEstadosALosQueLlego.add("5");
		//		cantEstados = 7;

		for(String estado : ultimosEstadosAgregados) {
			for (Proyeccion proyeccion : proyecciones) {
				if (estado.equals(proyeccion.getEstadoSalida()) && proyeccion.getSimboloInput().equals(EPSILON) && estadoYaExiste(proyeccion.getEstadoLlegada(), nuevosEstadosALosQueLlego) == false
						&& estadoYaExiste(proyeccion.getEstadoLlegada(), nuevosEstadosAgregados) == false) {
					nuevosEstadosALosQueLlego.add(proyeccion.getEstadoLlegada());
					nuevosEstadosAgregados.add(proyeccion.getEstadoLlegada());
				}
			}
		}

		if (nuevosEstadosALosQueLlego.equals(estadosALosQueLlego) == false && nuevosEstadosALosQueLlego.size() != cantEstados) {
			return getEstadosALosQueLlegoConEpsilon(nuevosEstadosALosQueLlego, nuevosEstadosAgregados, proyecciones, cantEstados);
		}
		else {
			return nuevosEstadosALosQueLlego;
		}
	}

	private boolean estadoYaExiste(String estadoAChequear, List<String> estados) {

		boolean response = false;

		for (String estado : estados) {
			if (estado.equals(estadoAChequear)){
				response = true;
			}
		}

		return response;
	}

	private boolean estadoYaExiste(List<String> estadoAChequear, List<List<String>> estados) {

		boolean response = false;

		for (List<String> estado : estados) {
			if (estado.equals(estadoAChequear)){
				response = true;
			}
		}

		return response;
	}

	private boolean proyeccionYaExiste(Proyeccion proyeccionAChequear, List<Proyeccion> proyecciones) {

		boolean response = false;

		//TODO chequear el tema del equals
		for (Proyeccion proyeccion : proyecciones) {
			if (proyeccion.equals(proyeccionAChequear)){
				return true;
				
			}
		}

		return response;
	}

	private List<String> getSimbolosInputSinEpsilon(List<String> simbolosInput){

		List<String> response = new ArrayList<String>();				

		for (String simbolo : simbolosInput) {
			if (simbolo.equals(EPSILON) == false) {
				response.add(simbolo);
			}
		}

		return response;
	}

	private String getEstadoFromListado(List<String> nuevoEstadoInicialListado) {
		return nuevoEstadoInicialListado.stream()
				.map(n -> n)
				.collect(Collectors.joining("", "{", "}"));
	}

	private boolean poseeTransicionEpsilon(String estadoSalida, Automata automata) {

		boolean response = false;

		for (Proyeccion proyeccion : automata.getProyecciones()) {
			if (proyeccion.getEstadoSalida().equals(estadoSalida) && proyeccion.getSimboloInput().equals(EPSILON)) {
				response = true;
			}
		}		

		return response;
	}
}
