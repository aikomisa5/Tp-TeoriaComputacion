package exceptions;

public class BadTransicionException extends Exception{

	private static final long serialVersionUID = -244728917462604049L;
	
	private String mensajeDeError;
	
	public BadTransicionException() {
		super();
	}

	public BadTransicionException(String message) {
		super(message);
	}
	
	public BadTransicionException(String message, Exception e) {
		super(message, e);
	}	

	public String getMensajeDeError() {
		return mensajeDeError;
	}

	public void setMensajeDeError(String mensajeDeError) {
		this.mensajeDeError = mensajeDeError;
	}
}
