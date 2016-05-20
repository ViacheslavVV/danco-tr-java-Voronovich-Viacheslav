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
import com.training.danco.params.CourseDateParam;
import com.training.danco.params.SortingParam;

public class CourseRepository extends AbstractRepository<Course, Integer> implements ICourseRepository {

	private static final Integer COURSE_POSITION = 0;
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String FINAL_DATE = "finalDate";
	private static final String START_DATE = "startDate";

	public CourseRepository() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getSorted(Session session, CourseDateParam courseDateParam, SortingParam sortingParam,
			Date date) throws SQLException {
		Criteria criteria = session.createCriteria(Course.class);

		switch (courseDateParam) {
		case AFTER_DATE: {
			criteria.add(Restrictions.gt(START_DATE, date));
			break;
		}
		case CURRENT: {
			Date curDate = new Date(System.currentTimeMillis());
			criteria.add(Restrictions.le(START_DATE, curDate)).add(Restrictions.ge(FINAL_DATE, curDate));
			break;
		}
		default: {
		}
		}

		switch (sortingParam) {
		case LECTURER: {
			criteria.setFetchMode("lecturer", FetchMode.JOIN).createAlias("lecturer", "lect");
			criteria.addOrder(Order.asc("lect.name"));
			break;
		}
		case STUDENTS_COUNT: {
			criteria.setFetchMode("students", FetchMode.JOIN).createAlias("students", "stud");
			ProjectionList projectionList = Projections.projectionList();
			projectionList.add(Projections.groupProperty("stud.course"));
			projectionList.add(Projections.rowCount(), "studCount");
			criteria.setProjection(projectionList);
			criteria.addOrder(Order.asc("studCount"));
			return getCoursesFromMixedResult(criteria.list());
		}
		case NAME: {
			criteria.addOrder(Order.asc(NAME));
			break;
		}
		case START_DATE: {
			criteria.addOrder(Order.asc(START_DATE));
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
				session.createCriteria(Course.class).setProjection(Projections.rowCount()).uniqueResult().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCoursesInInterval(Session session, Date dateFrom, Date dateTo) throws SQLException {

		Criteria criteria = session.createCriteria(Course.class);
		return criteria.add(Restrictions.eq(START_DATE, dateFrom)).add(Restrictions.le(FINAL_DATE, dateTo))
				.addOrder(Order.asc(ID)).list();
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

	private List<Course> getCoursesFromMixedResult(List<Object> list) {
		List<Course> courses = new ArrayList<>();
		for (Object obj : list) {
			obj.getClass();
			courses.add((Course) ((Object[]) obj)[COURSE_POSITION]);
		}
		return courses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCoursesByStudent(Session session, Integer studentId) throws SQLException {
		Criteria criteria = session.createCriteria(Course.class);
		return criteria.setFetchMode("students", FetchMode.JOIN).createAlias("students", "stud")
				.add(Restrictions.eq("stud.id", studentId)).addOrder(Order.asc(ID)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCoursesByLecturer(Session session, Integer lecturerId) throws SQLException {
		Criteria criteria = session.createCriteria(Course.class);
		return criteria.setFetchMode("lecturer", FetchMode.JOIN).createAlias("lecturer", "lect")
				.add(Restrictions.eq("lect.id", lecturerId)).addOrder(Order.asc(ID)).list();
	}

}
