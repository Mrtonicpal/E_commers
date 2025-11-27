package com.example.E_commers.Service;


import com.example.E_commers.Repository.CartItemRepository;
import com.example.E_commers.Repository.OrderRepository;
import com.example.E_commers.Repository.ProductRepository;
import com.example.E_commers.Repository.UserRepository;
import com.example.E_commers.model.CartItem;
import com.example.E_commers.model.Order;
import com.example.E_commers.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public Order PlaceOrder(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        if (cartItems.isEmpty()) throw new RuntimeException("Cart is empty");

        double total = cartItems.stream().mapToDouble(ci -> ci.getProduct().getPrice() * ci.getQuantity()).sum();
        Order order = Order.builder()
                .user(user)
                .cartItems(cartItems)
                .orderDate(LocalDateTime.now())
                .totalAmount(total)
                .build();

        Order saved = orderRepository.save(order);
        cartItemRepository.deleteByUser(user);
        return saved;
    }


    public List<Order> getUserOrders(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return orderRepository.findByUser(user);
    }


}
