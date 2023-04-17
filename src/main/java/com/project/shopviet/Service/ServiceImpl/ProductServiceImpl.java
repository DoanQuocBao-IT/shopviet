package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.ProductDetailDto;
import com.project.shopviet.DTO.ProductDto;
import com.project.shopviet.Model.Product;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.ProductRepository;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Product addProductBySeller(Product product) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userSeller = userRepository.findUserByName(authentication.getName());
            product.setUserSeller(userSeller);
            product.setCreatedAt(new Date());
            return productRepository.save(product);
        }catch (IllegalArgumentException e){
            System.out.println("Add Product By Seller Error: "+e.getMessage());
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
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User userSeller = userRepository.findUserByName(authentication.getName());
                currentProduct.setUserSeller(userSeller);
                currentProduct.setName(product.getName());
                currentProduct.setDescription(product.getDescription());
                currentProduct.setInventory(product.getInventory());
                currentProduct.setPrice(product.getPrice());
                currentProduct.setImage(product.getImage());
                currentProduct.setBrand(product.getBrand());
                currentProduct.setCreatedAt(new Date());
                productRepository.save(currentProduct);
                return currentProduct;
            } else return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Update Product Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<ProductDto> getAllProduct(String sort) {
        try{
            List<Product> products=productRepository.findAll();
            ModelMapper modelMapper=new ModelMapper();
            if (sort.equals("priceAsc")) {
                products.sort(Comparator.comparing(Product::getPrice));
            } else if (sort.equals("priceDesc")) {
                products.sort(Comparator.comparing(Product::getPrice).reversed());
            } else if (sort.equals("nameAsc")) {
                products.sort(Comparator.comparing(Product::getName).reversed());
            } else if (sort.equals("newest")) {
                products.sort(Comparator.comparing(Product::getCreatedAt).reversed());
            }else if (sort.equals("soleAsc")) {
                products.sort(Comparator.comparing(Product::getSold));
            }else if (sort.equals("soldDesc")) {
                products.sort(Comparator.comparing(Product::getSold).reversed());
            }
            return products.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
        }catch (IllegalArgumentException e){
            System.out.println("Get All Product Error: "+e.getMessage());
            return null;
        }
    }


    @Override
    public Optional<ProductDetailDto> findProductById(int id) {
        try {
            Optional<Product> productDetail=productRepository.getProductById(id);
            ModelMapper modelMapper=new ModelMapper();
            return productDetail.map(product -> modelMapper.map(product,ProductDetailDto.class));

        }catch (IllegalArgumentException e){
            System.out.println("Get Product Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<ProductDto> getProductForCategory(int category_id) {
        try {
            List<Product> products=productRepository.getProductByCategoryId(category_id);
            ModelMapper modelMapper=new ModelMapper();

            return products.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
        }catch (IllegalArgumentException e){
            System.out.println("Get Product Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<ProductDto> getProductForBrand(int brand_id) {
        try {
            List<Product> products=productRepository.getProductByBrandId(brand_id);
            ModelMapper modelMapper=new ModelMapper();
            return products.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
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
    public List<ProductDto> getProductBySeller(int seller_id) {
        try {
            List<Product> products=productRepository.getProductBySellerId(seller_id);
            ModelMapper modelMapper=new ModelMapper();
            return products.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
        }catch (IllegalArgumentException e){
            System.out.println("Get Product Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isExistById(int id) {
        return productRepository.existsById(id);
    }
}
