package de.ravenfly.mle.modulebase;

public class DataException extends Exception {

	private static final long serialVersionUID = -8285444476235444160L;

	public DataException() {
		super();
	}

	public DataException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataException(String message) {
		super(message);
	}

	public DataException(Throwable cause) {
		super(cause);
	}
}
