package com.training.danco.dao.api;

import org.hibernate.Session;
import java.sql.SQLException;
import java.util.List;

import com.training.danco.model.Lecturer;
import com.training.danco.params.SortingParam;

public interface ILecturerRepository extends IBaseRepository<Lecturer,Integer> {

	public List<Lecturer> getSorted(Session session, SortingParam sortingParam) throws SQLException;
	public Integer getCount(Session session) throws SQLException;
}
