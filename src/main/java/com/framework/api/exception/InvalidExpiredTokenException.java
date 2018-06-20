package com.framework.api.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidExpiredTokenException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidExpiredTokenException() {
		super(null);
	}

	public InvalidExpiredTokenException(String msg) {
		super(msg);
	}

}
