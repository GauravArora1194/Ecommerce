package com.nagp.ecommerce.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class ProductDTO implements Serializable {

	private static final long serialVersionUID = -2025832316612380966L;

	private String code;

	private String name;

	private Double price;

	private Double tax;

	private Double discount;

	private String description;

	// private List<ColorVariant> colorVariants;

	private Integer stock;

	private String brand;

	private String imageUrl;

	private Set<CategoryDTO> categories;

}
