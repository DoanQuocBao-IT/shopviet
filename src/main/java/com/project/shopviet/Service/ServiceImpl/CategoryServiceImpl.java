package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.request.CategoryRequest;
import com.project.shopviet.DTO.response.CategoryDetailResponse;
import com.project.shopviet.DTO.response.CategoryResponse;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.Model.Category;
import com.project.shopviet.Repository.CategoryRepository;
import com.project.shopviet.Service.CategoryService;
import com.project.shopviet.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ImageService imageService;
    @Override
    public ResponseObject addCategory(CategoryRequest cat) {
        try{
            Category category = new Category();
            category.setName(cat.getName());
            if (cat.getImage()!=null) {
                category.setImage(imageService.saveImage(cat.getImage()));
            }
            category.setDescription(cat.getDescription());
            category.setCreatedAt(new Date());
            category.setUpdatedAt(new Date());
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
        try {
            if (!isExistById(id)) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Category not found")
                        .build();
            }
            if (imageService.deleteImage(categoryRepository.findById(id).get().getImage())) {
                categoryRepository.deleteById(id);
            }
            return ResponseObject.builder()
                    .code(200)
                    .message("Delete Category Successful")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Delete Category Error: " + e.getMessage())
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
            if (category.getImage() != null && !category.getImage().equals(cat.getImage())) {
                if (imageService.deleteImage(cat.getImage())) {
                    cat.setImage(imageService.saveImage(category.getImage()));
                }
            }
            cat.setDescription(category.getDescription());
            cat.setUpdatedAt(new Date());
            categoryRepository.save(cat);
            return ResponseObject.builder()
                    .code(200)
                    .message("Update Category Successful")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Update Category Error: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject getAllCategory() {
        try {
            List<Category> categories = categoryRepository.findAllParentCategory();
            List<CategoryResponse> categoryResponses = new ArrayList<>();
            categories.forEach(category -> {
                List<Category> childCategories = categoryRepository.findAllChildCategory(category.getId());
                List<CategoryResponse> childCategoryResponses = new ArrayList<>();
                childCategories.forEach(childCategory -> {
                    CategoryResponse childCategoryResponse = CategoryResponse.builder()
                            .id(childCategory.getId())
                            .name(childCategory.getName())
                            .image(childCategory.getImage())
                            .build();
                    childCategoryResponses.add(childCategoryResponse);
                });
                CategoryResponse categoryResponse = CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .image(category.getImage())
                        .child(childCategoryResponses)
                        .build();
                categoryResponses.add(categoryResponse);
            });
            return ResponseObject.builder()
                    .code(200)
                    .message("Get All Category Successful")
                    .data(categoryResponses)
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Get All Category Error: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject getAllCategoryParentAndChild() {
        try {
            List<Category> categories = categoryRepository.findAllParentCategory();
            List<CategoryDetailResponse> categoryResponses = new ArrayList<>();
            categories.forEach(category -> {
                List<Category> childCategories = categoryRepository.findAllChildCategory(category.getId());
                List<CategoryDetailResponse> childCategoryResponses = new ArrayList<>();
                childCategories.forEach(childCategory -> {
                    CategoryDetailResponse childCategoryResponse = CategoryDetailResponse.builder()
                            .id(childCategory.getId())
                            .name(childCategory.getName())
                            .image(childCategory.getImage())
                            .description(childCategory.getDescription())
                            .status(childCategory.getStatus())
                            .priority(childCategory.getPriority())
                            .created_at(childCategory.getCreatedAt())
                            .updated_at(childCategory.getUpdatedAt())
                            .build();
                    childCategoryResponses.add(childCategoryResponse);
                });
                CategoryDetailResponse categoryResponse = CategoryDetailResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .image(category.getImage())
                        .description(category.getDescription())
                        .status(category.getStatus())
                        .priority(category.getPriority())
                        .created_at(category.getCreatedAt())
                        .updated_at(category.getUpdatedAt())
                        .child(childCategoryResponses)
                        .build();
                categoryResponses.add(categoryResponse);
            });
            return ResponseObject.builder()
                    .code(200)
                    .message("Get All Category Successful")
                    .data(categoryResponses)
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Get All Category Error: " + e.getMessage())
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
