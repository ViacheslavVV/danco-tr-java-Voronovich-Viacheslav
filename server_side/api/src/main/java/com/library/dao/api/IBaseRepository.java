package com.library.dao.api; 

import java.util.List;

import com.library.model.BaseModel;

import java.io.Serializable;
import java.sql.SQLException;

public interface IBaseRepository<TEntity extends BaseModel, PK extends Serializable>  {

	public PK set(TEntity entity) throws SQLException;
	public TEntity get(Integer id) throws SQLException;
	public void update(TEntity entity) throws SQLException;
	public void delete(TEntity entity) throws SQLException;
	public List<TEntity> getAll() throws SQLException;
}
