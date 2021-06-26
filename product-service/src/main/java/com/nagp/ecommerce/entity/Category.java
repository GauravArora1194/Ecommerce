package com.nagp.ecommerce.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {

	@Id
	private String code;

	private String name;

	private String description;

	@ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private Set<Product> products;

	@ManyToMany
	@JoinTable(name = "category_category")
	private Set<Category> superCategories;

}
