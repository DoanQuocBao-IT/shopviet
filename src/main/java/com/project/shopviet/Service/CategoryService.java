package com.project.shopviet.Service;

import com.project.shopviet.Model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category addCategory(Category category);
    String deleteCategory(int id);
    Category updateCategory(int id,Category category);
    List<Category>  getAllCategory();
    Optional<Category>  getCategory(int id);
    boolean isExistById(int id);
}
