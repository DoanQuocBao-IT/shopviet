package com.project.shopviet.Service;

import com.project.shopviet.DTO.request.BrandRequest;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.Model.Brand;


public interface BrandService {
    ResponseObject addBrand(BrandRequest brand);
    ResponseObject deleteBrand(int id);
    ResponseObject updateBrand(int id,BrandRequest brand);
    ResponseObject getAllBrand();
    boolean isExistById(int id);
}
