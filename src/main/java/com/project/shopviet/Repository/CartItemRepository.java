package com.project.shopviet.Repository;

import com.project.shopviet.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    boolean existsByUser_IdAndProduct_Id(int user_id,int product_id);
    CartItem getCartItemByUser_IdAndProduct_Id(int user_id,int product_id);
    List<CartItem> getCartItemsByProduct_UserSeller_Id(int seller_id);
}

