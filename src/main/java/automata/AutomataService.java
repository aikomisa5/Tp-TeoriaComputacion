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

import exceptions.BadAutomataException;
import exceptions.BadFileException;

public class AutomataService {

	public static final String ERROR_CANT_ESTADOS = "Ocurrio un error al intentar obtener el dato de la cantidad de estados del archivo: ";
	public static final String ERROR_EMPTY_FILE = "Ocurrio un error al intentar obtener los datos del automata del archivo, debido a que el archivo esta vacio";
	private static final String patternSimbolosInput = "([a-zA-Z0-9]+,\\s)*[a-zA-Z0-9]{1}";
	public static final String formatoSimbolosInput = "SimboloInput, SimboloInput, SimboloInput... Ej1: a, b, c. Ej2: a";
	private static final String patternEstadosFinales = "([0-9]+,\\s)*[0-9]{1}";
	public static final String formatoEstadosFinales = "EstadoFinal, EstadoFinal, EstadoFinal... Ej1: 1, 2, 3. Ej2: 1";
	private static final String patternTransicion = "[1-9]+,\\s[a-zA-Z]+\\s->\\s[1-9]+";
	public static final String formatoTransicion = "NumeroEstadoSalida, SimboloInput -> NumeroEstadoLlegada. Ej: 4, E -> 4";
	private static final String EPSILON = "E";
	private static final String ESTADO_INICIAL = "1";
	private static final String ESTADO_TRAMPA = "T";
	private static final String LINEA = "----------------------------------------";

	/**
	 * Metodos publicos
	 **/
	
	
	/**
	 * Lee el AFND-e desde el .txt 
	 **/
	public Automata getAFNDFromTxtFile(String fileName) throws BadFileException, FileNotFoundException {
		return readAFNDFromTxtFile(fileName);
	}

	public Automata getAFD(List<Transicion> transicionesOriginales, List<String> simbolosInput, List<String> estadosFinales, int cantEstados) {

		/**
		 * AFND-e
		 **/
		
		/**
		 * Cargo el estado inicial 1 para el AFND-e 
		 **/
		List<String> estadosALosQueLlego = new ArrayList<String>();
		estadosALosQueLlego.add(ESTADO_INICIAL);

		/**
		 * Verifico a donde llego con el estado 1 a traves de transiciones Epsilon
		 **/
		List<String> ultimosEstadosAgregados = new ArrayList<String>();
		ultimosEstadosAgregados.add(ESTADO_INICIAL);
		List<String> nuevoEstadoInicialListado = getEstadosALosQueLlegoConEpsilon(estadosALosQueLlego, ultimosEstadosAgregados, transicionesOriginales, cantEstados);
		Collections.sort(nuevoEstadoInicialListado);
		
		/**
		 * AFD 
		 **/
		List<Transicion> nuevasTransiciones = new ArrayList<Transicion>();
		List<List<String>> nuevosEstados = new ArrayList<List<String>>();
		List<List<String>> ultimosNuevosEstados = new ArrayList<List<String>>();
		int nuevaCantEstados = 1;
		
		/**
		 * Cargo el nuevo estado inicial del AFD 
		 **/
		ultimosNuevosEstados.add(nuevoEstadoInicialListado);
		nuevosEstados.add(nuevoEstadoInicialListado);

		/**
		 * Empiezo a armar el AFD a partir del nuevo estado inicial
		 **/
		Automata afd = getAutomata(nuevosEstados, ultimosNuevosEstados, transicionesOriginales, nuevasTransiciones, simbolosInput, estadosFinales, cantEstados, nuevaCantEstados);

		List<String> estadosAFD = new ArrayList<String>();

		for (List<String> estadoListado : afd.getEstadosListado()) {
			estadosAFD.add(getEstadoFromListado(estadoListado));
		}

		/*** Seteo el estado inicial como String en vez de como listado ***/
		afd.setEstadoInicial(getEstadoFromListado(nuevoEstadoInicialListado));
		afd.setTransiciones(completarTransicionesAEstadoTrampa(afd.getTransiciones(), estadosAFD, afd.getSimbolosInput()));

		/*** Verifico si alguna transicion tiene estado trampa. Si es asi, entonces aumento la cantidad de estados del AFD y agrego el estado trampa al listado ***/
		if (automataPoseeEstadoTrampa(afd.getTransiciones()) == true) {
			int cantEstadosActual =  afd.getCantEstados();
			afd.setCantEstados(cantEstadosActual + 1);

			List<List<String>> estadosListadoActual = new ArrayList<List<String>>(afd.getEstadosListado());
			List<String> estadoTrampaListado = new ArrayList<String>();
			estadoTrampaListado.add(ESTADO_TRAMPA);
			estadosListadoActual.add(estadoTrampaListado);
			afd.setEstadosListado(estadosListadoActual);
		}
		
		ordenarAutomata(afd);
		
		return afd;
	}

