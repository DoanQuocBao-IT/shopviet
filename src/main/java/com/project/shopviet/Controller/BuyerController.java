package com.project.shopviet.Controller;

import com.project.shopviet.DTO.*;
import com.project.shopviet.DTO.request.ItemCartRequest;
import com.project.shopviet.DTO.request.OrderRequest;
import com.project.shopviet.DTO.response.CartResponse;
import com.project.shopviet.DTO.response.Response;
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
    OrderDetailService orderDetailService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    CartService cartService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    MessageService messageService;
    @Autowired
    OrderService orderService;
    @Autowired
    ConsigneeService consigneeService;
    @PostMapping("/cart")
    public Response addToCart(@RequestBody ItemCartRequest request) {
        return cartItemService.addToCart(request);
    }

    @PutMapping("/cart")
    public Response updateToCart(@RequestBody ItemCartRequest request){
        return cartItemService.updateCart(request);
    }
    @DeleteMapping("/cart/product/{id}")
    public Response deleteCart(@PathVariable int id){
        return cartItemService.deleteCartItem(id);
    }
    @GetMapping("/cart")
    public CartResponse getCartItems() {
        return cartService.getCart();
    }
    @PostMapping("/order")
    public List<OrderDto> addToOrder(@RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }
    @PostMapping("/consignee")
    public ConsigneeDto addConsignee(@RequestBody ConsigneeDto consignee){
        return consigneeService.createConsignee(consignee);
    }
    @PostMapping("/order{order_id}/addcart{cart_id}_item")
    public OrderItem addToOrderItem(@PathVariable int order_id,@PathVariable int cart_id){
        return orderItemService.addToOrderItem(cart_id,order_id);
    }
    @GetMapping("/order-item")
    public List<OrderItemDto> getAllOrderItem(){
        return orderItemService.getAllOrderItemByUser();
    }
    @PostMapping("/order/add-orderItem")
    public OrderDetail addToOrder(@RequestBody OrderDetail orderDetail){
        return orderDetailService.addToOrder(orderDetail);
    }
    @GetMapping("/allOrder")
    List<OrderUserDto> getAllOrderByBuyer(){
        return orderDetailService.getAllOrderByBuyer();
    }
    @GetMapping("/order/cancel/{order_id}")
    OrderItemDto cancelledOrder(@PathVariable int order_id){
        return orderItemService.updateStatusCancelled(order_id);
    }

    @GetMapping("/order/success/{id}")
    OrderItemDto updateOrderSuccess(@PathVariable int id){
        return orderItemService.updateStatusSuccess(id);
    }
    @PostMapping("/review/orderItem{orderItem_id}")
    Review addReview(@RequestBody Review review,@PathVariable int orderItem_id){
        return reviewService.addReview(orderItem_id,review);
    }

    @PostMapping("/message/user/{receiver_id}")
    Messages sendMessage(@RequestBody Messages messages,@PathVariable int receiver_id){
        return messageService.sendMessage(messages,receiver_id);
    }
    @GetMapping("/message/user/{receiver_id}")
    List<MessageDto> getAllMessage(@PathVariable int receiver_id)
    {
        return messageService.findBySenderAndReceiverOrderByCreateAtAsc(receiver_id);
    }
}
