package com.project.shopviet.Controller;

import com.project.shopviet.DTO.CartDto;
import com.project.shopviet.DTO.MessageDto;
import com.project.shopviet.DTO.OrderItemDto;
import com.project.shopviet.DTO.OrderUserDto;
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
    UserService userService;
    @Autowired
    FollowService followService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    MessageService messageService;
    @PostMapping("/cart/add-item")
    public CartItem addToCart(@RequestBody CartItem cartItem) {
        return cartItemService.addToCart(cartItem);
    }
    @GetMapping("/cart")
    public List<CartDto> getCartItems() {
        return cartItemService.getAllCartItem();
    }
    @PostMapping("/cart/{id}/update-item")
    public CartItem updateCart(@RequestBody CartItem cartItem, @PathVariable int id){
        return cartItemService.updateCart(id,cartItem);
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
        return orderService.addToOrder(orderDetail);
    }
    @GetMapping("/allOrder")
    List<OrderUserDto> getAllOrderByBuyer(){
        return orderService.getAllOrderByBuyer();
    }
    @GetMapping("/cancelled/order{order_id}")
    OrderItem cancelledOrder(@PathVariable int order_id){
        return orderItemService.updateStatusCancelled(order_id);
    }

    @GetMapping("/order/success/{id}")
    OrderItem updateOrderSuccess(@PathVariable int id){
        return orderItemService.updateStatusSuccess(id);
    }
    @PostMapping("/review/orderItem{orderItem_id}")
    Review addReview(@RequestBody Review review,@PathVariable int orderItem_id){
        return reviewService.addReview(orderItem_id,review);
    }


    @GetMapping("/follow/user{id}")
    Follow addFollow(@PathVariable int id){
        return followService.addFollow(id);
    }
    @GetMapping("/delfollow/user{id}")
    void deleteFollow(@PathVariable int id){
        followService.deteleFollow(id);
    }
    @GetMapping("/countfollow/user{id}")
    int countFollow(@PathVariable int id){
        return followService.countFollow(id);
    }

    @GetMapping("/profile")
    User getProfileBuyer(){
        return userService.getProfile();
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
