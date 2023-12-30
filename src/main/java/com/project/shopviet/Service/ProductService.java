package com.project.shopviet.Service;

import com.project.shopviet.DTO.request.ProductRequest;
import com.project.shopviet.DTO.response.ResponseObject;

public interface ProductService {
    ResponseObject addProductBySeller(ProductRequest product);
    ResponseObject deleteProduct(int id);
    ResponseObject updateProduct(int id,ProductRequest productRequest);
    ResponseObject getAllProduct(int category_id, String sort, int perPage, int currentPage);
    ResponseObject getAllProduct(int brand_id,int seller_id,String sort, int perPage, int currentPage);

    ResponseObject findProductById(int id);
    ResponseObject get3ProductBestSellerForBrand(int brand_id);
    ResponseObject findProductByName(String name);
    boolean isExistById(int id);
}
