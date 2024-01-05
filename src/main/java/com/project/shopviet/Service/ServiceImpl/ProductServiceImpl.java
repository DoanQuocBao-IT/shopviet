package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.*;
import com.project.shopviet.DTO.request.ProductRequest;
import com.project.shopviet.DTO.response.*;
import com.project.shopviet.Model.*;
import com.project.shopviet.Repository.*;
import com.project.shopviet.Service.ImageService;
import com.project.shopviet.Service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;
    @Override
    public ResponseObject addProductBySeller(ProductRequest productRequest) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userSeller = userRepository.findUserByName(authentication.getName());
            Optional<Seller> seller = sellerRepository.findByUserId(userSeller.getId());
            if (!seller.isPresent()){
                return ResponseObject.builder()
                        .code(400)
                        .message("You are not a seller")
                        .build();
            }
            Product product = new Product();
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setDiscount(productRequest.getDiscount());
            product.setInventory(productRequest.getInventory());
            Optional<Brand> brand=brandRepository.findById(productRequest.getBrand_id());
            if (brand.isEmpty()){
                return ResponseObject.builder()
                        .code(400)
                        .message("Brand not found")
                        .build();
            }
            brand.get().setProductCount(brand.get().getProductCount()+1);
            brandRepository.save(brand.get());
            product.setBrand(brand.get());
            Optional<Category> category=categoryRepository.findById(productRequest.getCategory_id());
            if (category.isEmpty()){
                return ResponseObject.builder()
                        .code(400)
                        .message("Category not found")
                        .build();
            }
            product.setCategory(category.get());
            product.setSeller(seller.get());
            product.setCreatedAt(new Date());
            product.setUpdatedAt(new Date());
            productRepository.save(product);
            productRequest.getProductTypes().forEach(productTypeRequest -> {
                ProductType productType=new ProductType();
                productType.setProduct(product);
                productType.setName(productTypeRequest.getName());
                productType.setImage(productTypeRequest.getImage());
                productType.setPrice(productTypeRequest.getPrice());
                productType.setQuantity(productTypeRequest.getQuantity());
                productTypeRepository.save(productType);
            });
            return ResponseObject.builder()
                    .code(200)
                    .message("Add Product Successful")
                    .build();

        }catch (IllegalArgumentException e){
            return ResponseObject.builder()
                    .code(400)
                    .message("Add Product Error: "+e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject deleteProduct(int id) {
        try{
            if (!isExistById(id)){
                return ResponseObject.builder()
                        .code(400)
                        .message("Product not found")
                        .build();
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userSeller = userRepository.findUserByName(authentication.getName());
            Optional<Seller> seller = sellerRepository.findByUserId(userSeller.getId());
            if (!seller.isPresent()){
                return ResponseObject.builder()
                        .code(400)
                        .message("You are not a seller")
                        .build();
            }
            Optional<Product> product=productRepository.findByIdAndSellerId(id,seller.get().getId());
            if (product.isEmpty()){
                return ResponseObject.builder()
                        .code(400)
                        .message("Product not found")
                        .build();
            }
            List<ProductType> productTypes=productTypeRepository.findByProduct_Id(id);
            productTypeRepository.deleteAll(productTypes);
            productRepository.delete(product.get());
            return ResponseObject.builder()
                    .code(200)
                    .message("Delete Product Successful")
                    .build();
        }catch (IllegalArgumentException e){
            return ResponseObject.builder()
                    .code(400)
                    .message("Delete Product Error: "+e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject updateProduct(int id, ProductRequest productRequest) {
        try {
            if (!isExistById(id)) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Product not found")
                        .build();
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userSeller = userRepository.findUserByName(authentication.getName());
            Optional<Seller> seller = sellerRepository.findByUserId(userSeller.getId());
            if (seller.isEmpty()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("You are not a seller")
                        .build();
            }
            Optional<Product> product = productRepository.findByIdAndSellerId(id, seller.get().getId());
            if (product.isEmpty()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Product not found")
                        .build();
            }
            Product productUpdate = product.get();
            productUpdate.setName(!Objects.equals(productRequest.getName(), "") ? productRequest.getName() : productUpdate.getName());
            productUpdate.setDescription(!Objects.equals(productRequest.getDescription(), "") ? productRequest.getDescription() : productUpdate.getDescription());
            productUpdate.setDiscount(productRequest.getDiscount() != productUpdate.getDiscount() ? productRequest.getDiscount() : productUpdate.getDiscount());
            productUpdate.setInventory(productRequest.getInventory() != productUpdate.getInventory() ? productRequest.getInventory() : productUpdate.getInventory());
            Optional<Brand> brand = brandRepository.findById(productRequest.getBrand_id());
            if (brand.isEmpty()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Brand not found")
                        .build();
            }
            productUpdate.setBrand(brand.get());
            Optional<Category> category = categoryRepository.findById(productRequest.getCategory_id());
            if (category.isEmpty()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Category not found")
                        .build();
            }
            productUpdate.setCategory(category.get());
            productUpdate.setUpdatedAt(new Date());
            productRepository.save(productUpdate);
            return ResponseObject.builder()
                    .code(200)
                    .message("Update Product Successful")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Update Product Error: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseObject getAllProduct(int category_id, String sort, int perPage, int currentPage) {
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
            Page<Product> productPage = new PageImpl<>(Collections.emptyList());
            if (category_id==0) {
                productPage = productRepository.findAll(pageable);
            }else if (category_id>0){
                productPage = productRepository.findAllByCategoryId(category_id,pageable);
            }
            List<ProductDto> productDtoPage=productPage.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
            return ResponseObject.builder()
                    .code(200)
                    .message("Get All Product Successful")
                    .data(PageProductResponse.builder()
                            .total_page(productPage.getTotalPages())
                            .current_page(currentPage)
                            .per_page(perPage)
                            .total_product(productPage.getTotalElements())
                            .data(productDtoPage)
                            .build())
                    .build();
        }catch (IllegalArgumentException e){
            System.out.println("Get All Product Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseObject getAllProduct(int brand_id, int seller_id, String sort, int perPage, int currentPage) {
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
            Page<Product> productPage = new PageImpl<>(Collections.emptyList());
            if (brand_id==0 && seller_id==0) {
                productPage = productRepository.findAll(pageable);
            }else if (brand_id>0 && seller_id==0){
                productPage = productRepository.findAllByBrandId(brand_id,pageable);
            }else if (brand_id==0 && seller_id>0){
                productPage = productRepository.findAllBySellerId(seller_id,pageable);
            }else if (brand_id>0 && seller_id>0){
                productPage = productRepository.findAllByBrandIdAndSellerId(brand_id,seller_id,pageable);
            }
            List<ProductDto> productDtoPage=productPage.stream().map(product -> modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
            return ResponseObject.builder()
                    .code(200)
                    .message("Get All Product Successful")
                    .data(PageProductResponse.builder()
                            .total_page(productPage.getTotalPages())
                            .current_page(currentPage)
                            .per_page(perPage)
                            .total_product(productPage.getTotalElements())
                            .data(productDtoPage)
                            .build())
                    .build();
        }catch (IllegalArgumentException e){
            return ResponseObject.builder()
                    .code(400)
                    .message("Get All Product Error: "+e.getMessage())
                    .build();
        }
    }


    @Override
    public ResponseObject findProductById(int id) {
        try{
            Optional<Product> product=productRepository.findById(id);
            if (product.isEmpty()){
                return ResponseObject.builder()
                        .code(400)
                        .message("Product not found")
                        .build();
            }
            Seller seller = sellerRepository.findById(id).get();
            Brand brand = brandRepository.findById(id).get();
            Category category = categoryRepository.findById(id).get();
            List<ProductType> productTypes=productTypeRepository.findByProduct_Id(id);
            return ResponseObject.builder()
                    .code(200)
                    .message("Get Product Successful")
                    .data(ProductDetailResponse.builder()
                            .id(product.get().getId())
                            .name(product.get().getName())
                            .discount(product.get().getDiscount())
                            .inventory(product.get().getInventory())
                            .sold(product.get().getSold())
                            .description(product.get().getDescription())
                            .rate(product.get().getRate())
                            .seller(UserSellerResponse.builder()
                                    .id(seller.getId())
                                    .name_store(seller.getNameStore())
                                    .image(seller.getImage())
                                    .totalProduct(seller.getTotalProduct())
                                    .totalFollower(seller.getTotalFollower())
                                    .totalFeedback(seller.getTotalFeedback())
                                    .mall(seller.isMall())
                                    .createdAt(seller.getCreatedAt())
                                    .build())
                            .brand(BrandResponse.builder()
                                    .id(brand.getId())
                                    .name(brand.getName())
                                    .image(brand.getImage())
                                    .build())
                            .category(CategoryResponse.builder()
                                    .id(category.getId())
                                    .name(category.getName())
                                    .image(category.getImage())
                                    .build())
                            .productTypes(productTypes.stream().map(productType -> ProductTypeResponse.builder()
                                    .id(productType.getId())
                                    .name(productType.getName())
                                    .image(productType.getImage())
                                    .price(productType.getPrice())
                                    .quantity(productType.getQuantity())
                                    .build()).collect(Collectors.toList()))
                            .build())

                    .build();
        }catch (IllegalArgumentException e){
            System.out.println("Get Product Error: "+e.getMessage());
            return null;
        }
    }


    @Override
    public ResponseObject get3ProductBestSellerForBrand(int brand_id) {
        List<Product> products = productRepository.findTop3ByBrandIdOrderBySoldDesc(brand_id);
        return ResponseObject.builder()
                .code(200)
                .message("Get 3 Product Best Seller For Brand Successful")
                .data(products.stream().map(product -> ProductBestSellerResponse.class).collect(Collectors.toList()))
                .build();
    }


    @Override
    public ResponseObject findProductByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        ModelMapper modelMapper = new ModelMapper();
        return ResponseObject.builder()
                .code(200)
                .message("Get Product Successful")
                .data(products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList()))
                .build();
    }

    @Override
    public boolean isExistById(int id) {
        return productRepository.existsById(id);
    }
}
