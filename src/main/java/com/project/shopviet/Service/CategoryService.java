package com.project.shopviet.Service;

import com.project.shopviet.DTO.CategoryDto;
import com.project.shopviet.DTO.response.CategoryResponse;
import com.project.shopviet.Model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category addCategory(Category category);
    String deleteCategory(int id);
    Category updateCategory(int id,Category category);
    List<CategoryResponse>  getAllCategory();
    List<CategoryDto> getAllCategoryDto();
    Optional<Category>  getCategory(int id);
    boolean isExistById(int id);
}
