package com.training.danco.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.training.danco.comparator.Comparator;
import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.model.Course;

public class CourseRepository implements ICourseRepository {

	
	private List<Course> courses; 
	
	public CourseRepository(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public boolean set(Course course) {
		
		return this.courses.add(course);
	}

	@Override
	public Course get(int id) {
		int index = getCourseIndexById(id);
		if (index != -1)
		{
			return this.courses.get(index);
		}
		return null;
	}

	@Override
	public boolean update(Course course) {
		int index = getCourseIndexById(course.getId());
		if (index != -1)
		{
			this.courses.set(index, course);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Course course) {
		int index = getCourseIndexById(course.getId());
		if (index != -1)
		{
			return this.courses.remove(index) != null;
		}
		return false;
	}

	@Override
	public List<Course> getAll() {

		Collections.sort(this.courses,Comparator.COURSE_ID_COMPARATOR);
		return this.courses;
	}
	
	private int getCourseIndexById(int id)
	{
		for (int i=0; i<this.courses.size(); i++)
		{			
			if (this.courses.get(i).getId() == id)
			{
				return i;
			}
		}
		return -1;
	}
	
	private List<Course> getCurrentCourses()
	{
		Date curDate = new Date(System.currentTimeMillis());
		List<Course> currentCourses = new ArrayList<Course>();
		for (Course course : this.courses)
		{
			if (course.getFinalDate().after(curDate) &&
					course.getStartDate().before(curDate))
			{
				currentCourses.add(course);
			}
		}
		return currentCourses;
	}
	
	private List<Course> getCoursesAfterDate(Date date)
	{
		List<Course> coursesAfterDate = new ArrayList<Course>();
		for (Course course : this.courses)
		{
			if (course.getStartDate().after(date))
			{
				coursesAfterDate.add(course);
			}
		}
		return coursesAfterDate;
	}
	
	@Override
	public List<Course> getSortedByStartDate() {
		
		Collections.sort(this.courses, Comparator.COURSE_START_DATE_COMPARATOR);
		return this.courses;
	}

	@Override
	public List<Course> getSortedByStudentsCount() {
		Collections.sort(this.courses,Comparator.COURSE_STUDENT_COUNT_COMPARATOR);
		return this.courses;
	}

	@Override
	public List<Course> getSortedByLecturer() {
		Collections.sort(this.courses,Comparator.COURSE_LECTURER_COMPARATOR);
		return this.courses;
	}

	@Override
	public List<Course> getSortedByName() {
		Collections.sort(this.courses,Comparator.COURSE_NAME_COMPARATOR);
		return this.courses;
	}

	@Override
	public int getCount() {
		
		return this.courses.size();
	}

	@Override
	public List<Course> getCoursesInInterval(Date dateFrom, Date dateTo) {
		
		List<Course> coursesInInterval = new ArrayList<Course>();
		for (Course course : this.courses)
		{
			if (course.getStartDate().before(dateTo) 
					&& course.getStartDate().after(dateFrom))
			{
				coursesInInterval.add(course);
			}
		}
		return coursesInInterval;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStartDate() {

		List<Course> cours = getCurrentCourses();
		Collections.sort(this.courses, Comparator.COURSE_START_DATE_COMPARATOR);
		return cours;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStudentsCount() {
		List<Course> cours = getCurrentCourses();
		Collections.sort(this.courses, Comparator.COURSE_STUDENT_COUNT_COMPARATOR);
		return cours;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByLecturer() {
		List<Course> cours = getCurrentCourses();
		Collections.sort(this.courses, Comparator.COURSE_LECTURER_COMPARATOR);
		return cours;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByName() {
		List<Course> cours = getCurrentCourses();
		Collections.sort(this.courses, Comparator.COURSE_NAME_COMPARATOR);
		return cours;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStartDate(Date date) {
		List<Course> cours = getCoursesAfterDate(date);
		Collections.sort(this.courses, Comparator.COURSE_START_DATE_COMPARATOR);
		return cours;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Date date) {

		List<Course> cours = getCoursesAfterDate(date);
		Collections.sort(this.courses, Comparator.COURSE_STUDENT_COUNT_COMPARATOR);
		return cours;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByLecturer(Date date) {
		List<Course> cours = getCoursesAfterDate(date);
		Collections.sort(this.courses,  Comparator.COURSE_LECTURER_COMPARATOR);
		return cours;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByName(Date date) {

		List<Course> cours = getCoursesAfterDate(date);
		Collections.sort(this.courses, Comparator.COURSE_NAME_COMPARATOR);
		return cours;
	}

	@Override
	public Course cloneCourse(Course course) {
		
		return course == null ? null : course.clone();
	}

}
