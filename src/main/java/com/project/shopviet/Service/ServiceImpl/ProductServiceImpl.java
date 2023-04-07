package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.Model.Category;
import com.project.shopviet.Model.Product;
import com.project.shopviet.Repository.ProductRepository;
import com.project.shopviet.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        try{
            return productRepository.save(product);
        }catch (IllegalArgumentException e){
            System.out.println("Add Category Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public String deleteProduct(int id) {
        try{
            productRepository.deleteById(id);
            return "Delete Product Successful";
        }catch (IllegalArgumentException e){
            System.out.println("Delete Category Error: "+e.getMessage());
            return "Delete Category Error: "+e.getMessage();
        }
    }

    @Override
    public Product updateProduct(int id, Product product) {
        try {
            if (isExistById(id)) {
                Product currentProduct = productRepository.findById(id).get();
                currentProduct.setName(product.getName());
                currentProduct.setDescription(product.getDescription());
                currentProduct.setInventory(product.getInventory());
                currentProduct.setPrice(product.getPrice());
                currentProduct.setRate(product.getRate());
                currentProduct.setImage(product.getImage());

                productRepository.save(currentProduct);
                return currentProduct;
            } else return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Update Product Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> getAllProduct() {
        try{
            return (List<Product>) productRepository.findAll();
        }catch (IllegalArgumentException e){
            System.out.println("Get All Product Error: "+e.getMessage());
            return null;
        }
    }


    @Override
    public Optional<Product> findProductById(int id) {
        try {
            return productRepository.getProductById(id);
        }catch (IllegalArgumentException e){
            System.out.println("Get Product Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<Product> getProductForCategory(int category_id) {
        try {
            return productRepository.getProductByCategoryId(category_id);
        }catch (IllegalArgumentException e){
            System.out.println("Get Product Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<Product> getProductForBrand(int brand_id) {
        try {
            return productRepository.getProductByBrandId(brand_id);
        }catch (IllegalArgumentException e){
            System.out.println("Get Product Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<String> getAllImageProduct() {
        try{
            return (List<String>) productRepository.getAllImageProduct();
        }catch (IllegalArgumentException e){
            System.out.println("Get All Image Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isExistById(int id) {
        return productRepository.existsById(id);
    }
}
