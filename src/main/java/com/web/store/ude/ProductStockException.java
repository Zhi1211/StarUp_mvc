package com.web.store.ude;

public class ProductStockException extends RuntimeException {

	public ProductStockException() {
		super();
	}

	public ProductStockException(String message) {
		super(message);
	}

	public ProductStockException(Throwable cause) {
		super(cause);
	}

	public ProductStockException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductStockException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
