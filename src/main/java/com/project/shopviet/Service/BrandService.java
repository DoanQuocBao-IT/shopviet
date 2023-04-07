package com.project.shopviet.Service;

import com.project.shopviet.Model.Brand;
import com.project.shopviet.Model.Category;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    Brand addBrand(Brand brand);
    String deleteBrand(int id);
    Brand updateBrand(int id,Brand brand);
    void updateBrandForCategory(int category_id,int brand_id);
    List<Brand> getAllBrand();
    Optional<Brand> getBrandByCategoryId(int id);
    Optional<Brand> getBrand(int id);
    boolean isExistById(int id);
}
