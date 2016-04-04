package com.training.danco.dao.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.training.danco.model.Lection;

public interface ILectionRepository extends IBaseRepository<Lection>{

	public List<Lection> getSortedByDate(Connection connection) throws SQLException;
	public List<Lection> getSortedByName(Connection connectio) throws SQLException;
	public List<Lection> getLectionsByDate(Connection connectio, Date date) throws SQLException;
}
