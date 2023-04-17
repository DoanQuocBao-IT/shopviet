package com.project.shopviet.Repository;

import com.project.shopviet.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    @Query(value = "SELECT c FROM CartItem c WHERE c.user.id=:buyer_id")
    List<CartItem> getCartByBuyerId(int buyer_id);
}

