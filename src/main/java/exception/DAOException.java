package exception;

public class DAOException extends RuntimeException {
	private String message;

	public DAOException(String message) {
		this.message = message;
	}

}
