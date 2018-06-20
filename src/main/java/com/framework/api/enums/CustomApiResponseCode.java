package com.framework.api.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomApiResponseCode {

	SUCCESS("Success", "0"), 
	INTERNAL_SERVER_ERROR("Internal server error","501"), 
	INVALID_ARGUEMENTS_PASSED("Please check your request!", "INV_ARGS"), 
	COMMON("", ""),
	
	// Token
	INVALID_EXPIRED_TOKEN("Token is either invalid or expired!", "401"), 
	INVALID_ACCESS("Invalid access!","403"), 

	/* Tests*/
	EMPTY_TEST("test cannot be empty!","EMP_TEST"),
	EMPTY_TEST_ID("testId cannot be empty!","EMP_TEST_ID"),
	TEST_NOT_FOUND("Test not found!","NF_TEST"),
	NO_TESTS_FOUND("No Tests added yet!","NO_TEST_ADDED"),
	;

	private String message;
	private String code;

	public static CustomApiResponseCode formatCode(CustomApiResponseCode lavaOssResponseCode, String value) {
		CustomApiResponseCode commonCode = CustomApiResponseCode.COMMON;
		commonCode.code = INTERNAL_SERVER_ERROR.code;
		commonCode.message = INTERNAL_SERVER_ERROR.message;
		if (lavaOssResponseCode != null) {
			commonCode.code = lavaOssResponseCode.code;
			commonCode.message = String.format(lavaOssResponseCode.message, value);
		}
		return commonCode;
	}

	public String replaceFeilds(Object... args) {
		return String.format(getMessage(), args);
	}

}
