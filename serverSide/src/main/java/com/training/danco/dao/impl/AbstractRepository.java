package com.training.danco.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.training.danco.dao.api.IBaseRepository;
import com.training.danco.model.BaseModel;

public abstract class AbstractRepository<TEntity extends BaseModel, PK extends Serializable>
		implements IBaseRepository<TEntity, PK> {
	
	@SuppressWarnings("unchecked")
	protected Class<TEntity> getGenericEntityClass() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<TEntity>) parameterizedType.getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PK set(Session session, TEntity entity) throws SQLException {
		Transaction transaction = session.beginTransaction();
		PK result = (PK) session.save(entity); 
		transaction.commit();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TEntity get(Session session, Integer id) throws SQLException {
		return (TEntity) session.get(getGenericEntityClass(), id);
	}

	@Override
	public void update(Session session, TEntity entity) throws SQLException {
		Transaction transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
	}

	@Override
	public void delete(Session session, TEntity entity) throws SQLException {
		Transaction transaction = session.beginTransaction();
		session.delete(entity);
		transaction.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TEntity> getAll(Session session) throws SQLException {
		Criteria criteria = session.createCriteria(getGenericEntityClass());
		return criteria.list();
	}
	
}
