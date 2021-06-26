package com.nagp.amcart.order.dto;

import lombok.Data;

@Data
public class OrderDetailsDTO {

    private Integer quantity;

    private Double price;

    private String currency;

    private Double discount;

    private Double tax;

    private Double finalSellPrice;

    private Long productId;
}
