package com.nagp.ecommerce.mapper;

import com.nagp.ecommerce.dto.CartDTO;
import com.nagp.ecommerce.models.Cart;

public interface CartMapper {

	CartDTO map(Cart cart);

	 // Cart map(CartDTO cartDTO);
}
