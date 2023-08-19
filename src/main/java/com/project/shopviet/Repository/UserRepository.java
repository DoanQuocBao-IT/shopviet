package com.project.shopviet.Repository;

import com.project.shopviet.Model.Role;
import com.project.shopviet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT u.roles FROM User u WHERE u.id=:id")
    Set<Role> getRoleFromUserId(int id);
    Optional<User> getUserByUsername(String userName);
    @Query(value = "SELECT u FROM User u WHERE u.username=:userName")
    User findUserByName(String userName);
    User  findByEmail(String email);
    @Query(value = "SELECT u FROM User u WHERE u.roles=:role")
    Optional<User> findUserByRole(Role role);
    List<User> findByRolesIn(Set<Role> roles);
    List<User> findByRolesContaining(Role role);
    List<User> findByApprovedFalse();
    List<User> findByLockedTrue();



}