	public boolean cadenaPerteneceAlAFD(String cadena, Automata afd) throws BadAutomataException {

		if (afd == null) {
			throw new BadAutomataException("Ha ocurrido un error. El AFD no puede ser nulo");
		}
		if (afd.getEstadoInicial() == null || afd.getEstadoInicial().isEmpty() == true) {
			throw new BadAutomataException("Ha ocurrido un error. El AFD debe tener un estado inicial");
		}
		if (afd.getEstadosFinales() == null || afd.getEstadosFinales().size() == 0) {
			throw new BadAutomataException("Ha ocurrido un error. El AFD debe tener al menos un estado final");
		}
		if (afd.getTransiciones() == null || afd.getTransiciones().size() == 0) {
			throw new BadAutomataException("Ha ocurrido un error. El AFD debe tener al menos una transicion");
		}

		boolean response = false;

		List<String> cadenaListado = new ArrayList<>();

		for (char ch : cadena.toCharArray()) { 
			cadenaListado.add(String.valueOf(ch));
		}

		int largoCadena = cadenaListado.size();
		int contador = 0;

		String ultimoEstado = afd.getEstadoInicial();

		if (cadenaListado.size() == 0 && afd.getEstadosFinales().contains(ultimoEstado)) {
			return true;
		}

		/**
		 * Recorro la cadena y caracter a caracter verifico si existe una transicion. Si llego al ultimo caracter, y el estado de llegada es final, acepto la cadena 
		 **/
		
		for (String input : cadenaListado) {

			boolean match = false;

			for (Transicion transicion : afd.getTransiciones()) {
				if (transicion.getEstadoSalida().equals(ultimoEstado) && transicion.getSimboloInput().equals(input) && transicion.getEstadoLlegada().equals(ESTADO_TRAMPA) == false) {
					match = true;
					ultimoEstado = transicion.getEstadoLlegada();
					contador++;
					break;
				}
			}

			if (match == false) {
				return false;
			}
			if (match == true && afd.getEstadosFinales().contains(ultimoEstado) && largoCadena == contador) {
				return true;
			}
		}

		return response;
	}
	
	/**
	 * Ordeno los automatas de tal forma que no pase que 2 automatas que en realidad son iguales, solo por estar ordenados de forma de distinta, no se reconozcan 
	 **/
	public void ordenarAutomata (Automata automata) {
		
		Collections.sort(automata.getSimbolosInput());
		Collections.sort(automata.getEstadosFinales());
		Collections.sort(automata.getTransiciones());
		Collections.sort(automata.getEstados());
		Collections.sort(automata.getEstadosListado(), new ListComparator<>());
	}

	/**
	 * Metodos privados 
	 **/
	
