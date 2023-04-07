package com.project.shopviet.Service;

import com.project.shopviet.DTO.RegisterDto;
import com.project.shopviet.Model.Role;
import com.project.shopviet.Model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    void addRoleToUser(int userId, int roleId);
    boolean addRegisterUser(RegisterDto registerDto);
    String addRoleUser(String username,String name);
    Optional<User> findUserByUsername(String username);
    List<User> findUserByRole(Role role);
    List<User> findByRolesIn(Set<Role> roles);
    String deleteUser(int id);
    User updateUser(int id,User user);
    List<User> getAllUser();
    List<User> getUserForRole(int role_id);
    Set<Role> getRoleForUser(int user_id);
    boolean isExistById(int id);
}
