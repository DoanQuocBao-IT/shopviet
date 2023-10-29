package com.project.shopviet.Repository;

import com.project.shopviet.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart getCartByBuyer_Id(int buyer_id);
    boolean existsByBuyer_Id(int buyer_id);
}
