package com.project.shopviet.JWT;

import com.project.shopviet.Model.User;
import com.project.shopviet.Repository.UserRepository;
import com.project.shopviet.Service.UserService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

@Slf4j
@Service
public class JwtTokenProvider {
    public String secret = "Myshopviet";
    @Autowired
    private UserRepository userRepository;


    public String getUsernameFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }
    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims=getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    public String generateAccessToken(UserDetails userDetails){
        Map<String,Object>claims=new HashMap<>();
        JwtUserDetails jwtUserDetails=(JwtUserDetails) userDetails;
        String role= jwtUserDetails.getRoles();
        return doGenerateAccessToken(claims,userDetails.getUsername(),role);
    }
    public String generateRefreshToken(UserDetails userDetails){
        Map<String,Object>claims=new HashMap<>();
        JwtUserDetails jwtUserDetails=(JwtUserDetails) userDetails;
        String role= jwtUserDetails.getRoles();
        return doGenerateRefreshToken(claims,userDetails.getUsername(),role);
    }
    public String generateNewAccessToken(String token){
        String username= getUsernameFromToken(token);
        Optional<User> user=userRepository.getUserByUsername(username);
        Map<String,Object>claims=new HashMap<>();
        UserDetails userDetails= new JwtUserDetails(user.get());
        JwtUserDetails jwtUserDetails=(JwtUserDetails) userDetails;
        String role= jwtUserDetails.getRoles();
        return doGenerateAccessToken(claims,userDetails.getUsername(),role);
    }
    public String doGenerateAccessToken(Map<String, Object> claims,String username,String role) {
        claims.put("role", role);
        return Jwts.builder().setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60000*15)).signWith(SignatureAlgorithm.HS256, secret).compact();
    }
    public String doGenerateRefreshToken(Map<String, Object> claims,String username,String role) {
        return Jwts.builder().setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60000*60*24*15)).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token,UserDetails userDetails){
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }
    private Boolean isTokenExpired(String token){
        final Date expiration=getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }
    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public List<String> getRolesFromJwtToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        List<String> roles = (List<String>) claims.get("role");
        return roles;
    }
}
