package com.project.shopviet.Repository;

import com.project.shopviet.Model.CartItem;
import com.project.shopviet.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    Optional<CartItem> findByProductIdAndUserIdAndProductTypeId(int productId,int userId,int productTypeId);
    List<CartItem> findByUserIdOrderByCreatedAtDesc(int userId);
//    List<CartItem> findByUserIdAndProduct_SellerIdOrderByCreatedAtDescOrderByProduct_SellerIdDesc(int userId,int sellerId);

}

