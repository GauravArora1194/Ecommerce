package com.nagp.amcart.order.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nagp.amcart.order.dto.OrderDTO;
import com.nagp.amcart.order.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO convertToDto(Order entity);

    List<OrderDTO> convertToDTOList(List<Order> entityList);

    Order convertToEntity(OrderDTO dto);

}
