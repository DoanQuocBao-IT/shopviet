package com.project.shopviet.Service;

import com.project.shopviet.DTO.OrderDto;
import com.project.shopviet.DTO.request.OrderRequest;

import java.util.List;

public interface OrderService {
    List<OrderDto> createOrder(OrderRequest orderRequest);
}
