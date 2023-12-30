package com.project.shopviet.Service;

import com.project.shopviet.DTO.OrderItemDto;
import com.project.shopviet.DTO.request.OrderRequest;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.Model.OrderItem;

import java.util.List;

public interface OrderItemService {
    ResponseObject addToOrderItem(OrderRequest orderRequest);
    ResponseObject getAllOrderItemByUser();
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
