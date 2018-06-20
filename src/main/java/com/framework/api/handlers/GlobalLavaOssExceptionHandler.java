package com.framework.api.handlers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.api.enums.CustomApiResponseCode;
import com.framework.api.exception.CustomException;
import com.framework.api.responses.dto.BaseApiResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalLavaOssExceptionHandler {

	@ExceptionHandler(value = CustomException.class)
	@ResponseBody
	public <T> BaseApiResponse<T> handleLavaOssException(CustomException loe, HttpServletResponse response) {
		log.error("VantageException occured : ", loe);
		response.setStatus(loe.getHttpStatus().value());
		response.setHeader("Status", loe.getHttpStatus().value() + " " + loe.getHttpStatus().getReasonPhrase());
		return new BaseApiResponse<T>(true, loe.getCode(), null, loe.getMessage());
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
	public <T> BaseApiResponse<T> handleAllOtherException(MethodArgumentNotValidException e,
			HttpServletResponse response) {
		// String errorMsg =
		// e.getBindingResult().getFieldErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
		List<String> fieldErrors = e.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		log.error("Invalid arguement passed in api : ", e);
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setHeader("Status", HttpStatus.BAD_REQUEST.value() + " " + HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new BaseApiResponse<T>(false, null, CustomApiResponseCode.INVALID_ARGUEMENTS_PASSED, fieldErrors);
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public <T> BaseApiResponse<T> handleAllOtherException(Exception e, HttpServletResponse response) {
		log.error("Unhandled exception occured : ", e);
		String message = e.getMessage();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setHeader("Status",
				HttpStatus.INTERNAL_SERVER_ERROR.value() + " " + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		BaseApiResponse<T> baseApiResponse = new BaseApiResponse<T>(false, null,
				CustomApiResponseCode.INTERNAL_SERVER_ERROR, message);
		return baseApiResponse;
	}
}
