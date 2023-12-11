package br.com.senac.exception;

public class BOException extends Exception {

	private static final long serialVersionUID = -4422333424813589894L;

	public BOException() {
	}

	public BOException(String message) {
		super(message);
	}

	public BOException(Throwable cause) {
		super(cause);
	}

	public BOException(String message, Throwable cause) {
		super(message, cause);
	}

	public BOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
