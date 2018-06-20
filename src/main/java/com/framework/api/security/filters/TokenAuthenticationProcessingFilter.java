package com.framework.api.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import com.framework.api.exception.InvalidExpiredTokenException;


public class TokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
	
	private AuthenticationFailureHandler failureHandler;

	public TokenAuthenticationProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationFailureHandler failureHandler) {
		super(requiresAuthenticationRequestMatcher);
		this.failureHandler = failureHandler;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if(!StringUtils.isEmpty(token)) {
			token = token.trim();
			if(token.startsWith("Bearer")) {
				token = token.substring(6).trim();
			}
		} else {
			throw new InvalidExpiredTokenException();
		}
		return getAuthenticationManager().authenticate(new CustomAuthenticationToken(token));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(authResult);
		SecurityContextHolder.setContext(securityContext);
		chain.doFilter(request, response);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		this.failureHandler.onAuthenticationFailure(request, response, failed);
	}

}
