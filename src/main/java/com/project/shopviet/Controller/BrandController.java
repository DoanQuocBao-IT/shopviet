package com.project.shopviet.Controller;

import com.project.shopviet.Model.Brand;
import com.project.shopviet.Model.Category;
import com.project.shopviet.Service.BrandService;
import com.project.shopviet.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class BrandController {
    /*@Autowired
    BrandService brandService;

    @GetMapping("/getbrand{id}")
    public Optional<Brand> getBrand(@PathVariable int id){
        return brandService.getBrand(id);
    }
    @GetMapping("/allbycategory/{id}")
    public Optional<Brand> getBrandByCategoryId(@PathVariable int id){
        return brandService.getBrandByCategoryId(id);
    }
    @GetMapping("/{brand_id}/addcategory/{category_id}")
    public void updateBrandForCategory(@PathVariable int category_id,@PathVariable int brand_id){
        brandService.updateBrandForCategory(category_id,brand_id);
    }
    @PostMapping
    public Brand addBrand(@RequestBody Brand brand){
        return brandService.addBrand(brand);
    }
    @GetMapping("/allbrand")
    public List<Brand> getAllBrand(){
        return brandService.getAllBrand();
    }

    @DeleteMapping("/delbrand/{id}")
    public String deleteBrand(@PathVariable int id){
        return brandService.deleteBrand(id);
    }
    @PutMapping("/putbrand/{id}")
    public Brand updateBrand(@RequestBody Brand brand,@PathVariable int id){
        return brandService.updateBrand(id, brand);
    }
    */
}
