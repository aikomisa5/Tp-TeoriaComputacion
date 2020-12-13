package automata;

import exceptions.BadTransicionException;

public class Transicion implements Comparable<Transicion> {

	private String estadoSalida;
	private String simboloInput;
	private String estadoLlegada;
	
	public Transicion() {
		super();
	}

	public Transicion(String estadoSalida, String simboloInput, String estadoLlegada) throws BadTransicionException {
		super();		
		
		if (estadoSalida == null || simboloInput == null || estadoLlegada == null) {
			String msj = "Error. No se puede enviar como parametro del constructor un null";
			System.out.println(msj);
			throw new BadTransicionException(msj);
		}
		
		this.estadoSalida = estadoSalida;
		this.simboloInput = simboloInput;
		this.estadoLlegada = estadoLlegada;
	}

	public String getEstadoSalida() {
		return estadoSalida;
	}

	public void setEstadoSalida(String estadoSalida) {
		this.estadoSalida = estadoSalida;
	}

	public String getSimboloInput() {
		return simboloInput;
	}

	public void setSimboloInput(String simboloInput) {
		this.simboloInput = simboloInput;
	}

	public String getEstadoLlegada() {
		return estadoLlegada;
	}

	public void setEstadoLlegada(String estadoLlegada) {
		this.estadoLlegada = estadoLlegada;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        
        final Transicion proyeccion = (Transicion) obj;
        
        if (this.estadoSalida.equals(proyeccion.getEstadoSalida()) == false) {
        	return false;
        }
        
        if (this.simboloInput.equals(proyeccion.getSimboloInput()) == false) {
        	return false;
        }
        
        if (this.estadoLlegada.equals(proyeccion.getEstadoLlegada()) == false) {
        	return false;
        }
		
		return true;
	}

	@Override
	public int compareTo(Transicion o) {
		int result1 = this.estadoSalida.compareTo(o.estadoSalida);
		
		if (result1 != 0) {
			return result1;
		}
		
		int result2 = this.simboloInput.compareTo(o.simboloInput);
		
		if (result2 != 0) {
			return result2;
		}
		
		return this.estadoLlegada.compareTo(o.estadoLlegada);
	}
}
