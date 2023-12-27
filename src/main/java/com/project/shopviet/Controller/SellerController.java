package com.project.shopviet.Controller;

import com.project.shopviet.DTO.MessageDto;
import com.project.shopviet.DTO.OrderItemDto;
import com.project.shopviet.DTO.request.ProductRequest;
import com.project.shopviet.Model.*;
import com.project.shopviet.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/seller")
@CrossOrigin(origins = {"*"})
public class SellerController {
    @Autowired
    ProductService productService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;

    @PostMapping("/product")
    public Product addProduct(@RequestBody ProductRequest product){
        return productService.addProductBySeller(product);
    }
    @DeleteMapping("/delProd/{id}")
    public String deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }
    @PutMapping("/product/{id}")
    public Product updateProduct(@RequestBody ProductRequest product,@PathVariable int id){
        return productService.updateProduct(id, product);
    }
    @GetMapping("/allOrderItem")
    public List<OrderItemDto> getAllOrderItemOfSeller(){
        return orderItemService.getAllOrderItemOfSeller();
    }
    @GetMapping("/allOrder/{seller_id}")
    List<OrderDetail> getAllOrderBySeller(@PathVariable int seller_id){
        return orderDetailService.getAllOrderBySeller(seller_id);
    }
    @GetMapping("/approved/order{order_id}")
    OrderItemDto approvedOrder(@PathVariable int order_id){
        return orderItemService.updateStatusApproved(order_id);
    }
    @PostMapping("/feedback/{review_id}")
    Review addFeedback(@RequestBody String feedback,@PathVariable int review_id){
        return reviewService.addReviewFeedback(review_id,feedback);
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
