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
import com.training.danco.params.SortingParam;

public class LecturerRepository extends AbstractRepository<Lecturer, Integer> implements ILecturerRepository {

	private static final String ID = "id";
	private static final String NAME = "name";

	public LecturerRepository() {
	}

	@SuppressWarnings("unchecked")
	public List<Lecturer> getSorted(Session session, SortingParam sortingParam) throws SQLException {
		Criteria criteria = session.createCriteria(Lecturer.class);
		switch (sortingParam) {
			case NAME: {
				criteria.addOrder(Order.asc(NAME));
				break;
			}
			
			case COURSE_COUNT: {
				criteria.setFetchMode("courses", FetchMode.JOIN).createAlias("courses", "cour");
				ProjectionList projectionList = Projections.projectionList();
				projectionList.add(Projections.groupProperty("cour.lecturer"), "courGr");
				projectionList.add(Projections.rowCount(), "courCount");
				criteria.setProjection(projectionList);
				criteria.addOrder(Order.asc("courCount"));
				break;
			}
	
			default: {
				criteria.addOrder(Order.asc(ID));
			}
		}
		return criteria.list();
	}

	@Override
	public Integer getCount(Session session) throws SQLException {
		return Integer.parseInt(
				session.createCriteria(Lecturer.class).setProjection(Projections.rowCount()).uniqueResult().toString());
	}

}
