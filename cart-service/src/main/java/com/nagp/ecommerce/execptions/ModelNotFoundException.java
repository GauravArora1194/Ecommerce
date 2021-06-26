package com.nagp.ecommerce.execptions;

public class ModelNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7121852963753449263L;

	public ModelNotFoundException(String message) {
		super(message);
	}

}
