package com.nagp.amcart.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagp.amcart.order.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long Id);

    Optional<Order> findByOrderNumber(String orderNumber);

}
