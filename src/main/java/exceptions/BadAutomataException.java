package exceptions;

public class BadAutomataException extends Exception{

	private static final long serialVersionUID = 4276962061391669524L;
	
	private String mensajeDeError;
	
	public BadAutomataException() {
		super();
	}

	public BadAutomataException(String message) {
		super(message);
	}
	
	public BadAutomataException(String message, Exception e) {
		super(message, e);
	}	

	public String getMensajeDeError() {
		return mensajeDeError;
	}

	public void setMensajeDeError(String mensajeDeError) {
		this.mensajeDeError = mensajeDeError;
	}
}
