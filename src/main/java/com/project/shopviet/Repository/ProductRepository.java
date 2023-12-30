package com.project.shopviet.Repository;

import com.project.shopviet.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByIdAndSellerId(int id,int seller_id);
    Page<Product> findAllByCategoryId(int category_id, Pageable pageable);
    Page<Product> findAllByBrandId(int brand_id, Pageable pageable);
    Page<Product> findAllBySellerId(int seller_id, Pageable pageable);
    Page<Product> findAllByBrandIdAndSellerId(int brand_id,int seller_id, Pageable pageable);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findTop3ByBrandIdOrderBySoldDesc(int brand_id);
}
