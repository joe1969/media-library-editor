package de.ravenfly.mle.modulebase;

public class DataHandlerException extends Exception {

	private static final long serialVersionUID = -8285444476235444160L;

	public DataHandlerException() {
		super();
	}

	public DataHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataHandlerException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataHandlerException(String message) {
		super(message);
	}

	public DataHandlerException(Throwable cause) {
		super(cause);
	}
}
