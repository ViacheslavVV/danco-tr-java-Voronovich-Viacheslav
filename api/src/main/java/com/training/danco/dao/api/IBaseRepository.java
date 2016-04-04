package com.training.danco.dao.api;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

import com.training.danco.model.BaseModel;

public interface IBaseRepository<TEntity extends BaseModel>  {

	public boolean set(Connection connection, TEntity entity) throws SQLException;
	public TEntity get(Connection connection, int id) throws SQLException;
	public boolean update(Connection connection, TEntity entity) throws SQLException;
	public boolean delete(Connection connection, TEntity entity) throws SQLException;
	public List<TEntity> getAll(Connection connection) throws SQLException;
	public int getCount(Connection connection) throws SQLException;
}
