package com.example.E_commers.Repository;

import com.example.E_commers.model.CartItem;
import com.example.E_commers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem , Long> {

    List<CartItem> findByUser(User user) ;
    void deleteByUser(User user);
}
