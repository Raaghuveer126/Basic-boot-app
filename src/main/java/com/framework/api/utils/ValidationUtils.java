package com.framework.api.utils;

import org.springframework.util.StringUtils;

import com.framework.api.enums.CustomApiResponseCode;
import com.framework.api.exception.CustomException;
import com.framework.api.requests.dto.TestRequest;
import com.framework.api.responses.dto.TestResponse;

public class ValidationUtils {
	/* Tests Validations */
	public static void validateTestRequest(TestRequest requests) throws CustomException {
	if(requests.getTest()==null){
		throw new CustomException(CustomApiResponseCode.EMPTY_TEST);
	}
	}

	public static void validateTestResponse(TestResponse requests) throws CustomException {
	if(StringUtils.isEmpty(requests.getTestId())){
		throw new CustomException(CustomApiResponseCode.EMPTY_TEST_ID);
	}
	if(StringUtils.isEmpty(requests.getTest())){
		throw new CustomException(CustomApiResponseCode.EMPTY_TEST);
	}
	}
}/* finish */
