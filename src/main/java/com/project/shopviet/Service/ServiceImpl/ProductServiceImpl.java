package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.ProductDetailDto;
import com.project.shopviet.DTO.ProductDto;
import com.project.shopviet.DTO.response.PagedProductResponse;
import com.project.shopviet.Model.Product;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.ProductRepository;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PagedProductResponse getAllProduct(String sort, int perPage, int currentPage) {
        try{
            Sort sorting;
            switch (sort) {
                case "priceAsc":
                    sorting = Sort.by("price").ascending();
                    break;
                case "priceDesc":
                    sorting = Sort.by("price").descending();
                    break;
                case "nameAsc":
                    sorting = Sort.by("name").ascending();
                    break;
                case "nameDesc":
                    sorting = Sort.by("name").descending();
                    break;
                case "newest":
                    sorting = Sort.by("createdAt").descending();
                    break;
                case "soldAsc":
                    sorting = Sort.by("sold").ascending();
                    break;
                case "soldDesc":
                    sorting = Sort.by("sold").descending();
                    break;
                default:
                    sorting = Sort.unsorted();  // Hoặc bạn có thể đặt một sắp xếp mặc định tại đây
                    break;
            }
            Pageable pageable;
            if (sort != null) {
                pageable = PageRequest.of(currentPage - 1, perPage, sorting); // currentPage - 1 vì trang trong Spring Data JPA bắt đầu từ 0
            } else {
                pageable = PageRequest.of(currentPage - 1, perPage);
            }
            ModelMapper modelMapper=new ModelMapper();
            // Lấy trang sản phẩm
            Page<Product> productPage = productRepository.findAll(pageable);
            List<ProductDto> productDtoPage=productPage.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
            return PagedProductResponse.builder()
                    .total_page(productPage.getTotalPages())
                    .current_page(currentPage)
                    .per_page(perPage)
                    .total_product(productPage.getTotalElements())
                    .products(productDtoPage)
                    .build();
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
    public List<ProductDto> findProductByName(String name) {
        List<Product> products=productRepository.findByNameContainingIgnoreCase(name);
        ModelMapper modelMapper=new ModelMapper();
        return products.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public boolean isExistById(int id) {
        return productRepository.existsById(id);
    }
}
