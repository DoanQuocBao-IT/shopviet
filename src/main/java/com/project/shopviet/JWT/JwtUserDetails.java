package com.project.shopviet.JWT;

import com.project.shopviet.Model.Role;
import com.project.shopviet.Model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
public class JwtUserDetails implements UserDetails {
    private String username;
    private String password;
    private Set<Role> roles;

    public JwtUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }
    public String getRoles(){
        System.out.println("current user role: ");
        System.out.println(roles.stream().map(role -> role.getName()).collect(Collectors.toList()));
        return roles.stream().map(role -> role.getName()).collect(Collectors.toList()).get(0);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
