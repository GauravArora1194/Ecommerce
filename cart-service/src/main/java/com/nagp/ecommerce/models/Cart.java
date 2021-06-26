package com.nagp.ecommerce.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nagp.ecommerce.constants.CartStatus;
import com.nagp.ecommerce.constants.Currency;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Cart extends Audit {

	@Column(nullable = false, unique = true)
	private String number;

	@Column(nullable = false)
	private String userId;

	@Enumerated(EnumType.STRING)
	private Currency currency;

	private Date date;

	private Double totalPrice;

	private Double totalTax;

	private Double discount;

	private Double deliveryCost;

	private Double subTotal;

	@Enumerated(EnumType.STRING)
	private CartStatus status;

	@OneToMany(mappedBy = "cart")
	private List<CartEntry> entries;

}
