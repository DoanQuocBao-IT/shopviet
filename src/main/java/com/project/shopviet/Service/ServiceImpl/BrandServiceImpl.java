package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.Model.Brand;
import com.project.shopviet.Repository.BrandRepository;
import com.project.shopviet.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public Brand addBrand(Brand brand) {
        try {
            return brandRepository.save(brand);
        }catch (IllegalArgumentException e){
            System.out.println("Add Brand Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public String deleteBrand(int id) {
        try {
            brandRepository.deleteById(id);
            return "Xoa thanh cong Brand";
        }catch (IllegalArgumentException e){
            System.out.println("Delete Brand Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Brand updateBrand(int id, Brand brand) {
        try {
            if(isExistById(id)){
                Brand currentBrand=brandRepository.findById(id).get();
                currentBrand.setName(brand.getName());
                currentBrand.setImage(brand.getImage());
                brandRepository.save(currentBrand);
                return currentBrand;
            }
            else return null;
        }catch (IllegalArgumentException e){
            System.out.println("Update Brand Error: "+e.getMessage());
            return null;
        }
    }
    @Transactional
    @Override
    public void updateBrandForCategory(int category_id, int brand_id) {
        try {
            if(isExistById(brand_id)){
                brandRepository.updateBrandForCategory(category_id,brand_id);
            }
        }catch (IllegalArgumentException e){
            System.out.println("Update Brand Error: "+e.getMessage());
        }
    }

    @Override
    public List<Brand> getAllBrand() {
        try{
            return (List<Brand>) brandRepository.findAll();
        }catch (IllegalArgumentException e){
            System.out.println("Get All Brand Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<Brand> getBrandByCategoryId(int id) {
        try{
            return (Optional<Brand>) brandRepository.getBrandByCategoryId(id);
        }catch (IllegalArgumentException e){
            System.out.println("Get All Brand Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<Brand> getBrand(int id) {
        try{
            return (Optional<Brand>) brandRepository.findById(id);
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
