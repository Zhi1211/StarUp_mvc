package com.web.store.ude;

public class MyUserDefinedException extends RuntimeException {

	public MyUserDefinedException() {
		super();
	}

	public MyUserDefinedException(String message) {
		super(message);
	}

	public MyUserDefinedException(Throwable cause) {
		super(cause);
	}

	public MyUserDefinedException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyUserDefinedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
