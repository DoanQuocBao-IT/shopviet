package com.project.shopviet.Repository;

import com.project.shopviet.Model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    @Query(value = "select b from Brand b where b.category.id=:id")
    List<Brand> getBrandByCategoryId(int id);
}
