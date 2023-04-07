package com.project.shopviet.Repository;

import com.project.shopviet.Model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    @Query(value = "select b from Brand b where b.category.id=:id")
    Optional<Brand> getBrandByCategoryId(int id);
    @Modifying
    @Query("UPDATE Brand b SET b.category.id = :category_id WHERE b.id = :brand_id")
    void updateBrandForCategory(@Param("brand_id") int brandId, @Param("category_id") int categoryId);
}
