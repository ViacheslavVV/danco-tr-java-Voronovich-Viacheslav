package com.training.danco.dao.api;

import java.sql.SQLException;

import org.hibernate.Session;

import com.training.danco.model.Student;

public interface IStudentRepository extends IBaseRepository<Student,Integer> {
	public Integer getCount(Session session) throws SQLException;
}
