package com.framework.api.security.filters;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoggedInUserDetails userDetails;
	private String token;
	
	public CustomAuthenticationToken(String token) {
		super(null);
		this.token = token;
		this.setAuthenticated(false);
	}
	
	public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities, LoggedInUserDetails userDetails) {
		super(authorities);
		super.eraseCredentials();
		this.userDetails = userDetails;
		super.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getPrincipal() {
		return userDetails;
	}

}
