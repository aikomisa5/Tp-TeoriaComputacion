package exceptions;

public class BadFileException extends Exception{

	private static final long serialVersionUID = -333252470920400860L;
	
	private String mensajeDeError;
	
	public BadFileException() {
		super();
	}

	public BadFileException(String message) {
		super(message);
	}
	
	public BadFileException(String message, Exception e) {
		super(message, e);
	}	

	public String getMensajeDeError() {
		return mensajeDeError;
	}

	public void setMensajeDeError(String mensajeDeError) {
		this.mensajeDeError = mensajeDeError;
	}
}
