package com.framework.api.services;
import com.framework.api.requests.dto.TestRequest;
import com.framework.api.responses.dto.TestResponse;
import java.util.List;

public interface TestService{
	Long add(TestRequest requests);
	TestResponse getById(Long id);
	List<TestResponse> getAll();
	void delete(Long id);
	boolean exist(Long id);
	boolean update(TestResponse requests);
}