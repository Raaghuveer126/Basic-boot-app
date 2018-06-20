package com.framework.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.api.constants.RestMappingConstants;
import com.framework.api.enums.CustomApiResponseCode;
import com.framework.api.exception.CustomException;
import com.framework.api.responses.dto.BaseApiResponse;
import com.framework.api.utils.CollectionUtils;
import com.framework.api.requests.dto.TestRequest;
import com.framework.api.responses.dto.TestResponse;
import java.util.List;
import com.framework.api.services.TestService;
import com.framework.api.utils.TestConverter;

import com.framework.api.utils.ValidationUtils;

@Controller
@RequestMapping(value = RestMappingConstants.TestConstants.BASE)
public class TestController{

	@Autowired
	private TestService testService;
	@RequestMapping(value = RestMappingConstants.REQUEST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseApiResponse<TestRequest> generateRequestJson() throws CustomException{
	return new BaseApiResponse<TestRequest>(false, CustomApiResponseCode.SUCCESS.getCode(), TestConverter.getSample(),CustomApiResponseCode.SUCCESS.getMessage());
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseApiResponse<Long> add(@RequestBody(required = true) TestRequest requests)throws CustomException{
	ValidationUtils.validateTestRequest(requests);
	Long responses = testService.add(requests);
	return new BaseApiResponse<Long>(false, CustomApiResponseCode.SUCCESS.getCode(), responses, CustomApiResponseCode.SUCCESS.getMessage());
	}

	@RequestMapping(path = RestMappingConstants.ID_PARAM, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseApiResponse<TestResponse> getById(@PathVariable(RestMappingConstants.ID) Long id)throws CustomException{
	TestResponse responses = testService.getById(id);
	if(responses==null){
		throw new CustomException(CustomApiResponseCode.TEST_NOT_FOUND);
	}
	return new BaseApiResponse<TestResponse>(false, CustomApiResponseCode.SUCCESS.getCode(), responses, CustomApiResponseCode.SUCCESS.getMessage());
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseApiResponse<List<TestResponse>> getAll()throws CustomException{
	List<TestResponse> responses = testService.getAll();
	if(CollectionUtils.isEmpty(responses)){
		throw new CustomException(CustomApiResponseCode.NO_TESTS_FOUND);
	}
	return new BaseApiResponse<List<TestResponse>>(false, CustomApiResponseCode.SUCCESS.getCode(), responses, CustomApiResponseCode.SUCCESS.getMessage());
	}

	@RequestMapping(path = RestMappingConstants.ID_PARAM, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseApiResponse<Boolean> delete(@PathVariable(RestMappingConstants.ID) Long id)throws CustomException{
	if(!testService.exist(id)){
		throw new CustomException(CustomApiResponseCode.TEST_NOT_FOUND);
	}
	testService.delete(id);
	return new BaseApiResponse<Boolean>(false, CustomApiResponseCode.SUCCESS.getCode(), true, CustomApiResponseCode.SUCCESS.getMessage());
	}

	@RequestMapping(path = RestMappingConstants.ID_PARAM, method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BaseApiResponse<Boolean> update(@PathVariable(RestMappingConstants.ID) Long id,@RequestBody(required = true) TestResponse requests)throws CustomException{
	if(!testService.exist(id)){
		throw new CustomException(CustomApiResponseCode.TEST_NOT_FOUND);
	}
	requests.setTestId(id);
	Boolean responses = testService.update(requests);
	return new BaseApiResponse<Boolean>(false, CustomApiResponseCode.SUCCESS.getCode(), responses, CustomApiResponseCode.SUCCESS.getMessage());
	}

}