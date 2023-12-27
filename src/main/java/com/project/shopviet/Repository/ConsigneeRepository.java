package com.project.shopviet.Repository;

import com.project.shopviet.Model.Consignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsigneeRepository extends JpaRepository<Consignee,Integer> {
}
