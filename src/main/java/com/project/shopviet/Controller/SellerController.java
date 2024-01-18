package com.project.shopviet.Controller;

import com.project.shopviet.DTO.MessageDto;
import com.project.shopviet.DTO.OrderItemDto;
import com.project.shopviet.DTO.request.BrandRequest;
import com.project.shopviet.DTO.request.ProductRequest;
import com.project.shopviet.DTO.request.RegisterSellerRequest;
import com.project.shopviet.DTO.response.ResponseObject;
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
    ReviewService reviewService;
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @Autowired
    BrandService brandService;

    @PostMapping("/register")
    public ResponseObject registerSeller(@RequestBody RegisterSellerRequest register) {
        return userService.registerUserSeller(register);
    }

    @GetMapping("/profile")
    public ResponseObject getProfile() {
        return userService.getProfileForSeller();
    }

    @PostMapping("/product")
    public ResponseObject addProduct(@RequestBody ProductRequest product) {
        return productService.addProductBySeller(product);
    }

    @DeleteMapping("/product/{id}")
    public ResponseObject deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("/product/{id}")
    public ResponseObject updateProduct(@RequestBody ProductRequest product, @PathVariable int id) {
        return productService.updateProduct(id, product);
    }

    @GetMapping("/product/seller")
    public ResponseObject getProductForBrand() {
        return productService.getProductForSeller();
    }

    @GetMapping("/brand")
    public ResponseObject getAllBrandBySeller() {
        return brandService.getBrandBySeller();
    }

    @PostMapping("/brand")
    public ResponseObject addBrand(@RequestBody BrandRequest brand) {
        return brandService.addBrand(brand);
    }

    @DeleteMapping("/brand/{id}")
    public ResponseObject deleteBrand(@PathVariable int id) {
        return brandService.deleteBrand(id);
    }

    @PutMapping("/brand/{id}")
    public ResponseObject updateBrand(@RequestBody BrandRequest brand, @PathVariable int id) {
        return brandService.updateBrand(id, brand);
    }

    @GetMapping("/order")
    List<OrderItemDto> getAllOrderBySeller() {
        return orderItemService.getAllOrderItemOfSeller();
    }

    @GetMapping("/approved/order/{order_id}")
    OrderItemDto approvedOrder(@PathVariable int order_id) {
        return orderItemService.updateStatusApproved(order_id);
    }

    @PostMapping("/feedback/{review_id}")
    Review addFeedback(@RequestBody String feedback, @PathVariable int review_id) {
        return reviewService.addReviewFeedback(review_id, feedback);
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
