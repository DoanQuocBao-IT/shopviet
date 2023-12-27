package com.project.shopviet.Repository;

import com.project.shopviet.Model.CartItem;
import com.project.shopviet.Model.CartSellerItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartSellerItemRepository extends JpaRepository<CartSellerItem, Integer> {
    CartSellerItem getCartSellerItemBySellerIdAndBuyerId(int seller_id, int buyer_id);
    List<CartSellerItem> getCartSellerItemsByBuyerId(int buyer_id);
    @Query("SELECT c FROM CartSellerItem c JOIN c.cartItem item WHERE c.seller.id = :sellerId AND item IN :cartItems")
    CartSellerItem findBySellerIdAndCartItems(@Param("sellerId") int sellerId, @Param("cartItems") List<CartItem> cartItems);
    boolean existsBySellerIdAndBuyerId(int seller_id, int buyer_id);
}
