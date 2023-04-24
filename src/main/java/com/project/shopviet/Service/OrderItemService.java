package com.project.shopviet.Service;

import com.project.shopviet.DTO.OrderItemDto;
import com.project.shopviet.Model.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem addToOrderItem(int cart_id, int order_id);
    List<OrderItemDto> getAllOrderItemByUser();
    List<OrderItemDto> getAllOrderItem();
    List<OrderItemDto> getAllOrderItemOfSeller();



    OrderItemDto updateStatusApproved(int orderItem_id);
    OrderItemDto updateStatusShipped(int orderItem_id);
    OrderItemDto updateStatusCancelled(int orderItem_id);
    OrderItemDto updateStatusDelivered(int orderItem_id);
    OrderItemDto updateStatusOnTheWay(int orderItem_id);
    OrderItemDto updateStatusSuccess(int orderItem_id);
    List<OrderItemDto> getAllOrderItemApproved();
    List<OrderItemDto> getAllOrderItemShipped();
    List<OrderItemDto> getAllOrderItemOnTheWay();
    List<OrderItemDto> getAllOrderItemDelivered();

}
