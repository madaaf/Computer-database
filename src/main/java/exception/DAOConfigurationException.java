package exception;

public class DAOConfigurationException extends RuntimeException {
	private String message;

	public DAOConfigurationException(String message) {
		this.message = message;
	}
}
