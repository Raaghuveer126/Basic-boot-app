package com.framework.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.api.entity.Test;
import com.framework.api.requests.dto.TestRequest;
import com.framework.api.responses.dto.TestResponse;
import java.util.List;
import com.framework.api.services.TestService;
import com.framework.api.dao.TestDao;
import com.framework.api.utils.TestConverter;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private TestDao testDao;

	@Override
	public Long add(TestRequest requests){
	Test entity=TestConverter.getEntityFromRequest(requests);
	if(entity!=null){
		testDao.saveT(entity);
		return entity.getId();
	}
	return 0l;
	}

	@Override
	public TestResponse getById(Long id){
	return TestConverter.getResponseFromEntity(testDao.getTById(id));
	}

	@Override
	public List<TestResponse> getAll(){
	return TestConverter.getResponseListFromEntityList(testDao.getAllT());
	}

	@Override
	public void delete(Long id){
	testDao.deleteT(id);
	}

	@Override
	public boolean exist(Long id){
	if(getById(id)!=null){
		return true;
	}
	return false;
	}

	@Override
	public boolean update(TestResponse requests){
	Test entity=testDao.getTById(requests.getTestId());
	TestConverter.getEntityFromResponse(requests,entity);
	if(entity!=null){
		testDao.updateT(entity);
		return true;
	}
	return false;
	}

}