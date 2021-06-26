package com.nagp.amcart.order.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "amcart_order_details")
@Data
public class OrderDetails extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double price;

    private String currency;

    private Double discount;

    private Double tax;

    private Double finalSellPrice;

    private Long productId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
