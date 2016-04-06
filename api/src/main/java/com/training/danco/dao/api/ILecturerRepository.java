package com.training.danco.dao.api;

import org.hibernate.Session;
import java.sql.SQLException;
import java.util.List;

import com.training.danco.model.Lecturer;

public interface ILecturerRepository extends IBaseRepository<Lecturer> {

	public List<Lecturer> getSortedByName(Session session) throws SQLException;

	public List<Lecturer> getSortedByCoursesCount(Session session) throws SQLException;
}
