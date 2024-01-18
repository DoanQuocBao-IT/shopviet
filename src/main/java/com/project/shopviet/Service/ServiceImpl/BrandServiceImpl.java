package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.request.BrandRequest;
import com.project.shopviet.DTO.response.BrandResponse;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.Model.Brand;
import com.project.shopviet.Model.Seller;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.BrandRepository;
import com.project.shopviet.Repository.SellerRepository;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.BrandService;
import com.project.shopviet.Service.ImageService;
import com.project.shopviet.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ImageService imageService;

    @Override
    public ResponseObject addBrand(BrandRequest brand) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User userSeller = userRepository.findUserByName(currentPrincipalName);
            Optional<Seller> seller = sellerRepository.findByUserId(userSeller.getId());
            if (seller.isEmpty()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Seller not found")
                        .build();
            }
            Brand newBrand = new Brand();
            newBrand.setName(brand.getName());
            if (!Objects.equals(brand.getImage(), "")) {
                newBrand.setImage(imageService.saveImage(brand.getImage()));
            }else{
                newBrand.setImage("");
            }
            newBrand.setUserSeller(seller.get());
            brandRepository.save(newBrand);
            return ResponseObject.builder()
                    .code(200)
                    .message("Add Brand Success")
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Add Brand Error")
                    .build();
        }
    }

    @Override
    public ResponseObject deleteBrand(int id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User userSeller = userRepository.findUserByName(currentPrincipalName);
            Optional<Seller> seller = sellerRepository.findByUserId(userSeller.getId());
            if (seller.isEmpty()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Seller not found")
                        .build();
            }
            if (!isExistById(id)) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Brand not found")
                        .build();
            }
            Brand brand = brandRepository.findByIdAndUserSellerId(id, seller.get().getId()).get();
            if (!Objects.equals(brand.getImage(), "")) {
                if (imageService.deleteImage(brand.getImage())) {
                    brandRepository.delete(brand);
                }
            } else {
                brandRepository.delete(brand);
            }
            return ResponseObject.builder()
                    .code(200)
                    .message("Delete Brand Success")
                    .build();

        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Delete Brand Error")
                    .build();
        }
    }

    @Override
    public ResponseObject updateBrand(int id, BrandRequest brand) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User userSeller = userRepository.findUserByName(currentPrincipalName);
            Optional<Seller> seller = sellerRepository.findByUserId(userSeller.getId());
            if (seller.isEmpty()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Seller not found")
                        .build();
            }
            if (!isExistById(id)) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Brand not found")
                        .build();
            }
            Brand oldBrand = brandRepository.findByIdAndUserSellerId(id, seller.get().getId()).get();
            oldBrand.setName(brand.getName());
            if (!Objects.equals(brand.getImage(), "") && Objects.equals(oldBrand.getImage(), "")) {
                oldBrand.setImage(imageService.saveImage(brand.getImage()));
            } else if (!Objects.equals(brand.getImage(), "") && !Objects.equals(brand.getImage(), oldBrand.getImage())) {
                if (imageService.deleteImage(oldBrand.getImage())) {
                    oldBrand.setImage(imageService.saveImage(brand.getImage()));
                }
            }
            brandRepository.save(oldBrand);
            return ResponseObject.builder()
                    .code(200)
                    .message("Update Brand Success")
                    .build();

        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Update Brand Error")
                    .build();
        }
    }

    @Override
    public ResponseObject getAllBrand() {
        try {
            List<Brand> brands = brandRepository.findAll();
            return ResponseObject.builder()
                    .code(200)
                    .message("Get All Brand Success")
                    .data(brands.stream().map(brand -> {
                        return BrandResponse.builder()
                                .id(brand.getId())
                                .name(brand.getName())
                                .image(brand.getImage())
                                .total_product(brand.getProductCount())
                                .build();
                    }).collect(Collectors.toList()))
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Get All Brand Error")
                    .build();
        }
    }

    @Override
    public ResponseObject getBrandBySeller() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User userSeller = userRepository.findUserByName(currentPrincipalName);
            Optional<Seller> seller = sellerRepository.findByUserId(userSeller.getId());
            if (seller.isEmpty()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Seller not found")
                        .build();
            }
            List<Brand> brands = brandRepository.findAllByUserSellerId(seller.get().getId());
            return ResponseObject.builder()
                    .code(200)
                    .message("Get All Brand Success")
                    .data(brands.stream().map(brand -> {
                        return BrandResponse.builder()
                                .id(brand.getId())
                                .name(brand.getName())
                                .image(brand.getImage())
                                .total_product(brand.getProductCount())
                                .build();
                    }).collect(Collectors.toList()))
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Get All Brand Error")
                    .build();
        }
    }

    @Override
    public ResponseObject getBrandBySellerId(int id) {
        try {
            List<Brand> brands = brandRepository.findAllByUserSellerId(id);
            return ResponseObject.builder()
                    .code(200)
                    .message("Get All Brand Success")
                    .data(brands.stream().map(brand -> {
                        return BrandResponse.builder()
                                .id(brand.getId())
                                .name(brand.getName())
                                .image(brand.getImage())
                                .total_product(brand.getProductCount())
                                .build();
                    }).collect(Collectors.toList()))
                    .build();
        } catch (IllegalArgumentException e) {
            return ResponseObject.builder()
                    .code(400)
                    .message("Get All Brand Error")
                    .build();
        }
    }

    @Override
    public boolean isExistById(int id) {
        return brandRepository.existsById(id);
    }
}
