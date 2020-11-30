package com.PTU.PTU.exception;

public class PTUException extends Exception {

	private static final long serialVersionUID = -1213086982083885761L;

	public PTUException() {
		super();
	}

	public PTUException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PTUException(String message, Throwable cause) {
		super(message, cause);
	}

	public PTUException(String message) {
		super(message);
	}

	public PTUException(Throwable cause) {
		super(cause);
	}

}
