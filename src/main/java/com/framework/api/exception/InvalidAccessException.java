package com.framework.api.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidAccessException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidAccessException() {
		super(null);
	}

	public InvalidAccessException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
