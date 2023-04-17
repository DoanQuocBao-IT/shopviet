package com.project.shopviet.Service;

import com.project.shopviet.DTO.OrderUserDto;
import com.project.shopviet.Model.OrderDetail;

import java.util.List;

public interface OrderService {
    OrderDetail addToOrder(OrderDetail orderDetail);

    List<OrderUserDto> getAllOrder();
    List<OrderDetail> getAllOrderBySeller(int seller_id);
    List<OrderUserDto> getAllOrderByBuyer();
}
