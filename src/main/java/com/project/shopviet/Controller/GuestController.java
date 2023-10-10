package com.project.shopviet.Controller;

import com.project.shopviet.DTO.*;
import com.project.shopviet.DTO.response.PagedProductResponse;
import com.project.shopviet.Model.User;
import com.project.shopviet.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getAllCategory(){
        try {
            List<CategoryDto> category=categoryService.getAllCategory();
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        catch (Exception e){
            ErrorResponse errorResponse=new ErrorResponse("NOT_FOUND","Category not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allBrand")
    public ResponseEntity<?> getAllBrand(){
        try {
            List<BrandDto> brand=brandService.getAllBrand();
            return new ResponseEntity<>(brand, HttpStatus.OK);
        }
        catch (Exception e){
            ErrorResponse errorResponse=new ErrorResponse("NOT_FOUND","Brand not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
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
    public PagedProductResponse getAllProduct(@RequestParam String sort, @RequestParam int per_page, @RequestParam int current_page){
        return productService.getAllProduct(sort,per_page,current_page);
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
