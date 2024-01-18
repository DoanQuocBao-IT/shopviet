package com.project.shopviet.Repository;

import com.project.shopviet.Model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    Optional<Brand> findByIdAndUserSellerId(int id,int userSeller_id);
    List<Brand> findAllByUserSellerId(int userSeller_id);
}
