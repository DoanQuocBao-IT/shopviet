package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.*;
import com.project.shopviet.DTO.request.ProductRequest;
import com.project.shopviet.DTO.response.BrandProductResponse;
import com.project.shopviet.DTO.response.PagedProductResponse;
import com.project.shopviet.DTO.response.ProductsIdResponse;
import com.project.shopviet.Model.Brand;
import com.project.shopviet.Model.Product;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.BrandRepository;
import com.project.shopviet.Repository.FollowRepository;
import com.project.shopviet.Repository.ProductRepository;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.ImageService;
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
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    FollowRepository followRepository;
    @Override
    public Product addProductBySeller(ProductRequest productRequest) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userSeller = userRepository.findUserByName(authentication.getName());
            Product product=new Product();
            product.setUserSeller(userSeller);
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setInventory(productRequest.getQuantity());
            product.setPrice(productRequest.getPrice());
            product.setDiscount(productRequest.getDiscount());
            Brand brand= brandRepository.findById(productRequest.getBrand_id()).get();
            product.setBrand(brand);
            product.setImage("http://localhost:8080/images/"+imageService.saveImage(productRequest.getImage()));
            product.setCreatedAt(new Date());
            product.setUpdatedAt(new Date());
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
    public Product updateProduct(int id, ProductRequest productRequest) {
        try {
            if (isExistById(id)) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User userSeller = userRepository.findUserByName(authentication.getName());
                Product product = productRepository.getProductByIdAndUserSellerId(id, userSeller.getId()).get();
                product.setName(productRequest.getName());
                product.setDescription(productRequest.getDescription());
                product.setInventory(productRequest.getQuantity());
                product.setPrice(productRequest.getPrice());
                product.setDiscount(productRequest.getDiscount());
                Brand brand= brandRepository.findById(productRequest.getBrand_id()).get();
                product.setBrand(brand);
                if (product.getImage().equals(productRequest.getImage())){
                    product.setImage(productRequest.getImage());
                }else{
                    if (imageService.deleteImage(product.getImage())){
                        System.out.println("Delete Image Successful");
                        product.setImage("http://localhost:8080/images/"+imageService.saveImage(productRequest.getImage()));
                    }else{
                        System.out.println("Delete Image Error");
                        return null;
                    }
                }
                product.setUpdatedAt(new Date());
                return productRepository.save(product);
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
    public ProductDetailDto findProductById(int id) {
        Product product=productRepository.findById(id).get();
        int followers=followRepository.countFollowByFollowedUserId(product.getUserSeller().getId());
        int followings=followRepository.countFollowByUserId(product.getUserSeller().getId());
        int severity=(product.getInventory()-product.getSold())*100/product.getInventory();
        String severity_status="";
        if (severity>=80){
            severity_status="High Stock";
        } else if (severity>=50){
            severity_status="Medium Stock";
        } else if (severity>=20){
            severity_status="Low Stock";
        } else if (severity>0){
            severity_status="Very Low Stock";
        } else {
            severity_status="Out of Stock";
        }
        return ProductDetailDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .inventory(product.getInventory())
                .severity(severity_status)
                .sold(product.getSold())
                .description(product.getDescription())
                .image(product.getImage())
                .rate(product.getRate())
                .seller(UserSellerDto.builder()
                        .id(product.getUserSeller().getId())
                        .fname(product.getUserSeller().getFname())
                        .image(product.getUserSeller().getImage())
                        .address(product.getUserSeller().getAddress())
                        .follower(followers+"")
                        .following(followings+"")
                        .build())
                .brand(BrandDto.builder()
                        .id(product.getBrand().getId())
                        .name(product.getBrand().getName())
                        .image(product.getBrand().getImage())
                        .build())
                .reviews(product.getReviews().stream().map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .comment(review.getComment())
                        .rating(review.getRating())
                        .userBuyer(UserSellerDto.builder()
                                .id(review.getUserBuyer().getId())
                                .fname(review.getUserBuyer().getFname())
                                .image(review.getUserBuyer().getImage())
                                .build())
                        .build()).collect(Collectors.toList()))
                .build();
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
    public BrandProductResponse get3ProductBestSellerForBrand(int brand_id) {
        List<Product> products=productRepository.getProductByBrandId(brand_id);
        ModelMapper modelMapper=new ModelMapper();
        List<ProductImageDto> productImageDtos=new ArrayList<>();
        for (int i=0;i<1;i++){
            productImageDtos.add(modelMapper.map(products.get(i),ProductImageDto.class));
        }
        return BrandProductResponse.builder()
                .brand_id(brand_id)
                .name(products.get(0).getBrand().getName())
                .image(products.get(0).getBrand().getImage())
                .user_seller(modelMapper.map(products.get(0).getUserSeller(), UserSellerDto.class))
                .products(productImageDtos)
                .build();
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
    public PagedProductResponse getProductBySeller(int seller_id, int perPage, int currentPage) {
        try {
            Pageable pageable=PageRequest.of(currentPage-1,perPage);
            Page<Product> productPage=productRepository.getProductsUserSellerByUserSellerId(seller_id,pageable);
            ModelMapper modelMapper=new ModelMapper();
            List<ProductDto> productDtoPage=productPage.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
            return PagedProductResponse.builder()
                    .total_page(productPage.getTotalPages())
                    .current_page(currentPage)
                    .per_page(perPage)
                    .total_product(productPage.getTotalElements())
                    .products(productDtoPage)
                    .build();
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
    public List<ProductsIdResponse> getAllProductId() {
        List<Product> products=productRepository.findAll();
        ModelMapper modelMapper=new ModelMapper();
        return products.stream().map(product -> modelMapper.map(product,ProductsIdResponse.class)).collect(Collectors.toList());
    }

    @Override
    public boolean isExistById(int id) {
        return productRepository.existsById(id);
    }
}
