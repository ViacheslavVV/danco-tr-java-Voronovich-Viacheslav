package com.training.danco.dao.api;

import java.util.Date;
import java.util.List;

import com.training.danco.model.Course;

public interface ICourseRepository {

	public boolean set(Course course);
	public Course get(int id);
	public boolean update(Course course);
	public boolean delete(Course course);
	public List<Course> getAll();
	public Course cloneCourse(Course course);
	public int getCount();
	
	public List<Course> getCoursesInInterval(Date dateFrom, Date dateTo);

	public List<Course> getSortedByStartDate();
	public List<Course> getSortedByStudentsCount();
	public List<Course> getSortedByLecturer();
	public List<Course> getSortedByName();
	
	public List<Course> getCurrentCoursesSortedByStartDate();
	public List<Course> getCurrentCoursesSortedByStudentsCount();
	public List<Course> getCurrentCoursesSortedByLecturer();
	public List<Course> getCurrentCoursesSortedByName();

	public List<Course> getCoursesAfterDateSortedByStartDate(Date date);
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Date date);
	public List<Course> getCoursesAfterDateSortedByLecturer(Date date);
	public List<Course> getCoursesAfterDateSortedByName(Date date);
}
