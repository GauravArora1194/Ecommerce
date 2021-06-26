package com.nagp.ecommerce.services;

import com.nagp.ecommerce.dto.ProductDTO;
import com.nagp.ecommerce.models.Cart;

public interface CartService {

	Cart fetchCart(final String cartId);

	Cart createCart(String userName);

	void deleteCart(Long cartId);

	void addProductToCart(ProductDTO product, int quantity, String cartId);

	void removeProductFromCart(ProductDTO product, int quantity, String cartId);

	Cart recalculateCart(Long cartId);

	Cart recalculateCart(Cart cart);

	Cart getCartForUser(String userId);
}
