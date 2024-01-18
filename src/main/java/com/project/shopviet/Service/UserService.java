package com.project.shopviet.Service;

import com.project.shopviet.DTO.RegisterDto;
import com.project.shopviet.DTO.UserDto;
import com.project.shopviet.DTO.request.LoginRequest;
import com.project.shopviet.DTO.request.RegisterSellerRequest;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.DTO.response.UserSellerResponse;
import com.project.shopviet.Model.Role;
import com.project.shopviet.Model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    void addRoleToUser(int userId, int roleId);
    ResponseObject login(LoginRequest loginRequest);

    ResponseObject registerUserBuyer(RegisterDto registerDto);

    ResponseObject registerUserSeller(RegisterSellerRequest registerDto);

    ResponseObject getProfileSeller(int id);

    ResponseObject getProfileForSeller();
    ResponseObject updateProfileSeller(RegisterSellerRequest registerDto);

    String addRoleUser(String username, String name);

    Optional<User> getUserByUsername(String username);

    List<User> findUserByRole(Role role);

    List<User> findByRolesIn(Set<Role> roles);

    UserSellerResponse getUserById(int id);

    List<User> getAllUser();

    boolean isExistById(int id);

    void approveSellerOrShipper(int id);

    void blockSellerOrShipper(int id);

    void unBlockSellerOrShipper(int id);

    List<User> getAllUserApprove();

    List<User> getAllUserBlock();

    UserDto getProfile();
}
