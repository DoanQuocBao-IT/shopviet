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
import com.project.shopviet.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public ResponseObject addBrand(BrandRequest brand) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            User userSeller=userRepository.findUserByName(currentPrincipalName);
            Optional<Seller> seller=sellerRepository.findByUserId(userSeller.getId());
            if(seller.isEmpty()) {
                return ResponseObject.builder()
                        .code(400)
                        .message("Seller not found")
                        .build();
            }
            Brand newBrand=new Brand();
            newBrand.setName(brand.getName());
            newBrand.setImage(brand.getImage());
            newBrand.setUserSeller(seller.get());
            brandRepository.save(newBrand);
            return ResponseObject.builder()
                    .code(200)
                    .message("Add Brand Success")
                    .data(newBrand)
                    .build();
        }catch (IllegalArgumentException e){
            System.out.println("Add Brand Error: "+e.getMessage());
            return null;
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
            brandRepository.delete(brand);
            return ResponseObject.builder()
                    .code(200)
                    .message("Delete Brand Success")
                    .data(brand)
                    .build();

        } catch (IllegalArgumentException e) {
            System.out.println("Delete Brand Error: " + e.getMessage());
            return null;
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
            oldBrand.setImage(brand.getImage());
            brandRepository.save(oldBrand);
            return ResponseObject.builder()
                    .code(200)
                    .message("Update Brand Success")
                    .data(oldBrand)
                    .build();

        } catch (IllegalArgumentException e) {
            System.out.println("Update Brand Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ResponseObject getAllBrand() {
        try{
            List<Brand> brands=brandRepository.findAll();
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
        }catch (IllegalArgumentException e){
            System.out.println("Get All Brand Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isExistById(int id) {
        return brandRepository.existsById(id);
    }
}
