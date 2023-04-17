package com.project.shopviet.Service;

import com.project.shopviet.DTO.ProductDetailDto;
import com.project.shopviet.DTO.ProductDto;
import com.project.shopviet.Model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProductBySeller(Product product);
    String deleteProduct(int id);
    Product updateProduct(int id,Product product);
    List<ProductDto> getAllProduct(String sort);
    Optional<ProductDetailDto> findProductById(int id);
    List<ProductDto> getProductForCategory(int category_id);
    List<ProductDto> getProductForBrand(int brand_id);
    List<String> getAllImageProduct();
    List<ProductDto> getProductBySeller(int seller_id);
    boolean isExistById(int id);
}
