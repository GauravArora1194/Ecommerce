package com.nagp.amcart.order.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagp.amcart.order.dto.OrderDTO;
import com.nagp.amcart.order.entities.Order;
import com.nagp.amcart.order.entities.OrderDetails;
import com.nagp.amcart.order.enums.OrderStatus;
import com.nagp.amcart.order.exception.AmCartBusinessException;
import com.nagp.amcart.order.mapper.OrderMapper;
import com.nagp.amcart.order.repository.OrderRepository;
import com.nagp.amcart.order.services.IOrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderDTO> getAllUserOrders(Long userId) {
        List<Order> userOrders = this.orderRepository.findByUserId(userId);
        return this.orderMapper.convertToDTOList(userOrders);
    }

    @Override
    public OrderDTO getOrderDetails(String id) {
        Order order = getOrderByOrderID(id);
        OrderDTO orderDTO = this.orderMapper.convertToDto(order);

        return orderDTO;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = this.orderMapper.convertToEntity(orderDTO);
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setStatus(OrderStatus.CREATED);
        
        if(null != order.getOrderDetails() && !order.getOrderDetails().isEmpty())
        {
        	for (OrderDetails orderDetails : order.getOrderDetails()) {
                orderDetails.setOrder(order);
            }
        }
        
        order = this.orderRepository.save(order);
        log.debug("Created new order with order ID : {}", order.getId());
        return this.orderMapper.convertToDto(order);
    }

    @Override
    public OrderDTO editOrder(String orderId, OrderStatus status) {
        Order order = getOrderByOrderID(orderId);
        order.setStatus(status);
        order = this.orderRepository.save(order);
        return this.orderMapper.convertToDto(order);
    }

    private Order getOrderByOrderID(String id) {
        Optional<Order> orderOptional = this.orderRepository.findByOrderNumber(id);
        if (!orderOptional.isPresent()) {
            log.error("Order with order ID : {} not found in database", id);
            throw new AmCartBusinessException("Order with order id : " + id + " not found", "error.notFound.order");
        }
        return orderOptional.get();
    }

}
