package com.project.shopviet.Controller;

import com.project.shopviet.DTO.*;
import com.project.shopviet.DTO.request.ConsigneeRequest;
import com.project.shopviet.DTO.request.ItemCartRequest;
import com.project.shopviet.DTO.request.OrderRequest;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.Model.*;
import com.project.shopviet.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyer")
@CrossOrigin(origins = {"*"})
public class BuyerController {
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    MessageService messageService;
    @Autowired
    ConsigneeService consigneeService;

    @PostMapping("/cart")
    public ResponseObject addToCart(@RequestBody ItemCartRequest request) {
        return cartItemService.addToCart(request);
    }

    @PutMapping("/cart")
    public ResponseObject updateToCart(@RequestBody ItemCartRequest request) {
        return cartItemService.updateCart(request);
    }

    @DeleteMapping("/cart/{id}")
    public ResponseObject deleteCart(@PathVariable int id, @PathVariable int productTypeId) {
        return cartItemService.deleteCartItem(id, productTypeId);
    }

    @GetMapping("/cart")
    public ResponseObject getCartItems() {
        return cartItemService.getCart();
    }

    @PostMapping("/order")
    public ResponseObject addToOrder(@RequestBody OrderRequest orderRequest) {
        return orderItemService.addToOrderItem(orderRequest);
    }

    @PostMapping("/consignee")
    public ResponseObject addConsignee(@RequestBody ConsigneeRequest consignee) {
        return consigneeService.createConsignee(consignee);
    }

    @PutMapping("/consignee/{id}")
    public ResponseObject updateConsignee(@RequestBody ConsigneeRequest consignee, @PathVariable int id) {
        return consigneeService.updateConsignee(consignee, id);
    }

    @DeleteMapping("/consignee/{id}")
    public ResponseObject deleteConsignee(@PathVariable int id) {
        return consigneeService.deleteConsignee(id);
    }

    @GetMapping("/consignee")
    public ResponseObject getAllConsignee() {
        return consigneeService.getAllConsignee();
    }


    @GetMapping("/order")
    public ResponseObject getAllOrderItem() {
        return orderItemService.getAllOrderItemByUser();
    }

    @GetMapping("/order/cancel/{order_id}")
    OrderItemDto cancelledOrder(@PathVariable int order_id) {
        return orderItemService.updateStatusCancelled(order_id);
    }

    @GetMapping("/order/success/{id}")
    OrderItemDto updateOrderSuccess(@PathVariable int id) {
        return orderItemService.updateStatusSuccess(id);
    }

    @PostMapping("/review/orderItem{orderItem_id}")
    Review addReview(@RequestBody Review review, @PathVariable int orderItem_id) {
        return reviewService.addReview(orderItem_id, review);
    }

    @PostMapping("/message/user/{receiver_id}")
    Messages sendMessage(@RequestBody Messages messages, @PathVariable int receiver_id) {
        return messageService.sendMessage(messages, receiver_id);
    }

    @GetMapping("/message/user/{receiver_id}")
    List<MessageDto> getAllMessage(@PathVariable int receiver_id) {
        return messageService.findBySenderAndReceiverOrderByCreateAtAsc(receiver_id);
    }
}
