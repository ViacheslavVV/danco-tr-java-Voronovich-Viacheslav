package com.training.danco.dao.api;

import java.util.Date;

import com.training.danco.model.Course;

public interface ICourseRepository {

	public boolean set(Course course);
	public Course get(int id);
	public boolean update(Course course);
	public boolean delete(Course course);
	public Course[] getAll();
	
	public int getCount();
	
	public Course[] getCoursesInInterval(Date dateFrom, Date dateTo);

	public Course[] getSortedByStartDate();
	public Course[] getSortedByStudentsCount();
	public Course[] getSortedByLecturer();
	public Course[] getSortedByName();
	
	public Course[] getCurrentCoursesSortedByStartDate();
	public Course[] getCurrentCoursesSortedByStudentsCount();
	public Course[] getCurrentCoursesSortedByLecturer();
	public Course[] getCurrentCoursesSortedByName();

	public Course[] getCoursesAfterDateSortedByStartDate(Date date);
	public Course[] getCoursesAfterDateSortedByStudentsCount(Date date);
	public Course[] getCoursesAfterDateSortedByLecturer(Date date);
	public Course[] getCoursesAfterDateSortedByName(Date date);
}
