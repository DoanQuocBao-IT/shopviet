package com.project.shopviet.Service.ServiceImpl;

import com.project.shopviet.Model.PasswordResetToken;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.PasswordResetTokenRepository;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.EmailSenderService;
import com.project.shopviet.Service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public void sendPasswordResetMail(String email) {
        User user=userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Email not found "+email);
        }

        PasswordResetToken token= new PasswordResetToken(user);
        passwordResetTokenRepository.save(token);
        String subject = "Reset Your Password in ShopViet";
        String body = "To reset your password, please click the following link: "
                + "http://localhost:8080/api/auth/reset-password?token=" + token.getToken()+"";


        // Send email
        emailSenderService.sendEmailActive(email,subject,body);
    }
    @Transactional
    @Override
    public void resetPassword(String token, String password) {
        PasswordResetToken passwordResetToken= passwordResetTokenRepository.findByToken(token);
        if (passwordResetToken == null) {
            throw new IllegalArgumentException("Invalid token");
        }
        if (passwordResetToken.isTokenExpired()) {
            throw new IllegalArgumentException("Token has expired");
        }
        User user=passwordResetToken.getUser();
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        passwordResetTokenRepository.deleteByToken(token);
    }
}
