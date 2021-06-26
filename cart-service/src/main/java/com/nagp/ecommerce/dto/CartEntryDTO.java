package com.nagp.ecommerce.dto;

import com.nagp.ecommerce.constants.Currency;

import lombok.Data;

@Data
public class CartEntryDTO {

	private int entryNumber;

	private String code;
	
	private String name;

	private int quantity;

	private Double basePrice;

	private Double totalPrice;

	private Currency currency;

	private Double discount;

	private Double tax;

}
