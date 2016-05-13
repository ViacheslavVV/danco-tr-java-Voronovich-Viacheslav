package com.training.danco.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import java.sql.SQLException;

import com.training.danco.dao.api.IStudentRepository;
import com.training.danco.model.*;

public class StudentRepository extends AbstractRepository<Student, Integer> implements IStudentRepository {

	public StudentRepository() {
	}

	@Override
	public Integer getCount(Session session) throws SQLException {
		Criteria criteria = session.createCriteria(Student.class);
		return Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
	}

}