	/**
	 * Recorro el .txt y voy armando el AFND-e
	 **/
	private Automata readAFNDFromTxtFile(String fileName) throws BadFileException, FileNotFoundException {
		Automata afnd = new Automata();

		Scanner myReader = null;
		int indice = 0;

		try {
			File myObj = new File("src/main/resources/" + fileName);
			myReader = new Scanner(myObj);

			if (myReader.hasNextLine() == false) {
				String msj = ERROR_EMPTY_FILE;
				System.out.println(msj);
				throw new BadFileException(msj);
			}

			List<Transicion> transiciones = new ArrayList<Transicion>();

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();

				if (indice == 0) {
					
					Pattern pattern = Pattern.compile(patternSimbolosInput);

					Matcher matcher = pattern.matcher(data);
					boolean matches = matcher.matches();

					if (matches == false) {
						String msj = "Ocurrio un error, los simbolos de input no cumplen con el formato necesario en el archivo. La misma debe ser de la forma: " + formatoSimbolosInput + ", y la misma es: " + data;
						System.out.println(msj);
						throw new BadFileException(msj);
					}
					
					try {
						List<String> simbolosForTrim = new ArrayList<String>(Arrays.asList(data.split(",")));
						List<String> simbolos = new ArrayList<String>();
						for(String simbolo : simbolosForTrim) {
							simbolos.add(simbolo.trim());
						}
						afnd.setSimbolosInput(simbolos);
					}
					catch(Exception e) {
						String msj = "Ocurrio un error al intentar obtener los datos de los simbolos de input del archivo: " + e.getMessage();
						System.out.println(msj);
						throw new BadFileException(msj, e);
					}
				}
				else if (indice == 1) {
					try {
						afnd.setCantEstados(Integer.valueOf(data));
					}
					catch(Exception e) {
						String msj = ERROR_CANT_ESTADOS + e.getMessage();
						System.out.println(msj);
						throw new BadFileException(msj, e);
					}
				}

				else if (indice == 2) {
					
					Pattern pattern = Pattern.compile(patternEstadosFinales);

					Matcher matcher = pattern.matcher(data);
					boolean matches = matcher.matches();

					if (matches == false) {
						String msj = "Ocurrio un error, los estados finales no cumplen con el formato necesario en el archivo. Los mismos deben ser de la forma: " + formatoEstadosFinales + ", y el mismo es: " + data;
						System.out.println(msj);
						throw new BadFileException(msj);
					}
					
					try {
						List<String> estadosFinalesForTrim = new ArrayList<String>(Arrays.asList(data.split(",")));
						List<String> estadosFinales = new ArrayList<String>();
						for(String estado : estadosFinalesForTrim) {
							estadosFinales.add(estado.trim());
						}
						afnd.setEstadosFinales(estadosFinales);
					}
					catch(Exception e) {
						String msj = "Ocurrio un error al intentar obtener los datos de los estados finales del archivo: " + e.getMessage();
						System.out.println(msj);
						throw new BadFileException(msj, e);
					}
				}
				else {
					Pattern pattern = Pattern.compile(patternTransicion);

					Matcher matcher = pattern.matcher(data);
					boolean matches = matcher.matches();

					if (matches == false) {
						String msj = "Ocurrio un error, una de las transiciones no cumple con el formato necesario en el archivo. La misma debe ser de la forma: " + formatoTransicion + ", y la misma es: " + data;
						System.out.println(msj);
						throw new BadFileException(msj);
					}

					Transicion transicion = new Transicion();

					/**
					 * Ejemplo: 4, E -> 4
					 **/

					String[] parts = data.split(",");

					transicion.setEstadoSalida(parts[0]);

					String[] parts2 = parts[1].split("->");

					transicion.setSimboloInput(parts2[0].trim());
					transicion.setEstadoLlegada(parts2[1].trim());

					transiciones.add(transicion);
				}

				indice++;
			}

			if (indice < 3) {
				String msj = "Ocurrio un error, el archivo no contenia transiciones";
				System.out.println(msj);
				throw new BadFileException(msj);
			}
			else {
				afnd.setTransiciones(transiciones);
			}
			
			ordenarAutomata(afnd);
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

		return afnd;
	}

