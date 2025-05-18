package com.spring.springshoppingcart.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.spring.springshoppingcart.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<OrderItemDto> items;
}
