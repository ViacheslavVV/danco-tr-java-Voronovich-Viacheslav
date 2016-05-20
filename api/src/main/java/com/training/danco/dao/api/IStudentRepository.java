package com.training.danco.dao.api;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;

import com.training.danco.model.Student;

public interface IStudentRepository extends IBaseRepository<Student,Integer> {
	public Integer getCount(Session session) throws SQLException;
	
	public List<Student> getStudentsByCourse(Session session, Integer courseId) throws SQLException;

	public List<Student> getStudentsExceptCourse(Session session, Integer courseId) throws SQLException;
}
