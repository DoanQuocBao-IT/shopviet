package com.project.shopviet.Service;

import com.project.shopviet.DTO.request.CategoryRequest;
import com.project.shopviet.DTO.response.ResponseObject;


public interface CategoryService {
    ResponseObject addCategory(CategoryRequest category);
    ResponseObject deleteCategory(int id);
    ResponseObject updateCategory(int id,CategoryRequest category);
    ResponseObject getAllCategory();
    ResponseObject getAllCategoryParentAndChild();
    ResponseObject getCategory(int id);
    boolean isExistById(int id);
}
