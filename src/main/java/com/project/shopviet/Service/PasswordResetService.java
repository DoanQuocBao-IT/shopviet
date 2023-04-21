package com.project.shopviet.Service;

public interface PasswordResetService {
    void sendPasswordResetMail(String email);
    void resetPassword(String token,String password);

}
