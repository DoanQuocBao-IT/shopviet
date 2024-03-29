package com.project.shopviet.Repository;

import com.project.shopviet.Model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Integer> {
    List<ProductType> findByProduct_Id(int id);
    //price is lowest

    ProductType findTopByProduct_IdOrderByPriceAscQuantityDesc(int product_id);
}
