package com.project.shopviet.Controller;

import com.project.shopviet.Model.Brand;
import com.project.shopviet.Model.Category;
import com.project.shopviet.Model.Product;
import com.project.shopviet.Service.BrandService;
import com.project.shopviet.Service.CategoryService;
import com.project.shopviet.Service.ProductService;
import com.project.shopviet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/shopviet")
public class UserController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;
    @Autowired
    ProductService productService;
    @GetMapping("/allBrand")
    public List<Brand> getAllBrand(){
        return brandService.getAllBrand();
    }

    @GetMapping("/allCat")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/allProd")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/Product/{id}")
    public Optional<Product> getProductById(@PathVariable int id){
        return productService.findProductById(id);
    }
    @GetMapping("/allProdInCat/{id}")
    public Optional<Product> getAllProductInCategory(@PathVariable int id){
        return productService.getProductForCategory(id);
    }
    @GetMapping("/allProdInBrand/{id}")
    public Optional<Product> getAllProductInBrand(@PathVariable int id){
        return productService.getProductForBrand(id);
    }
    @GetMapping("/allImageProd")
    public List<String> getAllImageProduct(){
        return productService.getAllImageProduct();
    }

}
