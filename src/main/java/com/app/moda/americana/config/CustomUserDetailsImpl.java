package com.app.moda.americana.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.moda.americana.domain.Role;
import com.app.moda.americana.domain.User;

public class CustomUserDetailsImpl implements UserDetails {
	
	private User user;
     
    public CustomUserDetailsImpl(User user) {
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	List<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
         
        return authorities;
    }
    
 
    @Override
    public String getPassword() {
        return user.getPassword();
    }
 
    @Override
    public String getUsername() {
    	return user.getFirstName() + " " + user.getLastName();
        // return user.getEmail();
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
		return user.isEnabled();
	}
     
    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }
 
    
    public User getUser() {
    	return this.user;
    }
    
    @Override
    public String toString() {
      return "CustomUserDetailsImpl{" +
          "user=" + user +
          '}';
    }
}