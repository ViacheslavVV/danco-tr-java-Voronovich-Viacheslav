package com.training.danco.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;

import com.training.danco.dao.api.*;
import com.training.danco.model.*;

public class CourseRepository implements ICourseRepository {

	private static final int COURSE_POSITION = 0;
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String FINAL_DATE = "finalDate";
	private static final String START_DATE = "startDate";

	public CourseRepository() {
	}

	@Override
	public Integer set(Session session, Course course) throws SQLException {
		return (Integer) session.save(course);
	}

	@Override
	public Course get(Session session, int id) throws SQLException {
		return (Course) session.get(Course.class, id);
	}

	@Override
	public void update(Session session, Course course) throws SQLException {
		session.update(course);
	}

	@Override
	public void delete(Session session, Course course) throws SQLException {
		session.delete(course);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getAll(Session session) throws SQLException {
		return (List<Course>) session.createCriteria(Course.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getSortedByStartDate(Session session) throws SQLException {

		return (List<Course>) session.createCriteria(Course.class).addOrder(Order.asc(START_DATE)).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getSortedByStudentsCount(Session session) throws SQLException {
		Criteria criteria = session.createCriteria(Course.class);
		criteria.setFetchMode("students", FetchMode.JOIN).createAlias("students", "stud");
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("stud.course"));
		projectionList.add(Projections.rowCount(), "studCount");
		criteria.setProjection(projectionList);
		criteria.addOrder(Order.asc("studCount"));
		return getCoursesFromMixedResult(criteria.list());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getSortedByLecturer(Session session) throws SQLException {
		Criteria criteria = session.createCriteria(Course.class);
		criteria.setFetchMode("lecturer", FetchMode.JOIN).createAlias("lecturer", "lect");
		criteria.addOrder(Order.asc("lect.name"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getSortedByName(Session session) throws SQLException {

		return session.createCriteria(Course.class).addOrder(Order.asc(NAME)).list();
	}

	@Override
	public int getCount(Session session) throws SQLException {
		return Integer.parseInt(session.createCriteria(Course.class).setProjection(Projections.rowCount()).uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCoursesInInterval(Session session, Date dateFrom, Date dateTo) throws SQLException {

		Criteria criteria = session.createCriteria(Course.class);
		return criteria.add(Restrictions.ge(START_DATE, dateFrom)).add(Restrictions.le(FINAL_DATE, dateTo))
				.addOrder(Order.asc(ID)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCurrentCoursesSortedByStartDate(Session session) throws SQLException {

		Date curDate = new Date(System.currentTimeMillis());
		Criteria criteria = session.createCriteria(Course.class);
		return criteria.add(Restrictions.le(START_DATE, curDate)).add(Restrictions.ge(FINAL_DATE, curDate))
				.addOrder(Order.asc(START_DATE)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCurrentCoursesSortedByStudentsCount(Session session) throws SQLException {
		Date curDate = new Date(System.currentTimeMillis());
		Criteria criteria = session.createCriteria(Course.class);
		criteria.setFetchMode("students", FetchMode.JOIN).createAlias("students", "stud");
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("stud.course"), "studGr");
		projectionList.add(Projections.rowCount(), "studCount");
		criteria.setProjection(projectionList).add(Restrictions.le(START_DATE, curDate))
				.add(Restrictions.ge(FINAL_DATE, curDate));
		criteria.addOrder(Order.asc("studCount"));
		return getCoursesFromMixedResult(criteria.list());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCurrentCoursesSortedByLecturer(Session session) throws SQLException {
		Date curDate = new Date(System.currentTimeMillis());
		Criteria criteria = session.createCriteria(Course.class);
		criteria.setFetchMode("lecturer", FetchMode.JOIN).createAlias("lecturer", "lect");
		return criteria.add(Restrictions.le(START_DATE, curDate)).add(Restrictions.ge(FINAL_DATE, curDate))
				.addOrder(Order.asc("lect.name")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCurrentCoursesSortedByName(Session session) throws SQLException {
		Date curDate = new Date(System.currentTimeMillis());
		Criteria criteria = session.createCriteria(Course.class);
		return criteria.add(Restrictions.le(START_DATE, curDate)).add(Restrictions.ge(FINAL_DATE, curDate))
				.addOrder(Order.asc(NAME)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCoursesAfterDateSortedByStartDate(Session session, Date date) throws SQLException {
		Criteria criteria = session.createCriteria(Course.class);
		return criteria.add(Restrictions.gt(START_DATE, date)).addOrder(Order.asc(START_DATE)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Session session, Date date) throws SQLException {
		Criteria criteria = session.createCriteria(Course.class);
		criteria.setFetchMode("students", FetchMode.JOIN).createAlias("students", "stud");
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("stud.course"), "studGr");
		projectionList.add(Projections.rowCount(), "studCount");
		criteria.setProjection(projectionList).add(Restrictions.gt(START_DATE, date));
		criteria.addOrder(Order.asc("studCount"));
		return getCoursesFromMixedResult(criteria.list());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCoursesAfterDateSortedByLecturer(Session session, Date date) throws SQLException {
		Criteria criteria = session.createCriteria(Course.class);
		criteria.setFetchMode("lecturer", FetchMode.JOIN).createAlias("lecturer", "lect");
		return criteria.add(Restrictions.gt(START_DATE, date)).addOrder(Order.asc("lect.name")).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCoursesAfterDateSortedByName(Session session, Date date) throws SQLException {

		Criteria criteria = session.createCriteria(Course.class);
		return criteria.add(Restrictions.gt(START_DATE, date)).addOrder(Order.asc(NAME)).list();
	}

	@Override
	public Course cloneCourse(Session session, Course course) throws SQLException {
		Course clone = course.clone();
		Integer id = set(session, clone);
		if (id == null) {
			return null;
		}
		clone.setId(id);
		return clone;
	}
	
	
	@SuppressWarnings("unchecked")
	private List<Course> getCoursesFromMixedResult(List<Object> list){
		List<Course> courses = new ArrayList<>();
		for (Object obj : list) {
			obj.getClass();
			courses.add((Course)((Object[])obj)[COURSE_POSITION]);	
		}
		return courses;
	}
}
