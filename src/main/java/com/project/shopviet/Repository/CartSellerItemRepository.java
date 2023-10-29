package com.project.shopviet.Repository;

import com.project.shopviet.Model.CartSellerItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartSellerItemRepository extends JpaRepository<CartSellerItem, Integer> {
    CartSellerItem getCartSellerItemBySellerIdAndBuyerId(int seller_id, int buyer_id);
    List<CartSellerItem> getCartSellerItemsByBuyerId(int buyer_id);
    boolean existsBySellerIdAndBuyerId(int seller_id, int buyer_id);
}
