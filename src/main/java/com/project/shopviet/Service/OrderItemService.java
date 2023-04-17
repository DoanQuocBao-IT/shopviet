package com.project.shopviet.Service;

import com.project.shopviet.DTO.OrderItemDto;
import com.project.shopviet.Model.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem addToOrderItem(int cart_id, int order_id);
    List<OrderItemDto> getAllOrderItemByUser();
    List<OrderItemDto> getAllOrderItem();
    List<OrderItemDto> getAllOrderItemOfSeller();



    OrderItem updateStatusApproved(int orderItem_id);
    OrderItem updateStatusShipped(int orderItem_id);
    OrderItem updateStatusCancelled(int orderItem_id);
    OrderItem updateStatusDelivered(int orderItem_id);
    OrderItem updateStatusOnTheWay(int orderItem_id);
    OrderItem updateStatusSuccess(int orderItem_id);
    List<OrderItem> getAllOrderItemApproved();
    List<OrderItem> getAllOrderItemShipped();
    List<OrderItem> getAllOrderItemOnTheWay();
    List<OrderItem> getAllOrderItemDelivered();

}
