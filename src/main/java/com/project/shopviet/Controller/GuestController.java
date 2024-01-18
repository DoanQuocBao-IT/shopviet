package com.project.shopviet.Controller;

import com.project.shopviet.DTO.response.*;
import com.project.shopviet.Model.User;
import com.project.shopviet.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/shopviet")
public class GuestController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;
    @Autowired
    FollowService followService;

    @GetMapping("/categories")
    public ResponseObject getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/categories/parent")
    public ResponseObject getAllCategoryParentAndChild() {
        return categoryService.getAllCategoryParentAndChild();
    }

    @GetMapping("/categories/child")
    public ResponseObject getAllCategoryChild() {
        return categoryService.getAllCategoryChild();
    }

    @GetMapping("/brands")
    public ResponseObject getAllBrand() {
        return brandService.getAllBrand();
    }

    @GetMapping("/brands/seller/{id}")
    public ResponseObject getAllBrandBySeller(@PathVariable int id) {
        return brandService.getBrandBySellerId(id);
    }

    @GetMapping("/products")
    public ResponseObject getAllProduct(@RequestParam int category_id, @RequestParam String sort, @RequestParam int per_page, @RequestParam int current_page) {
        return productService.getAllProduct(category_id, sort, per_page, current_page);
    }

    @GetMapping("/seller/{id}")
    public ResponseObject getSellerById(@PathVariable int id) {
        return userService.getProfileSeller(id);
    }

    @GetMapping("/products/seller/{seller_id}/brand/{brand_id}")
    public ResponseObject getAllProduct(@PathVariable int brand_id, @PathVariable int seller_id, @RequestParam String sort, @RequestParam int per_page, @RequestParam int current_page) {
        return productService.getAllProduct(brand_id, seller_id, sort, per_page, current_page);
    }

    @GetMapping("/product")
    public ResponseObject findProductByName(@RequestParam String name) {
        return productService.findProductByName(name);
    }

    @GetMapping("/product/{id}")
    public ResponseObject getProductById(@PathVariable int id) {
        return productService.findProductById(id);
    }

    @GetMapping("/edit_product/{id}")
    public ResponseObject getProductEditById(@PathVariable int id) {
        return productService.findProductEditById(id);
    }

    @GetMapping("/product/seller/{id}")
    public ResponseObject getProductForBrand(@PathVariable int id) {
        return productService.getProductForSeller(id);
    }

    @GetMapping("/user/{id}")
    UserSellerResponse getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username/{name}")
    Optional<User> getUserById(@PathVariable String name) {
        return userService.getUserByUsername(name);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestBody String base64String) {
        String imagePath = imageService.saveImage(base64String);
        return ResponseEntity.ok(imagePath);
    }

    @GetMapping("/followers/user/{id}")
    int countFollow(@PathVariable int id) {
        return followService.countFollowers(id);
    }

    @GetMapping("/followings/user/{id}")
    int countFollowings(@PathVariable int id) {
        return followService.countFollowings(id);
    }
}
