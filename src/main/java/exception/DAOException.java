package exception;

public class DAOException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public DAOException(String message) {
		this.message = message;
	}
}
