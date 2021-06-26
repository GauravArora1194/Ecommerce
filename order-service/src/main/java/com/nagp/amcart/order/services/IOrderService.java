package com.nagp.amcart.order.services;

import java.util.List;

import com.nagp.amcart.order.dto.OrderDTO;
import com.nagp.amcart.order.enums.OrderStatus;

/**
 * The Interface IOrderService.
 */
public interface IOrderService {

    /**
     * Gets the all user orders.
     *
     * @param userId
     *            the user id
     * @return the all user orders
     */
    List<OrderDTO> getAllUserOrders(Long userId);

    /**
     * Gets the order details.
     *
     * @param id
     *            the id
     * @return the order details
     */
    OrderDTO getOrderDetails(String id);

    /**
     * Creates the order.
     *
     * @param orderDTO
     *            the order DTO
     * @return the order DTO
     */
    OrderDTO createOrder(OrderDTO orderDTO);

    /**
     * Edits the order.
     *
     * @param orderId
     *            the order id
     * @param status
     *            the status
     * @return the order DTO
     */
    OrderDTO editOrder(String orderId, OrderStatus status);

}
