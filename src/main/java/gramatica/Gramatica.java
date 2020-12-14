package gramatica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gramatica {

    private List<Produccion> producciones;
    private String simboloInicial = "S";

    private static final String terminalSinEpsilonPatternString = "[a-df-z]";

    public Gramatica() {
        super();
        producciones = new ArrayList<Produccion>();
    }

    public Gramatica(List<Produccion> producciones) {
        super();
        this.producciones = producciones;
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void setProducciones(List<Produccion> producciones) {
        this.producciones = producciones;
    }

    public String getEstadoInicial() {
        return simboloInicial;
    }

    public void setEstadoInicial(String estadoInicial) {
        this.simboloInicial = estadoInicial;
    }

    @Override
    public String toString() {
        return "Gramatica{" +
                "producciones=" + producciones +
                ", simboloInicial='" + simboloInicial + '\'' +
                '}';
    }
    
    public boolean isInFNC()
    {
        for(Produccion produccion : getProducciones()){
            if (produccion.getSimbolos().size() == 1){ // Caso en el que podría estar compuesto por un terminal.
               if (!esSimboloTerminalNoEpsilon(produccion.getSimbolos().get(0)))
                   return false;
            }
            else if (produccion.getSimbolos().size() == 2){ // si son dos simbolos y hay algun terminal, return false.
                boolean acumulador = false;
                for (Character simbolo : produccion.getSimbolos())
                    acumulador = acumulador || esSimboloTerminalNoEpsilon(simbolo);
                if (acumulador)
                    return false;
            }
            else
                return false;
        }
        return true;
    }

    public void eliminarE() {

         List<Character> N = new ArrayList<>();

         //1º Agrego los simbolos que derivan directamente vacio
         for (Produccion p : getProducciones()) {
             boolean isNullable = false;

             for (Character c : p.getSimbolos())
                 if (c.equals('E'))
                     isNullable = true;
             if (isNullable)
                 N.add(p.getSimboloInput().charAt(0));
         }
         //2º Verifico si existe alguna produccion que esté completamente en N
         for (Produccion p : getProducciones()) {
             boolean isNullable = true;
             for (Character c : p.getSimbolos())
                 if (!N.contains(c))
                     isNullable = false;
             if (isNullable)
                 N.add(p.getSimboloInput().charAt(0));
         }

         List<String> strings = new ArrayList<>();
         Set<Produccion> producciones = new HashSet<>();
         for (Produccion produccion : getProducciones()) {
        	 for (Character simboloNulleable : N) {
        		 strings = obtenerCombinacionesNullables(produccion, simboloNulleable);
        	 }
        	 for (String string : strings) {
        		 List<Character> simbolos = new ArrayList<>();
        		 for (int i = 0 ; i < string.length(); i++)
        			 simbolos.add(string.charAt(i));
        		 Produccion nuevaProduccion = new Produccion(produccion.getSimboloInput(),simbolos);
        		 producciones.add(nuevaProduccion);
        	 }
         }
         List<Produccion> prods = new ArrayList<>(producciones);
         if (prods.size() > 0)
        	 setProducciones(prods);
    }


    
    private List<String> obtenerCombinacionesNullables(Produccion produccion, Character simboloNulleable) {
        List<String> strings = new ArrayList<>();
    	StringBuilder str = new StringBuilder();
        for(Character simbolo: produccion.getSimbolos()) {
        	str.append(simbolo);
        }
        // queremos el que tiene todo.
        String stringSinE = str.toString().replace("E", "");
        if (stringSinE.equals("")) {
        	return strings;
        }
        strings.add(stringSinE);
        String string = str.toString();
        if(str.toString().contains(""+simboloNulleable)) {
        	 Pattern pattern = Pattern.compile("("+simboloNulleable+")");
             Matcher matcher = pattern.matcher(string);
             long count = matcher.results().count();
             matcher = pattern.matcher(string);
             while (matcher.find()) {
            	 string = matcher.replaceFirst("");
            	 strings.add(string);
                 matcher = pattern.matcher(string);
             }
             if (count > 1) {
            	 string = str.reverse().toString();
            	 
               	 pattern = Pattern.compile("("+simboloNulleable+")");
                 matcher = pattern.matcher(string);
                 while (matcher.find()) {
                	 string = matcher.replaceFirst("");
                	 StringBuilder strB = new StringBuilder(string);
                	 strings.add(strB.reverse().toString());
                     matcher = pattern.matcher(string);
                 }
             }
        }
        return strings;
    }


    public void eliminarProduccionesUnitarias() {

        Set<Par> pares = new HashSet<>();

        //Creo pares unitarios bases

        //Lados Izquierdos
        for (Produccion p : getProducciones()) {
            pares.add(new Par(p.getSimboloInput().charAt(0),
                    p.getSimboloInput().charAt(0)));
        }
        //Lados Derechos
        for (Produccion p : filtrarProduccionesUnitarias()) {
            pares.add(new Par(p.getSimbolos().get(0), p.getSimbolos().get(0)));
        }

        //Búsqueda de más pares unitarios
        pares = buscarParesUnitarios(pares);


        //Construcción de nueva gramática con los pares unitarios encontrados
        List<Produccion> newProducciones = new ArrayList<>();
        for (Produccion p : filtrarProduccionesInteresantes()) {
            for (Par par : pares) {
                Character c = p.getSimboloInput().charAt(0);
                if (c.equals(par.getDerecho()))
                    newProducciones.add(new Produccion(par.getIzquierdo().toString(),p.getSimbolos())); //sumos las nuevas
            }
        }

        //Elimino repetidos
        Set<Produccion> conjuntoProducciones = new HashSet<>();
        conjuntoProducciones.addAll(newProducciones);
        conjuntoProducciones.addAll(filtrarProduccionesInteresantes());// sumo además las interesantes ya existentes en la gramática

        List<Produccion> newProduccionesSinRepetidos = new ArrayList<>(conjuntoProducciones);

        setProducciones(newProduccionesSinRepetidos);

    }

    private Set<Par> buscarParesUnitarios(Set<Par> pares) {

        Set<Par> paresEncontrados = new HashSet<>();
        for (Par par : pares) {
            for (Produccion p : filtrarProduccionesUnitarias()) {

                if (par.getDerecho().equals(p.getSimboloInput().charAt(0)))
                    paresEncontrados.add(new Par(par.getIzquierdo(), p.getSimbolos().get(0)));

            }
        }

        if (paresEncontrados.isEmpty() || esSubconjunto(paresEncontrados, pares))
            return pares;

        paresEncontrados.addAll(pares);

        return buscarParesUnitarios(paresEncontrados);

    }

    private boolean esSubconjunto(Set<Par> paresEncontrados, Set<Par> pares) {
        return pares.containsAll(paresEncontrados);
    }

    private List<Produccion> filtrarProduccionesUnitarias() {

        List<Produccion> produccionesFiltradas = new ArrayList<>();

        for (int i = 0; i < getProducciones().size(); i++) {

            boolean esVariable = Character.isUpperCase(getProducciones().get(i).getSimbolos().get(0));

            if (getProducciones().get(i).getSimbolos().size() == 1 && esVariable)
                produccionesFiltradas.add(getProducciones().get(i));
        }
        return produccionesFiltradas;
    }

    private List<Produccion> filtrarProduccionesInteresantes() {

        List<Produccion> produccionesFiltradas = new ArrayList<>();

        for (int i = 0; i < getProducciones().size(); i++) {

            boolean esVariable = Character.isUpperCase(getProducciones().get(i).getSimbolos().get(0));

            if (getProducciones().get(i).getSimbolos().size() > 1 || !esVariable)
                produccionesFiltradas.add(getProducciones().get(i));
        }
        return produccionesFiltradas;
    }


    public void eliminarSimbolosNoGeneradores() {
        StringBuilder toPattern = new StringBuilder("");
        for (Produccion produccion : getProducciones()) {
            // identifico los simbolos terminales en mi caso base.
            boolean derivaTodosTerminales = true;

            for (Character simbolo : produccion.getSimbolos()){
                boolean esTerminal = esSimboloTerminalNoEpsilon(simbolo);

                derivaTodosTerminales = derivaTodosTerminales && esTerminal;
                if (esTerminal)
                    toPattern.append(simbolo);
            }
            if (derivaTodosTerminales)
                // hago esto porque por algun motivo el simbolo input no es un char.
                toPattern.append(produccion.getSimboloInput().charAt(0));
        }
        Pattern pattern1 = Pattern.compile("[" + toPattern.toString() + "]+");

        for (int i = 0; i < toPattern.toString().length(); i++) {
            for (Produccion produccion : getProducciones()) {
                String produccionEnString = "";
                for (Character simboloProduccion : produccion.getSimbolos())
                    produccionEnString = produccionEnString.concat("" + simboloProduccion);
                Matcher matcher = pattern1.matcher(produccionEnString);
                if (matcher.matches()) {
                    if (!toPattern.toString().contains(produccion.getSimboloInput()))
                        toPattern.append(produccion.getSimboloInput().charAt(0));
                }
            }
        }
        List<Produccion> produccionesDeSimbolosGeneradores = new ArrayList<>();
        boolean estadoInicialEstaContenido = false;
        for (Produccion produccion : getProducciones()) {
            boolean hayUnSimboloInutil = false;
            for (Character simbolo : produccion.getSimbolos())
                hayUnSimboloInutil = hayUnSimboloInutil || !toPattern.toString().contains("" + simbolo);
            if (toPattern.toString().contains(produccion.getSimboloInput()) && !hayUnSimboloInutil) {
                if (produccion.getSimboloInput().equals(simboloInicial))
                    estadoInicialEstaContenido = true;
                produccionesDeSimbolosGeneradores.add(produccion);
            }

        }
        //Si no hubiese estado inicial, significa que esta gramatica no forma ningun string
        setProducciones(estadoInicialEstaContenido ? produccionesDeSimbolosGeneradores : new ArrayList<>());
    }

    public boolean esSimboloTerminalNoEpsilon(Character simbolo){
        Pattern pattern = Pattern.compile(terminalSinEpsilonPatternString);
        Matcher matcher = pattern.matcher(""+simbolo);

        return matcher.matches();
    }

    public void eliminarSimbolosNoAlcanzables() {
        StringBuilder alcanzables = new StringBuilder(simboloInicial);
        Pattern pattern1 = Pattern.compile("[" + alcanzables.toString() + "]+");

        for (int i = 0; i < alcanzables.toString().length(); i++) {
            for (Produccion produccion : getProducciones()) {
                Matcher matcher = pattern1.matcher(produccion.getSimboloInput());
                if (matcher.matches()) {
                    for (Character simbolo : produccion.getSimbolos()) {
                        if (!alcanzables.toString().contains("" + simbolo)) {
                            alcanzables.append(simbolo);
                            pattern1 = Pattern.compile("[" + alcanzables.toString() + "]+");
                        }
                    }
                }
            }
        }
        List<Produccion> produccionesDeSimbolosNoAlcanzables = new ArrayList<>();
        for (Produccion produccion : getProducciones()) {
            boolean hayUnSimboloInutil = false;
            if (alcanzables.toString().contains(produccion.getSimboloInput())) {
                for (Character simbolo : produccion.getSimbolos()) {
                    hayUnSimboloInutil = hayUnSimboloInutil || !alcanzables.toString().contains("" + simbolo);
                }
                if (!hayUnSimboloInutil)
                    produccionesDeSimbolosNoAlcanzables.add(produccion);
            }
        }
        setProducciones(produccionesDeSimbolosNoAlcanzables);
    }
    
	public int buscarIndiceProduccion(String simbolo) {
		int indice = -1;
		for(int i = 0 ; i < getProducciones().size() ; i++) {
			if(getProducciones().get(i).getSimboloInput().equals(simbolo)) {
				indice = i;
			}
		}
		return indice;
	}
}