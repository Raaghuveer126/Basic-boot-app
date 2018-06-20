package com.framework.api.exception;

import org.springframework.http.HttpStatus;

import com.framework.api.enums.CustomApiResponseCode;

import lombok.Getter;

@Getter
public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private HttpStatus httpStatus;
	
	public CustomException() {
		super();
		this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	
	public CustomException(String message, String code, HttpStatus httpStatus) {
		super(message);
		this.code = code;
		this.httpStatus = httpStatus;
	}
	
	public CustomException(String message, String code) {
		this(code, message, HttpStatus.BAD_REQUEST);
	}
	
	
	public CustomException(CustomApiResponseCode lavaOssResponseCode, HttpStatus httpStatus) {
		this(lavaOssResponseCode.getMessage(), lavaOssResponseCode.getCode(), httpStatus);
	}
	
	public CustomException(CustomApiResponseCode lavaOssResponseCode) {
		this(lavaOssResponseCode, HttpStatus.BAD_REQUEST);
	}
}
