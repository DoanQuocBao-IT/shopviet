package com.project.shopviet.Controller;

import com.project.shopviet.DTO.*;
import com.project.shopviet.DTO.response.*;
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
    @Autowired
    ImageService imageService;
    @GetMapping("/allCat")
    public ResponseEntity<?> getAllCategory(){
        try {
            List<CategoryResponse> category=categoryService.getAllCategory();
            return new ResponseEntity<>(category, HttpStatus.OK);
        }
        catch (Exception e){
            ErrorResponse errorResponse=new ErrorResponse("NOT_FOUND","Category not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/all/category")
    public List<CategoryDto> getAllCategoryDto(){
        return categoryService.getAllCategoryDto();
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
    @GetMapping("/best-brand")
    public List<BrandResponse> get5BrandBestSeller(){
        return brandService.get5BrandBestSeller();
    }
    @GetMapping("/brand/cat{category_id}")
    public List<BrandDto> getAllBrandForCategoryId(@PathVariable int category_id){
        return brandService.getBrandByCategoryId(category_id);
    }
    @GetMapping("/allBrandProduct")
    public List<BrandProductDto> getAllBrandProduct(){
        return brandService.getAllBrandProduct();
    }
    @GetMapping("/all/brand-product/category/{name}")
    public CategoryIdBrandResponse getAllBrandProductByCategoryId(@PathVariable String name){
        return brandService.getAllBrandProductByCategoryId(name);
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
    @GetMapping("/best-product/brand/{id}")
    public BrandProductResponse get3ProductBestSellerForBrand(@PathVariable int id){
        return productService.get3ProductBestSellerForBrand(id);
    }
    @GetMapping("/products/seller/{seller_id}")
    public PagedProductResponse getAllProductFromSeller(@PathVariable int seller_id, @RequestParam int per_page, @RequestParam int current_page){
        return productService.getProductBySeller(seller_id,per_page,current_page);
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
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestBody String base64String) {
        String imagePath = imageService.saveImage(base64String);
        return ResponseEntity.ok(imagePath);
    }
}
