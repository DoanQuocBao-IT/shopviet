package com.project.shopviet.Controller;

import com.project.shopviet.Model.Category;
import com.project.shopviet.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
    /*
    @Autowired
    CategoryService categoryService;

    @GetMapping("/getcate{id}")
    public Optional<Category> getCategory(@PathVariable int id){
        return categoryService.getCategory(id);
    }
    @PostMapping
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
    @GetMapping("/allcategory")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @DeleteMapping("/delcategory/{id}")
    public String deleteCategory(@PathVariable int id){
        return categoryService.deleteCategory(id);
    }
    @PutMapping("/putcategory/{id}")
    public Category updateCategory(@RequestBody Category category,@PathVariable int id){
        return categoryService.updateCategory(id, category);
    }
    *
     */
}
