package com.project.shopviet.Repository;

import com.project.shopviet.Model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Integer> {
    int countFollowByFollowedUserId(int id);
    int countFollowByUserId(int id);
}
