package com.framework.api.dao.impl;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import com.framework.api.entity.Test;
import com.framework.api.dao.TestDao;
@Repository
@Transactional
public class TestDaoImpl extends AbstractDaoImpl<Test> implements TestDao{
	public TestDaoImpl() {
		super(Test.class);
	}

}