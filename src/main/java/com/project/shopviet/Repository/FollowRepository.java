package com.project.shopviet.Repository;

import com.project.shopviet.Model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Integer> {
    @Query(value = "select f from Follow f where f.followedUser.id=:id")
    Follow findFollowByUserId(int id);
    @Query("SELECT COUNT(f) FROM Follow f WHERE f.followedUser.id = :user_id")
    int countFollowersByUserId(@Param("user_id") int user_id);
}
