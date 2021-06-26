package com.nagp.ecommerce.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagp.ecommerce.models.Cart;

public interface CartJpaRepository extends JpaRepository<Cart, Long> {

	Cart findByUserId(String userId);

	Optional<Cart> findByNumber(String number);
}
