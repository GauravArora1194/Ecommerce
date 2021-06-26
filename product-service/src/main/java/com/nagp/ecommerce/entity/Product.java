package com.nagp.ecommerce.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Product extends Audit {

	private String code;

	private String name;

	private Double price;

	private Double tax;

	private Double discount;

	private Integer stock;

	private String description;

	private String brand;

	private String imageUrl;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_code", referencedColumnName = "code"))
	private Set<Category> categories;

}
