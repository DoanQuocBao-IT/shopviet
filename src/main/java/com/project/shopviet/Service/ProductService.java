package com.project.shopviet.Service;

import com.project.shopviet.DTO.ProductDetailDto;
import com.project.shopviet.DTO.ProductDto;
import com.project.shopviet.DTO.ProductImageDto;
import com.project.shopviet.DTO.request.ProductRequest;
import com.project.shopviet.DTO.response.BrandProductResponse;
import com.project.shopviet.DTO.response.PagedProductResponse;
import com.project.shopviet.DTO.response.ProductsIdResponse;
import com.project.shopviet.Model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProductBySeller(ProductRequest product);
    String deleteProduct(int id);
    Product updateProduct(int id,ProductRequest productRequest);
    PagedProductResponse getAllProduct(String sort, int perPage, int currentPage);
    ProductDetailDto findProductById(int id);
    List<ProductDto> getProductForCategory(int category_id);
    List<ProductDto> getProductForBrand(int brand_id);
    BrandProductResponse get3ProductBestSellerForBrand(int brand_id);
    List<String> getAllImageProduct();
    PagedProductResponse getProductBySeller(int seller_id, int perPage, int currentPage);
    List<ProductDto> findProductByName(String name);
    List<ProductsIdResponse> getAllProductId();
    boolean isExistById(int id);
}
