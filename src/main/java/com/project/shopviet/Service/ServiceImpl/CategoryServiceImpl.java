package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.Model.Category;
import com.project.shopviet.Repository.CategoryRepository;
import com.project.shopviet.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Category addCategory(Category category) {
        try{
            return categoryRepository.save(category);
        }catch (IllegalArgumentException e){
            System.out.println("Add Category Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public String deleteCategory(int id) {
        try{
            categoryRepository.deleteById(id);
            return "Delete Category Successful";
        }catch (IllegalArgumentException e){
            System.out.println("Delete Category Error: "+e.getMessage());
            return "Delete Category Error: "+e.getMessage();
        }
    }

    @Override
    public Category updateCategory(int id, Category category) {

        try {
            if (isExistById(id)) {
                Category currentCategory = categoryRepository.findById(id).get();
                    currentCategory.setName(category.getName());
                    currentCategory.setImage(category.getImage());

                categoryRepository.save(currentCategory);
                return currentCategory;
            } else return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Update Category Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Category> getAllCategory() {
        try{
            return (List<Category>) categoryRepository.findAll();
        }catch (IllegalArgumentException e){
            System.out.println("Get All Category Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<Category> getCategory(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public boolean isExistById(int id) {
        return categoryRepository.existsById(id);
    }
}
