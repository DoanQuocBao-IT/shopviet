package com.project.shopviet.Controller;

import com.project.shopviet.DTO.OrderItemDto;
import com.project.shopviet.Model.*;
import com.project.shopviet.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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
    OrderService orderService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    FollowService followService;
    @Autowired
    UserService userService;


    @PostMapping("/addProd")
    public Product addProduct(@RequestBody Product product){
        return productService.addProductBySeller(product);
    }
    @DeleteMapping("/delProd/{id}")
    public String deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }
    @PutMapping("/putProd/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable int id){
        return productService.updateProduct(id, product);
    }
    @GetMapping("/allOrderItem1")
    public List<OrderItemDto> getAllOrderItemOfSeller(){
        return orderItemService.getAllOrderItemOfSeller();
    }
    @GetMapping("/allOrder/{seller_id}")
    List<OrderDetail> getAllOrderBySeller(@PathVariable int seller_id){
        return orderService.getAllOrderBySeller(seller_id);
    }
    @GetMapping("/approved/order{order_id}")
    OrderItem approvedOrder(@PathVariable int order_id){
        return orderItemService.updateStatusApproved(order_id);
    }
    @PostMapping("/feedback/{review_id}")
    Review addFeedback(@RequestBody String feedback,@PathVariable int review_id){
        return reviewService.addReviewFeedback(review_id,feedback);
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
    User getProfileShipper(){
        return userService.getProfile();
    }
}
