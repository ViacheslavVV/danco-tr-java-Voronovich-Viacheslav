package com.training.danco.dao.api;

import org.hibernate.Session;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.params.CourseDateParam;
import com.training.danco.params.SortingParam;

public interface ICourseRepository extends IBaseRepository<Course, Integer> {

	public Course cloneCourse(Session session, Course course) throws SQLException;
	
	public List<Course> getCoursesByStudent(Session session, Integer studentId) throws SQLException;
	
	public List<Course> getCoursesByLecturer(Session session, Integer lecturerId) throws SQLException;

	public List<Course> getCoursesInInterval(Session session, Date dateFrom, Date dateTo) throws SQLException;

	public List<Course> getSorted(Session session, CourseDateParam courseDateParam, SortingParam sortingParam,
			Date date) throws SQLException;

	public Integer getCount(Session session) throws SQLException;
}
