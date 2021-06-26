package com.nagp.ecommerce.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagp.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByCode(String code);
}
