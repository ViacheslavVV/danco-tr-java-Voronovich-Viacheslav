package com.training.danco.dao.api;

import org.hibernate.Session;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.training.danco.model.Lection;

public interface ILectionRepository extends IBaseRepository<Lection>{

	public List<Lection> getSortedByDate(Session session) throws SQLException;
	public List<Lection> getSortedByName(Session session) throws SQLException;
	public List<Lection> getLectionsByDate(Session session, Date date) throws SQLException;
}
