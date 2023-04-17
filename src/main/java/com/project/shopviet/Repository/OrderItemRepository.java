package com.project.shopviet.Repository;

import com.project.shopviet.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    @Query(value = "select p from OrderItem p where p.user.id=:id")
    List<OrderItem> findByOrderItemContaining(int id);
    @Query(value = "select p from OrderItem p where p.product.userSeller.id=:seller_id")
    List<OrderItem> findByOrderItemOfSeller(int seller_id);

    List<OrderItem> findByStatus(String status);

}
