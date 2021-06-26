package com.nagp.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagp.ecommerce.models.CartEntry;

public interface CartEntryJpaRepository extends JpaRepository<CartEntry, Long> {

}
