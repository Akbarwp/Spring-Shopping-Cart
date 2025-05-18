package com.spring.springshoppingcart.service.order;

import java.util.List;

import com.spring.springshoppingcart.dto.OrderDto;
import com.spring.springshoppingcart.model.Order;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
}
