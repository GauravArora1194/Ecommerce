package com.nagp.ecommerce.dto;

import lombok.Data;

@Data
public class ProductDTO {

	private String code;

	private String name;

	private Double price;

	private Double tax;

	private Double discount;

	private String description;

	private int stock;

	private String brand;

}
