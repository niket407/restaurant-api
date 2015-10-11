package restaurant.exception;

public class AppException extends Exception {
	
	private static final long serialVersionUID = 8873029477120202435L;

	public AppException(String message) {
		super(message);
	}
	
	public AppException (String message, Throwable cause) {
		super(message, cause);
	}
}
