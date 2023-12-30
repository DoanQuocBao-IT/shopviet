package com.project.shopviet.Service;

import com.project.shopviet.DTO.CategoryDto;
import com.project.shopviet.DTO.ListCategoriesDto;
import com.project.shopviet.DTO.request.CategoryRequest;
import com.project.shopviet.DTO.response.CategoryResponse;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.Model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    ResponseObject addCategory(CategoryRequest category);
    ResponseObject deleteCategory(int id);
    ResponseObject updateCategory(int id,CategoryRequest category);
    ResponseObject getAllCategory();
    ResponseObject getCategory(int id);
    boolean isExistById(int id);
}
