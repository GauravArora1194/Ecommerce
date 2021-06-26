package com.nagp.ecommerce.enums;

public enum ColorVariant {

	RED("RED"), GREEN("GREEN"), BLUE("BLUE"), BLACK("BLACK");

	private String color;

	private ColorVariant(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}
}
