package com.training.danco.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.training.danco.dao.api.ILectionRepository;
import com.training.danco.model.Lection;
import com.training.danco.params.SortingParam;

public class LectionRepository extends AbstractRepository<Lection, Integer> implements ILectionRepository {

	private static final String NAME = "name";
	private static final String ID = "id";
	private static final String DATE = "date";

	public LectionRepository() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lection> getSorted(Session session, SortingParam sortingParam) throws SQLException {
		Criteria criteria = session.createCriteria(Lection.class);
		switch (sortingParam) {
		case DATE: {
			criteria.addOrder(Order.asc(DATE));
			break;
		}
		case NAME: {
			criteria.addOrder(Order.asc(NAME));
			break;
		}
		default: {
			criteria.addOrder(Order.asc(ID));
		}
		}
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lection> getLectionsByDate(Session session, Date date) throws SQLException {
		Criteria criteria = session.createCriteria(Lection.class);
		return criteria.add(Restrictions.eq(DATE, date)).addOrder(Order.asc(ID)).list();
	}

	@Override
	public Integer getCount(Session session) throws SQLException {
		return Integer.parseInt(
				session.createCriteria(Lection.class).setProjection(Projections.rowCount()).uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lection> getLectionsByCourse(Session session, Integer courseId) throws SQLException {
		Criteria criteria = session.createCriteria(Lection.class);
		return criteria.setFetchMode("course", FetchMode.JOIN).createAlias("course", "cour")
				.add(Restrictions.eq("cour.id", courseId)).addOrder(Order.asc(ID)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lection> getFreeLections(Session session) throws SQLException {
		Criteria criteria = session.createCriteria(Lection.class);
		return criteria.add(Restrictions.isNull("course")).addOrder(Order.asc(ID)).list();
	}

}
