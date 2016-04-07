package com.training.danco.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import java.sql.SQLException;
import java.util.List;

import com.training.danco.dao.api.IStudentRepository;
import com.training.danco.model.*;

public class StudentRepository implements IStudentRepository {

	public StudentRepository() {
	}

	@Override
	public Integer set(Session session, Student student) throws SQLException {
		return (Integer) session.save(student);
	}

	@Override
	public Student get(Session session, int id) throws SQLException {
		return (Student) session.get(Student.class, id);
	}

	@Override
	public void update(Session session, Student student) throws SQLException {
		session.update(student);
	}

	@Override
	public void delete(Session session, Student student) throws SQLException {
		session.delete(student);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAll(Session session) throws SQLException {

		return session.createCriteria(Student.class).list();
	}

	@Override
	public int getCount(Session session) throws SQLException {
		Criteria criteria = session.createCriteria(Student.class);
		return Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
	}

}
