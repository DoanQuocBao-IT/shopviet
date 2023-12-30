package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.CategoryDto;
import com.project.shopviet.DTO.request.CategoryRequest;
import com.project.shopviet.DTO.response.CategoryResponse;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.Model.Category;
import com.project.shopviet.Repository.CategoryRepository;
import com.project.shopviet.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public ResponseObject addCategory(CategoryRequest cat) {
        try{
            Category category = new Category();
            category.setName(cat.getName());
            category.setImage(cat.getImage());
            category.setDescription(cat.getDescription());
            if (cat.getParent_id()<0) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Parent Id must be greater than 0")
                        .build();
            }
            if (cat.getParent_id()==0) {
                category.setParentId(0);
                categoryRepository.save(category);
                return ResponseObject.builder()
                        .code(200)
                        .message("Add Category Successful")
                        .build();
            }
            if (!isExistById(cat.getParent_id())) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Parent Category not found")
                        .build();
            }
            category.setParentId(cat.getParent_id());
            categoryRepository.save(category);
            return ResponseObject.builder()
                    .code(200)
                    .message("Add Category Successful")
                    .build();
        }catch (IllegalArgumentException e){
            return ResponseObject.builder()
                    .code(400)
                    .message("Add Category Error: "+e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject deleteCategory(int id) {
        try{
            if (!isExistById(id)) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Category not found")
                        .build();
            }
            categoryRepository.deleteById(id);
            return ResponseObject.builder()
                    .code(200)
                    .message("Delete Category Successful")
                    .build();
        }catch (IllegalArgumentException e){
            return ResponseObject.builder()
                    .code(400)
                    .message("Delete Category Error: "+e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject updateCategory(int id, CategoryRequest category) {
        try {
            if (!isExistById(id)) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Category not found")
                        .build();
            }
            Category cat = categoryRepository.findById(id).get();
            cat.setName(category.getName());
            cat.setImage(category.getImage());
            cat.setDescription(category.getDescription());
            if (category.getParent_id()<0) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Parent Id must be greater than 0")
                        .build();
            }
            if (category.getParent_id()==0) {
                cat.setParentId(0);
                categoryRepository.save(cat);
                return ResponseObject.builder()
                        .code(200)
                        .message("Update Category Successful")
                        .build();
            }
            if (!isExistById(category.getParent_id())) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Parent Category not found")
                        .build();
            }
            cat.setParentId(category.getParent_id());
            categoryRepository.save(cat);
            return ResponseObject.builder()
                    .code(200)
                    .message("Update Category Successful")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Update Category Error: "+e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject getAllCategory() {
        try {
            List<Category> categories = categoryRepository.findAllParentCategory();
            List<CategoryDto> categoryResponses = new ArrayList<>();
            for (Category category : categories) {
                CategoryDto categoryDto = new CategoryDto();
                categoryDto.setId(category.getId());
                categoryDto.setName(category.getName());
                categoryDto.setImage(category.getImage());
                categoryResponses.add(categoryDto);
            }
            return ResponseObject.builder()
                    .code(200)
                    .message("Get All Category Successful")
                    .data(categoryResponses)
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Get All Category Error: "+e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject getCategory(int id) {
        try {
            if (!isExistById(id)) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Category not found")
                        .build();
            }
            Category category = categoryRepository.findById(id).get();
            return ResponseObject.builder()
                    .code(200)
                    .message("Get Category Successful")
                    .data(CategoryResponse.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .image(category.getImage())
                            .build())
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Get Category Error: "+e.getMessage())
                    .build();
        }
    }

    @Override
    public boolean isExistById(int id) {
        return categoryRepository.existsById(id);
    }
}
