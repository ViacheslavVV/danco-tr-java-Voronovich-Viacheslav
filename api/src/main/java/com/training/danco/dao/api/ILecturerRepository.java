package com.training.danco.dao.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.training.danco.model.Lecturer;

public interface ILecturerRepository extends IBaseRepository<Lecturer> {

	public List<Lecturer> getSortedByName(Connection connectio) throws SQLException;

	public List<Lecturer> getSortedByCoursesCount(Connection connection) throws SQLException;
}
