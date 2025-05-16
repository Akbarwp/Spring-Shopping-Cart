package com.spring.springshoppingcart.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private List<OrderDto> orders;
    private CartDto cart;
}
