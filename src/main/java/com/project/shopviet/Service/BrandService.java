package com.project.shopviet.Service;

import com.project.shopviet.DTO.BrandDto;
import com.project.shopviet.Model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    Brand addBrand(Brand brand);
    String deleteBrand(int id);
    Brand updateBrand(int id,Brand brand);
    List<BrandDto> getAllBrand();
    List<BrandDto> getBrandByCategoryId(int id);
    Optional<Brand> getBrand(int id);
    boolean isExistById(int id);
}
