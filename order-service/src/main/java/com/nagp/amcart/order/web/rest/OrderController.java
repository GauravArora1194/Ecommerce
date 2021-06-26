package com.nagp.amcart.order.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.amcart.order.dto.OrderDTO;
import com.nagp.amcart.order.enums.OrderStatus;
import com.nagp.amcart.order.services.IOrderService;

/**
 * The Class OrderController.
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    /** The order service. */
    @Autowired
    private IOrderService orderService;

    /**
     * Gets the all user orders.
     *
     * @param id
     *            the id
     * @return the all user orders
     */
    @GetMapping("/user/{userId}")
    public List<OrderDTO> getAllUserOrders(@PathVariable("userId") Long id) {
        return this.orderService.getAllUserOrders(id);
    }

    /**
     * Gets the order details.
     *
     * @param id
     *            the id
     * @return the order details
     */
    @GetMapping("/{id}")
    public OrderDTO getOrderDetails(@PathVariable("id") String id) {
        return this.orderService.getOrderDetails(id);
    }

    /**
     * Creates the order.
     *
     * @param orderDTO
     *            the order DTO
     * @return the order DTO
     */
    @PostMapping("/")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return this.orderService.createOrder(orderDTO);
    }

    /**
     * Edits the order.
     *
     * @param id
     *            the id
     * @param status
     *            the status
     * @return the order DTO
     */
    @PutMapping("/{id}")
    public OrderDTO editOrder(@PathVariable("id") String id, @RequestParam("status") OrderStatus status) {
        return this.orderService.editOrder(id, status);
    }
    
    @GetMapping("/test")
    public String testAPI() {
    	return "This API works";
    }
}
