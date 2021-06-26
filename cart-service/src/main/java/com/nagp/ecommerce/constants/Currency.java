package com.nagp.ecommerce.constants;

public enum Currency {

	INR("INR"), USD("USD"), EUR("EUR"), AUD("AUD");

	private String code;

	private Currency(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
