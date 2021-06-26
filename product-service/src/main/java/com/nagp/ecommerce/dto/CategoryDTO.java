package com.nagp.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = -6883879160725399178L;

	private String code;

	private String name;

	private String description;

	private Set<CategoryDTO> superCategories;

}
