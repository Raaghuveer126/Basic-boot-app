package com.framework.api.handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.framework.api.enums.CustomApiResponseCode;
import com.framework.api.exception.InvalidAccessException;
import com.framework.api.exception.InvalidExpiredTokenException;
import com.framework.api.responses.dto.BaseApiResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TokenExceptionHandler {

	@ExceptionHandler(value = InvalidExpiredTokenException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public <T> BaseApiResponse<T> handleInvalidExpiredTokenException(InvalidExpiredTokenException loe) {
		log.error("InvalidExpiredTokenException occured : ", loe);
		return new BaseApiResponse<>(true, CustomApiResponseCode.INVALID_EXPIRED_TOKEN);
	}

	@ExceptionHandler(value = InvalidAccessException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public <T> BaseApiResponse<T> handleInvalidAccessException(InvalidAccessException loe) {
		log.error("InvalidAccessException occured : ", loe);
		return new BaseApiResponse<>(true, CustomApiResponseCode.INVALID_ACCESS);
	}
	
	@ExceptionHandler(value = AccessDeniedException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public <T> BaseApiResponse<T> handleAccessDeniedException(AccessDeniedException loe) {
		log.error("AccessDeniedException occured : ", loe);
		return new BaseApiResponse<>(true, CustomApiResponseCode.INVALID_ACCESS);
	}

}
