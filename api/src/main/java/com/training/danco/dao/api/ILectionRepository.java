package com.training.danco.dao.api;

import org.hibernate.Session;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.training.danco.model.Lection;
import com.training.danco.params.SortingParam;

public interface ILectionRepository extends IBaseRepository<Lection,Integer>{

	public List<Lection> getSorted(Session session, SortingParam sortingParam) throws SQLException;
	public List<Lection> getLectionsByDate(Session session, Date date) throws SQLException;
	public Integer getCount(Session session) throws SQLException;
	public List<Lection> getLectionsByCourse(Session session, Integer courseId) throws SQLException;
	public List<Lection> getFreeLections(Session session) throws SQLException;
}
