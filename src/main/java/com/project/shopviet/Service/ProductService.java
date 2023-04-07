package com.project.shopviet.Service;

import com.project.shopviet.Model.Category;
import com.project.shopviet.Model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product addProduct(Product product);
    String deleteProduct(int id);
    Product updateProduct(int id,Product product);
    List<Product> getAllProduct();
    Optional<Product> findProductById(int id);
    Optional<Product> getProductForCategory(int category_id);
    Optional<Product> getProductForBrand(int brand_id);
    List<String> getAllImageProduct();
    boolean isExistById(int id);
}
