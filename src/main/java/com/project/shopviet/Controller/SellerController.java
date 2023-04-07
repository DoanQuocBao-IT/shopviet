package com.project.shopviet.Controller;

import com.project.shopviet.Model.Brand;
import com.project.shopviet.Model.Category;
import com.project.shopviet.Model.Product;
import com.project.shopviet.Service.BrandService;
import com.project.shopviet.Service.CategoryService;
import com.project.shopviet.Service.ProductService;
import com.project.shopviet.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
@RestController
@RequestMapping("/api/seller")
@CrossOrigin(origins = {"*"})
public class SellerController {
    @Autowired
    ProductService productService;


    @PostMapping("/addProd")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    @DeleteMapping("/delProd/{id}")
    public String deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }
    @PutMapping("/putProd/{id}")
    public Product updateProduct(@RequestBody Product product,@PathVariable int id){
        return productService.updateProduct(id, product);
    }
}
