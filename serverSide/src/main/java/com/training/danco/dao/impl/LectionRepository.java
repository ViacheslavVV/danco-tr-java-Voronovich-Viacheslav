package com.training.danco.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.training.danco.dao.api.ILectionRepository;
import com.training.danco.model.Lection;

public class LectionRepository implements ILectionRepository {

	private static final String NAME = "name";
	private static final String ID = "id";
	private static final String DATE = "date";

	public LectionRepository() {
	}

	@Override
	public Integer set(Session session, Lection lection) throws SQLException {
		return (Integer) session.save(lection);
	}

	@Override
	public Lection get(Session session, int id) throws SQLException {
		return (Lection) session.get(Lection.class, id);
	}

	@Override
	public void update(Session session, Lection lection) throws SQLException {
		session.update(lection);
	}

	@Override
	public void delete(Session session, Lection lection) throws SQLException {
		session.delete(lection);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lection> getAll(Session session) throws SQLException {

		return session.createCriteria(Lection.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lection> getSortedByDate(Session session) throws SQLException {

		Criteria criteria = session.createCriteria(Lection.class);
		return criteria.addOrder(Order.asc(DATE)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lection> getSortedByName(Session session) throws SQLException {
		Criteria criteria = session.createCriteria(Lection.class);
		return criteria.addOrder(Order.asc(NAME)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lection> getLectionsByDate(Session session, Date date) throws SQLException {
		Criteria criteria = session.createCriteria(Lection.class);
		return criteria.add(Restrictions.eq(DATE, date)).addOrder(Order.asc(ID)).list();
	}

	@Override
	public int getCount(Session session) throws SQLException {
		return Integer.parseInt(session.createCriteria(Lection.class).setProjection(Projections.rowCount()).uniqueResult().toString());
	}

}
