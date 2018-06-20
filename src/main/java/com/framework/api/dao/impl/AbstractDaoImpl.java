package com.framework.api.dao.impl;


import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.framework.api.dao.IAbstractDao;

@Transactional
public abstract class AbstractDaoImpl<T> implements IAbstractDao<T> {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	protected String entityName;
	protected String FROM_ENTITY;
	protected String UPDATE_ENTITY;
	protected Class<T> entityClass;

	public AbstractDaoImpl(Class<T> entityClass) {
		this.entityName = entityClass.getName();
		this.entityClass = entityClass;
		this.FROM_ENTITY = "from " + entityName;
		this.UPDATE_ENTITY = "update " + entityName;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T getTById(Long id) {
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public void saveT(T itemToSave) {
		getSession().save(itemToSave);
	}

	@Override
	public void updateT(T itemToUpdate) {
		getSession().update(itemToUpdate);
	}

	@Override
	public void deleteT(Long id) {
		T itemToDelete = getTById(id);
		getSession().delete(itemToDelete);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getT(String query) {
		return (T) getSession().createQuery(query).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAllT() {
		return getSession().createQuery(FROM_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllT(String queryEntity) {
		return getSession().createQuery(queryEntity).list();
	}

	@Override
	public int executeUpdate(String hql) {
		Query query = getSession().createQuery(hql);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllT(Query query) {
		return query.list();
	}

	@Override
	public Query getQueryObject(String hql) {
		return getSession().createQuery(hql);
	}

	@Override
	public void deleteT(String hql) {
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public Long getTotalCount(String feild, Object value) {
		return (Long) getSession().createCriteria(entityClass).setProjection(Projections.rowCount())
				.add(Restrictions.eq(feild, value)).uniqueResult();
	}

	@Override
	public Long getTotalCount() {
		return (Long) getSession().createCriteria(entityClass).setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public List<T> getAllT(Integer offset, Integer limit) {
		Query createQuery = getQueryObject(FROM_ENTITY);
		if (offset != null) {
			createQuery.setFirstResult(offset);
		}
		if (limit != null) {
			createQuery.setMaxResults(limit);
		}
		return getAllT(createQuery);
	}

}
