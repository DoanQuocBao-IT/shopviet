package com.project.shopviet.Repository;

import com.project.shopviet.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    @Query(value = "select admin from Admin admin where admin.user.username=:admin_userName")
    Admin findAdminByUserName(String admin_userName);
}
