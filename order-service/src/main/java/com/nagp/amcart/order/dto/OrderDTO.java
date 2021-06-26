package com.nagp.amcart.order.dto;

import java.util.List;

import com.nagp.amcart.order.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderDTO {

    private String orderNumber;

    private OrderStatus status;

    private Double totalPrice;

    private String currency;

    private String userId;

    private Long paymentId;

    private List<OrderDetailsDTO> orderDetails;

}
