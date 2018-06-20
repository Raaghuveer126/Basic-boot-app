package com.framework.api.utils;

import com.framework.api.entity.Test;
import java.util.ArrayList;
import java.util.List;
import com.framework.api.requests.dto.TestRequest;
import com.framework.api.responses.dto.TestResponse;

public class TestConverter {

	public static TestRequest getSample() {
		TestRequest responses = new TestRequest();
		responses.setTest(123l);
		return responses;
	}

	public static Test getEntityFromRequest(TestRequest requests) {
		if(requests!=null){
			Test responses = new Test();
			responses.setTest(requests.getTest());
			return responses;
		}
		return null;
	}

	public static TestResponse getResponseFromEntity(Test requests) {
		if(requests!=null){
			TestResponse responses = new TestResponse();
			responses.setTestId(requests.getId());
			responses.setTest(requests.getTest());
			return responses;
		}
		return null;
	}

	public static Test getEntityFromResponse(TestResponse requests,Test responses) {
		if(requests!=null){
			responses.setTest(requests.getTest());
			return responses;
		}
		return null;
	}

	public static List<TestResponse> getResponseListFromEntityList(List<Test> requestsList) {
		if(CollectionUtils.isNotEmpty(requestsList)){
		List<TestResponse> responsesList = new ArrayList<>();
		for(Test requests:requestsList){
			responsesList.add(getResponseFromEntity(requests));
		}
		return responsesList;
		}
		return null;
	}
}