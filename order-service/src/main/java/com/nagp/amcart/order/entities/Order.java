package com.nagp.amcart.order.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nagp.amcart.order.enums.OrderStatus;

import lombok.Data;

@Entity
@Table(name = "amcart_order")
@Data
public class Order extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Double totalPrice;

    private String currency;

    private String userId;

    private Long paymentId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;
}
