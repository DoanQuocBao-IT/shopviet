package com.project.shopviet.Repository;

import com.project.shopviet.Model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    List<Brand> getBrandByCategoryId(int id);
    List<Brand> getBrandByCategoryName(String name);
}
