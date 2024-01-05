package com.project.shopviet.Controller;

import com.project.shopviet.DTO.AccessToken;
import com.project.shopviet.DTO.RefreshToken;
import com.project.shopviet.DTO.RegisterDto;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.DTO.request.LoginRequest;
import com.project.shopviet.JWT.JwtTokenProvider;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.EmailSenderService;
import com.project.shopviet.Service.PasswordResetService;
import com.project.shopviet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserService userService;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    PasswordResetService passwordResetService;

    @PostMapping("/login")
    public ResponseObject loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseObject registerUser(@RequestBody RegisterDto registerDto) {
        return userService.registerUserBuyer(registerDto);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshToken refreshToken) {
        final String accessToken = jwtTokenProvider.generateNewAccessToken(refreshToken.getRefresh_token());
        return ResponseEntity.ok(new AccessToken(accessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        String username = jwtTokenProvider.getUsernameFromToken(token);
        // Thêm token vào danh sách đen hoặc xóa nó khỏi cơ sở dữ liệu
        return ResponseEntity.ok().build();
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody String email) {
        passwordResetService.sendPasswordResetMail(email);
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestParam String token, @RequestBody String password) {
        passwordResetService.resetPassword(token, password);
    }
}
