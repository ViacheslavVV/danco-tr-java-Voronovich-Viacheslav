package com.training.danco.dao.api;

import java.util.List;

import org.hibernate.Session;
import java.sql.SQLException;

import com.training.danco.model.BaseModel;

public interface IBaseRepository<TEntity extends BaseModel>  {

	public Integer set(Session session, TEntity entity) throws SQLException;
	public TEntity get(Session session, int id) throws SQLException;
	public void update(Session session, TEntity entity) throws SQLException;
	public void delete(Session session, TEntity entity) throws SQLException;
	public List<TEntity> getAll(Session session) throws SQLException;
	public int getCount(Session session) throws SQLException;
}
