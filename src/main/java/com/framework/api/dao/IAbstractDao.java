package com.framework.api.dao;


import java.util.List;

import org.hibernate.Query;

public interface IAbstractDao<T> {
	public T getTById(Long id);
	public void saveT(T itemToSave);
	public void updateT(T itemToUpdate);
	public void deleteT(Long id);
	public T getT(String query);
	public List<T> getAllT();
	public List<T> getAllT(String query);
	public List<T> getAllT(Query query);
	public List<T> getAllT(Integer offset, Integer limit);
	public int executeUpdate(String hql);
	public Query getQueryObject(String hql);
	public void deleteT(String hql);
	public Long getTotalCount(String feild,Object value);
	public Long getTotalCount();

}
