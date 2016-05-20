package com.training.danco.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.List;

import com.training.danco.dao.api.IStudentRepository;
import com.training.danco.model.*;

public class StudentRepository extends AbstractRepository<Student, Integer> implements IStudentRepository {

	private static final String ID = "id";
	
	public StudentRepository() {
	}

	@Override
	public Integer getCount(Session session) throws SQLException {
		Criteria criteria = session.createCriteria(Student.class);
		return Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentsByCourse(Session session, Integer courseId) throws SQLException {
		Criteria criteria = session.createCriteria(Student.class);
		return criteria.setFetchMode("course", FetchMode.JOIN).createAlias("course", "cour")
				.add(Restrictions.eq("cour.id", courseId)).addOrder(Order.asc(ID)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentsExceptCourse(Session session, Integer courseId) throws SQLException {
		Criteria criteria = session.createCriteria(Student.class);
		return criteria.add(Restrictions.or(Restrictions.ne("course.id",courseId),Restrictions.isNull("course"))).addOrder(Order.asc(ID)).list();
	}

}
