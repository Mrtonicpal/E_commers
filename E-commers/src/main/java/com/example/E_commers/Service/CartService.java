package com.example.E_commers.Service;


import com.example.E_commers.Repository.CartItemRepository;
import com.example.E_commers.Repository.ProductRepository;
import com.example.E_commers.Repository.UserRepository;
import com.example.E_commers.model.CartItem;
import com.example.E_commers.model.Product;
import com.example.E_commers.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;


    public List<CartItem> getCart(String username ){
        User user = userRepository.findByUsername(username).orElseThrow();
        return cartItemRepository.findByUser(user);
    }

    public CartItem addToCart(String username, Long productId , int quantity) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        CartItem cartItem = CartItem.builder()
                .user(user)
                .product(product)
                .quantity(quantity)
                .build();
return cartItemRepository.save(cartItem);

    }

    public void removeFromCart(Long cartItemId){
        cartItemRepository.deleteById(cartItemId);
    }

    public void clearCart(User user){
        cartItemRepository.deleteByUser(user);
    }
}
