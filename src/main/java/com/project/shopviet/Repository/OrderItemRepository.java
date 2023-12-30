package com.project.shopviet.Repository;

import com.project.shopviet.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    List<OrderItem> findAllByUserIdOrderByCreatedAtDesc(int id);
    List<OrderItem> findAllByProduct_Seller_Id(int id);
    List<OrderItem> findAllByStatus(String status);
}
