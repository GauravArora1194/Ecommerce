package com.nagp.ecommerce.constants;

public enum CartStatus {

	CREATED("CREATED"), LOCKED("LOCKED"), PAYMENT_IN_PROGRESS("PAYMENT_IN_PROGRESS"), CANCELLED("CANCELLED");

	private String code;

	private CartStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
