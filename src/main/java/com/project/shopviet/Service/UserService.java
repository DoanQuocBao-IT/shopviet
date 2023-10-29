package com.project.shopviet.Service;

import com.project.shopviet.DTO.RegisterDto;
import com.project.shopviet.DTO.UserDto;
import com.project.shopviet.DTO.UserSellerDto;
import com.project.shopviet.Model.Role;
import com.project.shopviet.Model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    void addRoleToUser(int userId, int roleId);
    boolean addRegisterUser(RegisterDto registerDto);
    String addRoleUser(String username,String name);
    Optional<User> getUserByUsername(String username);
    List<User> findUserByRole(Role role);
    List<User> findByRolesIn(Set<Role> roles);
    String deleteUser(int id);
    User updateUser(int id,User user);
    UserSellerDto getUserById(int id);
    List<User> getAllUser();
    List<User> getUserForRole(int role_id);
    Set<Role> getRoleForUser(int user_id);
    boolean isExistById(int id);
    void approveSellerOrShipper(int id);
    void blockSellerOrShipper(int id);
    void unBlockSellerOrShipper(int id);
    List<User> getAllUserApprove();
    List<User> getAllUserBlock();
    UserDto getProfile();
}
