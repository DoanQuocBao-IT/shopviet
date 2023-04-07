package com.project.shopviet.JWT;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
public class JwtTokenProvider {
    public String secret = "Myshopviet";

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
    public String generateToken(UserDetails userDetails){
        Map<String,Object>claims=new HashMap<>();
        JwtUserDetails jwtUserDetails=(JwtUserDetails) userDetails;
        String role= jwtUserDetails.getRoles();
        return doGenerateToken(claims,userDetails.getUsername(),role);
    }
    public String doGenerateToken(Map<String, Object> claims,String sub,String role) {
        claims.put("role", role);
        return Jwts.builder().setClaims(claims).setSubject(sub)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 360000 * 1000)).signWith(SignatureAlgorithm.HS256, secret).compact();
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
