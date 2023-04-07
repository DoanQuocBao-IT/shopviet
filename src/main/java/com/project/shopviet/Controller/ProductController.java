package com.project.shopviet.Controller;

import com.project.shopviet.Model.Category;
import com.project.shopviet.Model.Product;
import com.project.shopviet.Service.CategoryService;
import com.project.shopviet.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
    /*
    @Autowired
    ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    @GetMapping("/image")
    public List<String> getAllImageProduct(){
        return productService.getAllImageProduct();
    }
    @GetMapping("/all")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
    @DeleteMapping("/del/{id}")
    public String deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }
    @PutMapping("/put/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable int id){
        return productService.updateProduct(id, product);
    }
    *
 */
}