	/**
	 * Metodo Recursivo I. Con este metodo obtengo el AFD a partir de los datos de un AFND-e
	 **/
	private Automata getAutomata(List<List<String>> nuevosEstados, List<List<String>> ultimosNuevosEstados, 
			List<Transicion> transicionesOriginales, List<Transicion> nuevasTransiciones, List<String> simbolosInput, 
			List<String> estadosFinalesOriginales, int cantEstados, int nuevaCantEstados){

		/*** Debido a que en el AFD los estados pueden estar compuestos de varios estados del AFND-e, se opto por manejar List<List<String>> ***/
		
		List<List<String>> nuevosEstadosTotales = new ArrayList<List<String>>(nuevosEstados);
		List<List<String>> nuevosEstadosAgregados = new ArrayList<List<String>>();
		
		/*** Recorro los simbolos de input. Ejemplo: a,b,c.. ***/
		for (String input : simbolosInput) {
			/*** Recorro la lista de listas de ultimos nuevos estados del AFD. Lista de lista porque en el AFD un estado puede ser del estilo: {1,2,3} ***/
			for (List<String> estadoListado : ultimosNuevosEstados) {

				List<String> nuevoEstadoListado = new ArrayList<String>();

				for (String estado : estadoListado) {
					/*** Para cada estado, verifico a donde llego. Ejemplo: {1,2,3}. Verifico a donde llego con 1, luego con 2, y luego con 3 ***/
					List<String> estadoALosQueLlego = getEstadosALosQueLlego(estado, input, transicionesOriginales, cantEstados);

					/*** Voy agregando al nuevo estado, los estados que no esten contemplados aun ***/
					if (estadoALosQueLlego != null) {
						for (String estadoNuevo : estadoALosQueLlego) {
							if (nuevoEstadoListado.contains(estadoNuevo) == false) {
								nuevoEstadoListado.add(estadoNuevo);
							}
						}
					}
				}

				/*** Al nuevo estado le hago un sort, para asegurarme de no tener un mismo estado pero invertido. Ejemplo: {1,2,3} y {3,2,1} ***/
				Collections.sort(nuevoEstadoListado); 

				/*** Si el estado nuevo aun no existe para el AFD, lo agrego ***/
				if (nuevoEstadoListado != null && nuevoEstadoListado.size() > 0 && estadoYaExiste(nuevoEstadoListado, nuevosEstadosTotales) == false 
						&& estadoYaExiste(nuevoEstadoListado, nuevosEstadosAgregados) == false) {

					nuevosEstadosTotales.add(nuevoEstadoListado);
					nuevosEstadosAgregados.add(nuevoEstadoListado);
					nuevaCantEstados++;
				}

				Transicion transicion = new Transicion();

				/*** Creo la nueva transicion entre el ultimo nuevo estado en cuestion y el nuevo estado con el input correspondiente, sino existe la transicion la agrego como nueva transicion ***/				
				if (estadoListado != null && estadoListado.size() > 0 && input != null && input.isEmpty() == false && nuevoEstadoListado != null && nuevoEstadoListado.size() > 0) {

					transicion.setEstadoSalida(getEstadoFromListado(estadoListado));
					transicion.setSimboloInput(input);
					transicion.setEstadoLlegada(getEstadoFromListado(nuevoEstadoListado));

					if (transicionYaExiste(transicion, nuevasTransiciones) == false) {
						nuevasTransiciones.add(transicion);	
					}

				}
			}
		}

		/*** Llamo recursivamente al mismo metodo para ir generando el AFD, si en algun momento no hay un nuevo estado, entonces corto la recursion y retorno el AFD ***/
		if (nuevosEstadosTotales.equals(nuevosEstados)) {
			Automata automata = new Automata();
			automata.setEstadosFinales(getEstadosFinales(nuevosEstadosTotales, estadosFinalesOriginales));
			automata.setTransiciones(nuevasTransiciones);
			automata.setCantEstados(nuevaCantEstados);
			automata.setSimbolosInput(getSimbolosInputSinEpsilon(simbolosInput));
			automata.setEstadosListado(nuevosEstados);

			return automata;
		}
		else {
			return getAutomata(nuevosEstadosTotales, nuevosEstadosAgregados, transicionesOriginales, nuevasTransiciones, simbolosInput, estadosFinalesOriginales, cantEstados, nuevaCantEstados);
		}
	}

