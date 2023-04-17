package com.project.shopviet.Repository;

import com.project.shopviet.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value = "select p.image from Product p")
    List<String> getAllImageProduct();
    @Query(value = "select prod from Product prod where prod.id=:id")
    Optional<Product> getProductById(int id);
    @Query(value = "select prod from Product prod where prod.brand.id=:id")
    List<Product> getProductByBrandId(int id);
    @Query(value = "select prod from Product prod where prod.brand.category.id=:id")
    List<Product> getProductByCategoryId(int id);
    @Query(value = "select prod from Product prod where prod.userSeller.id=:seller_id")
    List<Product> getProductBySellerId(int seller_id);

}
