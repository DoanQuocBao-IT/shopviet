package com.project.shopviet.Repository;

import com.project.shopviet.Model.Product;
import com.project.shopviet.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    @Query(value = "select s from Seller s where s.user.username=:username")
    Optional<Seller> findSellerByUsername(String username);

    @Query(value = "select s.products from Seller s where s.user.username=:username")
    List<Product> findAllProductBySellerUsername(String username);
}
