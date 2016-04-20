package training.danco.dao.api;

import java.util.List;

import org.hibernate.Session;

import training.danco.model.BaseModel;

import java.io.Serializable;
import java.sql.SQLException;


public interface IBaseRepository<TEntity extends BaseModel, PK extends Serializable>  {

	public PK set(Session session, TEntity entity) throws SQLException;
	public TEntity get(Session session, Integer id) throws SQLException;
	public void update(Session session, TEntity entity) throws SQLException;
	public void delete(Session session, TEntity entity) throws SQLException;
	public List<TEntity> getAll(Session session) throws SQLException;
}
