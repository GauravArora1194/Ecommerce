package com.nagp.amcart.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagp.amcart.order.entities.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

}
