package com.example.E_commers.Controller;

import com.example.E_commers.Service.CartService;
import com.example.E_commers.Service.OrderService;
import com.example.E_commers.model.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;



    @GetMapping
    public List<CartItem> view(@AuthenticationPrincipal UserDetails user) {
        return cartService.getCart(user.getUsername());
    }

    @PostMapping("/add")
    public CartItem add(@AuthenticationPrincipal UserDetails user , @RequestParam Long productId , @RequestParam int quantity){
        return cartService.addToCart(user.getUsername(), productId, quantity);

    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable Long id){
        cartService.removeFromCart(id);
        return "Removed";
    }

}
