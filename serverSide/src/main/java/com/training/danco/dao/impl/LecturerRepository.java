package com.training.danco.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import java.sql.SQLException;
import java.util.List;

import com.training.danco.dao.api.ILecturerRepository;
import com.training.danco.model.Lecturer;

public class LecturerRepository implements ILecturerRepository {

	private static final String NAME = "name";

	public LecturerRepository() {
	}

	@Override
	public Integer set(Session session, Lecturer lecturer) throws SQLException {
		return (Integer) session.save(lecturer);
	}

	@Override
	public Lecturer get(Session session, int id) throws SQLException {
		return (Lecturer) session.get(Lecturer.class, id);
	}

	@Override
	public void update(Session session, Lecturer lecturer) throws SQLException {
		session.update(lecturer);
	}

	@Override
	public void delete(Session session, Lecturer lecturer) throws SQLException {
		session.delete(lecturer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lecturer> getAll(Session session) throws SQLException {
		return session.createCriteria(Lecturer.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lecturer> getSortedByName(Session session) throws SQLException {
		Criteria criteria = session.createCriteria(Lecturer.class);
		return criteria.addOrder(Order.asc(NAME)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lecturer> getSortedByCoursesCount(Session session) throws SQLException {
		Criteria criteria = session.createCriteria(Lecturer.class);
		criteria.setFetchMode("courses", FetchMode.JOIN).createAlias("courses", "cour");
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("cour.lecturer"),"courGr");
		projectionList.add(Projections.rowCount(),"courCount");
		criteria.setProjection(projectionList);
		criteria.addOrder(Order.asc("courCount"));
		return criteria.list();
	}

	@Override
	public int getCount(Session session) throws SQLException {
		return Integer.parseInt(session.createCriteria(Lecturer.class).setProjection(Projections.rowCount()).uniqueResult().toString());
	}

}
