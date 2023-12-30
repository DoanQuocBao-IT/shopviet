package com.project.shopviet.Controller;

import com.project.shopviet.DTO.AccessToken;
import com.project.shopviet.DTO.ErrorResponse;
import com.project.shopviet.DTO.RefreshToken;
import com.project.shopviet.DTO.RegisterDto;
import com.project.shopviet.DTO.response.ResponseObject;
import com.project.shopviet.JWT.JwtRequest;
import com.project.shopviet.JWT.JwtResponse;
import com.project.shopviet.JWT.JwtTokenProvider;
import com.project.shopviet.JWT.UserDetailsServiceImpl;
import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.EmailSenderService;
import com.project.shopviet.Service.PasswordResetService;
import com.project.shopviet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserService userService;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    PasswordResetService passwordResetService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
            final UserDetails userDetails= userDetailsService.loadUserByUsername(jwtRequest.getUsername());
            final String accessToken = jwtTokenProvider.generateAccessToken(userDetails);
            final String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails);

            Optional<User> user=userService.getUserByUsername(jwtRequest.getUsername());
            return ResponseEntity.ok(new JwtResponse(user.get().getId(),accessToken,refreshToken, user.get().getFname(),user.get().getImage(),user.get().getRoles()));
        } catch (BadCredentialsException e) {
            ErrorResponse errorResponse=new ErrorResponse("UNAUTHORIZED","Incorrect username or password");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseObject registerUser(@RequestBody RegisterDto registerDto) throws Exception{
        return userService.registerUserBuyer(registerDto);
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshToken refreshToken){
        final String accessToken = jwtTokenProvider.generateNewAccessToken(refreshToken.getRefreshToken());
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
    public void resetPassword(@RequestParam String token,@RequestBody String password){
        passwordResetService.resetPassword(token, password);
    }
}
