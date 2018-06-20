package com.framework.api.security.filters;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@Getter
public class LoggedInUserDetails {
	
	private final String username;
    private final List<GrantedAuthority> authorities;
    private final String role;
    private final Integer id;
    private final String name;
	
    private LoggedInUserDetails(String username, List<GrantedAuthority> authorities, String role, Integer id, String name) {
		this.username = username;
		this.authorities = authorities;
		this.role = role;
		this.id = id;
		this.name = name;
	}
    
    public static LoggedInUserDetails create(String username, List<GrantedAuthority> authorities, String role, Integer id, String name) {
    		return new LoggedInUserDetails(username, authorities, role, id, name);
    }
    
}
