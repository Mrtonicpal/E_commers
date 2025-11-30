package com.example.E_commers.Controller;

import com.example.E_commers.Repository.OrderRepository;
import com.example.E_commers.Service.OrderService;
import com.example.E_commers.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Orders")
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/place")
    public Order place(@AuthenticationPrincipal UserDetails user){
        return orderService.PlaceOrder(user.getUsername());
    }

    @GetMapping
    public List<Order> myOrder(@AuthenticationPrincipal UserDetails user){
        return orderService.getUserOrders(user.getUsername());
    }
}