	/**
	 * Metodo Recursivo II. Con este metodo verifico todos los estados a los que llego a partir de un estado con transiciones Epsilon
	 **/
	private List<String> getEstadosALosQueLlegoConEpsilon(List<String> estadosALosQueLlego, List<String> ultimosEstadosAgregados, List<Transicion> transiciones, int cantEstados){

		List<String> nuevosEstadosALosQueLlego = new ArrayList<String>(estadosALosQueLlego);
		List<String> nuevosEstadosAgregados = new ArrayList<String>();

		for(String estado : ultimosEstadosAgregados) {
			for (Transicion transicion : transiciones) {
				if (estado.equals(transicion.getEstadoSalida()) && transicion.getSimboloInput().equals(EPSILON) && estadoYaExiste(transicion.getEstadoLlegada(), nuevosEstadosALosQueLlego) == false
						&& estadoYaExiste(transicion.getEstadoLlegada(), nuevosEstadosAgregados) == false) {
					nuevosEstadosALosQueLlego.add(transicion.getEstadoLlegada());
					nuevosEstadosAgregados.add(transicion.getEstadoLlegada());
				}
			}
		}

		/*** Si en algun punto de la recursion no aparecen nuevos estados a los que llego, entonces corto la recursion y retorno la lista de estados a los que llego ***/
		if (nuevosEstadosALosQueLlego.equals(estadosALosQueLlego) == false && nuevosEstadosALosQueLlego.size() != cantEstados) {
			return getEstadosALosQueLlegoConEpsilon(nuevosEstadosALosQueLlego, nuevosEstadosAgregados, transiciones, cantEstados);
		}
		else {
			return nuevosEstadosALosQueLlego;
		}
	}

	/** 
	 * Verifico a que estados llego desde un estado en particular y ademas teniendo en cuenta las transiciones epsilon
	 * Si desde 1 llego a 2 con x, entonces verifico a donde llego desde 2 con epsilon
	 **/
	private List<String> getEstadosALosQueLlego(String estadoSalida, String input, List<Transicion> transiciones, int cantEstados){

		List<String> estadosALosQueLlego = new ArrayList<String>();
		List<String> ultimosEstadosAgregados = new ArrayList<String>();
		List<String> response = null;

		for (Transicion transicion : transiciones) {
			if (estadoSalida.equals(transicion.getEstadoSalida()) && input.equals(transicion.getSimboloInput())) {
				ultimosEstadosAgregados.add(transicion.getEstadoLlegada());
			}
		}

		if (ultimosEstadosAgregados != null && ultimosEstadosAgregados.size() > 0) {
			response = getEstadosALosQueLlegoConEpsilon(estadosALosQueLlego, ultimosEstadosAgregados, transiciones, cantEstados);

			for(String estado : ultimosEstadosAgregados) {
				if (response.contains(estado) == false) {
					response.add(estado);
				}
			}
		}

		return response;
	}

	/**
	 * Me fijo cuales de los nuevos estados del AFD son estados finales, a partir de la lista de estados finales del AFND-e 
	 **/
	private List<String> getEstadosFinales(List<List<String>> nuevosEstados, List<String> estadosFinalesOriginales){

		List<String> response = new ArrayList<String>();

		for(List<String> nuevoEstadoListado : nuevosEstados) {

			boolean esEstadoFinal = false;

			for(String estado : nuevoEstadoListado) {

				if (estadosFinalesOriginales.contains(estado) == true){
					esEstadoFinal = true;
				}
			}

			if (esEstadoFinal == true) {
				response.add(getEstadoFromListado(nuevoEstadoListado));
			}
		}

		return response;
	}
	
	private boolean automataPoseeEstadoTrampa(List<Transicion> transiciones) {

		boolean response = false;

		for (Transicion transicion : transiciones) {
			if (transicion.getEstadoSalida().equals(ESTADO_TRAMPA) || transicion.getEstadoLlegada().equals(ESTADO_TRAMPA)) {
				response = true;
				break;
			}
		}

		return response;
	}

