package com.project.shopviet.Service;

public interface EmailSenderService {
    void sendEmailActive(String to,String subject,String message);
    void sendEmailResetPasswordToken(String to,String subject,String message);
}
