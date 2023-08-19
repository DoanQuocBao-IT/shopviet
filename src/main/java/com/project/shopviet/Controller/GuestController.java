package com.project.shopviet.Controller;

import com.project.shopviet.DTO.*;
import com.project.shopviet.Model.User;
import com.project.shopviet.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping("/allCat")
    public List<CategoryDto> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/allBrand")
    public List<BrandDto> getAllBrand(){
        return brandService.getAllBrand();
    }
    @GetMapping("/brand/cat{category_id}")
    public List<BrandDto> getAllBrandForCategoryId(@PathVariable int category_id){
        return brandService.getBrandByCategoryId(category_id);
    }
    @GetMapping("/allBrandProduct")
    public List<BrandProductDto> getAllBrandProduct(){
        return brandService.getAllBrandProduct();
    }
    @GetMapping("/allProd")
    public List<ProductDto> getAllProduct(@RequestParam String sort){
        return productService.getAllProduct(sort);
    }
    @GetMapping("/product")
    public List<ProductDto> findProductByName(@RequestParam String name){
        return productService.findProductByName(name);
    }
    @GetMapping("/Product/{id}")
    public Optional<ProductDetailDto> getProductById(@PathVariable int id){
        return productService.findProductById(id);
    }
    @GetMapping("/allProdInCat/{id}")
    public List<ProductDto> getAllProductInCategory(@PathVariable int id){
        return productService.getProductForCategory(id);
    }
    @GetMapping("/allProdInBrand/{id}")
    public List<ProductDto> getAllProductInBrand(@PathVariable int id){
        return productService.getProductForBrand(id);
    }
    @GetMapping("/allProd/seller/{seller_id}")
    public List<ProductDto> getAllProductFromSeller(@PathVariable int seller_id){
        return productService.getProductBySeller(seller_id);
    }
    @GetMapping("/allImageProd")
    public List<String> getAllImageProduct(){
        return productService.getAllImageProduct();
    }

    @GetMapping("/user/{id}")
    UserSellerDto getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }
    @GetMapping("/username/{name}")
    Optional<User> getUserById(@PathVariable String  name){
        return userService.getUserByUsername(name);
    }
}