	private List<Transicion> completarTransicionesAEstadoTrampa(List<Transicion> transiciones, List<String> estados, List<String> simbolosInput) {

		List<Transicion> response = new ArrayList<Transicion>(transiciones);
		boolean existeEstadoTrampa = false;

		/**
		 * Chequeo si existe una transicion para cada estado con cada simbolo de input
		 **/
		for (String simbolo : simbolosInput) {
			for (String estado : estados) {

				boolean existeTransicion = false;

				for (Transicion transicion : transiciones) {
					if (transicion.getEstadoSalida().equals(estado) && transicion.getSimboloInput().equals(simbolo)) {
						existeTransicion = true;
					}
				}

				/**
				 * Si no existe una transicion de un estado x con un simbolo y, entonces se crea una transicion a un estado trampa 
				 **/
				if (existeTransicion == false) {
					existeEstadoTrampa = true;
					Transicion nuevaTransicion = new Transicion();
					nuevaTransicion.setEstadoSalida(estado);
					nuevaTransicion.setSimboloInput(simbolo);
					nuevaTransicion.setEstadoLlegada(ESTADO_TRAMPA);
					response.add(nuevaTransicion);
				}
			}
		}

		/**
		 * Si se creo un estado trampa, genero las transiciones hacia si mismo para completar el AFD
		 **/
		if (existeEstadoTrampa == true) {
			for (String simbolo : simbolosInput) {

				Transicion transicion = new Transicion();
				transicion.setEstadoSalida(ESTADO_TRAMPA);
				transicion.setSimboloInput(simbolo);
				transicion.setEstadoLlegada(ESTADO_TRAMPA);
				response.add(transicion);
			}
		}

		return response;
	}

	/**
	 * Estado como String 
	 **/
	private boolean estadoYaExiste(String estadoAChequear, List<String> estados) {

		boolean response = false;

		for (String estado : estados) {
			if (estado.equals(estadoAChequear)){
				response = true;
			}
		}

		return response;
	}

	/**
	 * Estado como List
	 **/
	private boolean estadoYaExiste(List<String> estadoAChequear, List<List<String>> estados) {

		boolean response = false;

		for (List<String> estado : estados) {
			if (estado.equals(estadoAChequear)){
				response = true;
			}
		}

		return response;
	}

	private boolean transicionYaExiste(Transicion transicionAChequear, List<Transicion> transiciones) {

		boolean response = false;

		for (Transicion transicion : transiciones) {
			if (transicion.equals(transicionAChequear)){
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

	/**
	 * Obtengo el estado como string a partir de una lista 
	 **/
	private String getEstadoFromListado(List<String> nuevoEstadoInicialListado) {
		return nuevoEstadoInicialListado.stream()
				.map(n -> n)
				.collect(Collectors.joining(""));
	}

	public void printAutomata(Automata automata, String tipoAutomata) {
		System.out.println(LINEA);
		System.out.println(tipoAutomata);
		System.out.println(LINEA);

		String simbolosInput = "";

		int indice = 1;

		for (String simbolo : automata.getSimbolosInput()) {

			if (indice == automata.getSimbolosInput().size()) {
				simbolosInput = simbolosInput + simbolo;
			}
			else {
				simbolosInput = simbolosInput + simbolo + ", ";
			}

			indice++;
		}

		String estadosFinales = "";

		int indiceEstadosFinales = 1;

		for (String estado : automata.getEstadosFinales()) {

			if (indiceEstadosFinales == automata.getEstadosFinales().size()) {
				estadosFinales = estadosFinales + estado;
			}
			else {
				estadosFinales = estadosFinales + estado + ", ";
			}

			indiceEstadosFinales++;
		}


		System.out.println(simbolosInput);
		System.out.println(automata.getCantEstados());
		System.out.println(estadosFinales);

		automata.getTransiciones().forEach(a ->{
			System.out.println(a.getEstadoSalida() + ", " + a.getSimboloInput() + " -> " + a.getEstadoLlegada());
		});
	}
}
