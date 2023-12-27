package com.project.shopviet.Repository;

import com.project.shopviet.Model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    @Query(value = "SELECT o FROM OrderDetail o WHERE o.userBuyer.id=:buyer_id")
    List<OrderDetail> getOrderByBuyerId(int buyer_id);
}
