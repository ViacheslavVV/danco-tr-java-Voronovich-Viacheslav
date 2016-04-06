package com.training.danco.dao.api;

import org.hibernate.Session;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.training.danco.model.Course;

public interface ICourseRepository extends IBaseRepository<Course>{

	public Course cloneCourse(Session session, Course course) throws SQLException;
	
	public List<Course> getCoursesInInterval(Session session, Date dateFrom, Date dateTo) throws SQLException;

	public List<Course> getSortedByStartDate(Session session) throws SQLException;
	public List<Course> getSortedByStudentsCount(Session session) throws SQLException;
	public List<Course> getSortedByLecturer(Session session) throws SQLException;
	public List<Course> getSortedByName(Session session) throws SQLException;
	
	public List<Course> getCurrentCoursesSortedByStartDate(Session session) throws SQLException;
	public List<Course> getCurrentCoursesSortedByStudentsCount(Session session) throws SQLException;
	public List<Course> getCurrentCoursesSortedByLecturer(Session session) throws SQLException;
	public List<Course> getCurrentCoursesSortedByName(Session session) throws SQLException;

	public List<Course> getCoursesAfterDateSortedByStartDate(Session session, Date date) throws SQLException;
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Session session, Date date) throws SQLException;
	public List<Course> getCoursesAfterDateSortedByLecturer(Session session, Date date) throws SQLException;
	public List<Course> getCoursesAfterDateSortedByName(Session session, Date date) throws SQLException;
}
