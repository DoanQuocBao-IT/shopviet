package com.project.shopviet.Repository;

import com.project.shopviet.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Optional<Seller> findByUserId(int userId);
}
