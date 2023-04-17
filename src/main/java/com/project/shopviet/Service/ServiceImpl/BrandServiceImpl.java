package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.DTO.BrandDto;
import com.project.shopviet.Model.Brand;
import com.project.shopviet.Repository.BrandRepository;
import com.project.shopviet.Service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<BrandDto> getAllBrand() {
        try{
            List<Brand> brands=brandRepository.findAll();
            ModelMapper modelMapper = new ModelMapper(); // khởi tạo đối tượng modelMapper
            return brands.stream().map(brand -> modelMapper.map(brand,BrandDto.class)).collect(Collectors.toList());
        }catch (IllegalArgumentException e){
            System.out.println("Get All Brand Error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<BrandDto> getBrandByCategoryId(int id) {
        try{
            List<Brand> brands=brandRepository.getBrandByCategoryId(id);
            ModelMapper modelMapper = new ModelMapper(); // khởi tạo đối tượng modelMapper
            return brands.stream().map(brand -> modelMapper.map(brand,BrandDto.class)).collect(Collectors.toList());
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
