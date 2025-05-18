package com.spring.springshoppingcart.service.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.springshoppingcart.dto.OrderDto;
import com.spring.springshoppingcart.enums.OrderStatus;
import com.spring.springshoppingcart.exception.ResourceException;
import com.spring.springshoppingcart.model.Cart;
import com.spring.springshoppingcart.model.Order;
import com.spring.springshoppingcart.model.OrderItem;
import com.spring.springshoppingcart.model.Product;
import com.spring.springshoppingcart.repository.OrderRepository;
import com.spring.springshoppingcart.repository.ProductRepository;
import com.spring.springshoppingcart.service.cart.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CartService cartService;

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);

        Order order = createOrder(cart);
        List<OrderItem> orderItemList = createOrderItem(order, cart);

        order.setOrderItems(new HashSet<>(orderItemList));
        order.setTotalAmount(calculateTotalAmount(orderItemList));

        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(cart.getId());

        return savedOrder;
    }

    @Transactional(readOnly = true)
    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
    
        return order;
    }

    @Transactional
    private List<OrderItem> createOrderItem(Order order, Cart cart) {
        return cart.getCartItems()
            .stream()
            .map(cartItem -> {
                Product product = cartItem.getProduct();
                product.setInventory(product.getInventory() - cartItem.getQuantity());
                productRepository.save(product);

                return new OrderItem(
                    order,
                    product,
                    cartItem.getQuantity(),
                    cartItem.getUnitPrice()
                );
            })
            .toList();
    }

    @Transactional(readOnly = true)
    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {
        return orderItemList.stream()
            .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto getOrder(Long orderId) {
        return orderRepository.findById(orderId)
            .map(this::convertToDto)
            .orElseThrow(() -> new ResourceException("Order not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this::convertToDto).toList();
    }

    private OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
}
