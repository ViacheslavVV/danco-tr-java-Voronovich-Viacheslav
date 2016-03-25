package com.training.danco.dao.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.model.Student;

public interface IStudentRepository extends IBaseRepository<Student> {
	
	public List<Student> getStudentsByCourse(Connection connectio, Course course) throws SQLException;
}
