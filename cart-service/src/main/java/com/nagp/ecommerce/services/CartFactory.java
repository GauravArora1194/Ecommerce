package com.nagp.ecommerce.services;

import com.nagp.ecommerce.dto.ProductDTO;
import com.nagp.ecommerce.models.Cart;
import com.nagp.ecommerce.models.CartEntry;

public interface CartFactory {

	Cart createCart(String userName);
	
	CartEntry createCartEntry(Cart cart, ProductDTO product, int quantity);
	
	CartEntry updateCartEntry(CartEntry entry, ProductDTO product, int quantity);
}
