package com.nagp.ecommerce.dto;

import java.util.Date;
import java.util.List;

import com.nagp.ecommerce.constants.CartStatus;
import com.nagp.ecommerce.constants.Currency;

import lombok.Data;

@Data
public class CartDTO {

	private String number;

	private String userId;

	private Currency currency;

	private Date date;

	private Double totalPrice;

	private Double totalTax;

	private Double discount;

	private Double deliveryCost;

	private Double subTotal;

	private CartStatus status;

	private List<CartEntryDTO> entries;

}
