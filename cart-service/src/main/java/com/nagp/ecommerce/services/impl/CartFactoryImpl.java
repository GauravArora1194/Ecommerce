package com.nagp.ecommerce.services.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nagp.ecommerce.constants.CartStatus;
import com.nagp.ecommerce.constants.Currency;
import com.nagp.ecommerce.dto.ProductDTO;
import com.nagp.ecommerce.models.Cart;
import com.nagp.ecommerce.models.CartEntry;
import com.nagp.ecommerce.services.CartFactory;

@Service
public class CartFactoryImpl implements CartFactory {

	@Override
	public Cart createCart(String userName) {

		return createCartInternal(userName);

	}

	@Override
	public CartEntry createCartEntry(Cart cart, ProductDTO product, int quantity) {

		return createCartEntryInternal(cart, product, quantity);

	}

	@Override
	public CartEntry updateCartEntry(CartEntry entry, ProductDTO product, int quantity) {

		entry.setQuantity(entry.getQuantity() + quantity);
		entry.setTotalPrice(Double.valueOf(entry.getQuantity() * entry.getBasePrice()));
		entry.setTax(Double.valueOf(entry.getQuantity() * product.getTax()));
		entry.setDiscount(Double.valueOf(entry.getQuantity() * product.getDiscount()));

		return entry;
	}

	private Cart createCartInternal(String userName) {

		Cart cart = new Cart();
		cart.setNumber(UUID.randomUUID().toString());
		cart.setStatus(CartStatus.CREATED);
		cart.setCurrency(Currency.INR);
		cart.setDate(new Date());
		cart.setTotalPrice(0.0d);
		cart.setDiscount(0.0d);
		cart.setDeliveryCost(0.0d);
		cart.setSubTotal(0.0d);
		cart.setTotalTax(0.0d);

		cart.setUserId(userName);

		return cart;

	}

	private CartEntry createCartEntryInternal(Cart cart, ProductDTO product, int quantity) {

		CartEntry cartEntry = new CartEntry();

		cartEntry.setEntryNumber(cart.getEntries().isEmpty() ? 0 : cart.getEntries().size());
		cartEntry.setCurrency(Currency.INR);
		cartEntry.setCode(product.getCode());
		cartEntry.setName(product.getName());
		cartEntry.setQuantity(quantity);
		cartEntry.setBasePrice(product.getPrice());
		cartEntry.setTotalPrice(Double.valueOf(quantity * cartEntry.getBasePrice()));
		cartEntry.setTax(Double.valueOf(quantity * product.getTax()));
		cartEntry.setCart(cart);
		cartEntry.setDiscount(Double.valueOf(quantity * product.getDiscount()));

		return cartEntry;

	}

}
