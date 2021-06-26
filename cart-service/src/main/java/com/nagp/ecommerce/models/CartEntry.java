package com.nagp.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nagp.ecommerce.constants.Currency;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class CartEntry extends Audit {

	@Column(nullable = false)
	private int entryNumber;

	private String code;
	
	private String name;

	private int quantity;

	private Double basePrice;

	private Double totalPrice;

	@Enumerated(EnumType.STRING)
	private Currency currency;

	private Double discount;

	private Double tax;

	@ManyToOne
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;

}
