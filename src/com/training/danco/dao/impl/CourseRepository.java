package com.training.danco.dao.impl;

import java.util.Arrays;
import java.util.Date;

import com.training.danco.comparator.Comparator;
import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.model.Course;

public class CourseRepository implements ICourseRepository {

	
	private Course[] courses; 
	
	public CourseRepository(Course[] courses) {
		this.courses = courses;
	}

	@Override
	public boolean set(Course course) {
		int index = getVocantCourseNumber();
		if (index != -1)
		{
			courses[index] = course;
			return true;
		}
		return false;
	}

	@Override
	public Course get(int id) {
		int index = getCourseIndexById(id);
		if (index != -1)
		{
			return courses[index];
		}
		return null;
	}

	@Override
	public boolean update(Course course) {
		int index = getCourseIndexById(course.getId());
		if (index != -1)
		{
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Course course) {
		int index = getCourseIndexById(course.getId());
		if (index != -1)
		{
			courses[index] = null;
			return true;
		}
		return false;
	}

	@Override
	public Course[] getAll() {

		return getNotNullCourses();
	}
	
	private int getVocantCourseNumber()
	{
		for (int i=0; i<courses.length; i++)
		{
			if (courses[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int getCourseIndexById(int id)
	{
		for (int i=0; i<courses.length; i++)
		{
			if (courses[i] == null)
			{
				continue;
			}
			
			if (courses[i].getId() == id)
			{
				return i;
			}
		}
		return -1;
	}
	
	private Course[] getNotNullCourses()
	{
		Course[] notNullCourses = new Course[getCount()];
		int currentIndex =0;
		for (Course course: this.courses)
		{
			if (course != null)
			{
				notNullCourses[currentIndex++] = course;
			}
		}
		return notNullCourses;
	}
	
	private int getCoursesIntervalCount(Date dateFrom, Date dateTo)
	{
		int count=0;
		for (Course course : this.courses)
		{
			if (course == null)
			{
				continue;
			}
			if (course.getFinalDate().compareTo(dateTo) <= 0 
					&& course.getStartDate().compareTo(dateFrom) >= 0)
			{
				count++;
			}
		}
		return count;
	}
	
	private int getCurrentCoursesCount(Date curDate)
	{
		int count = 0;
		for (Course course : this.courses)
		{
			if (course == null)
			{
				continue;
			}
			if (course.getFinalDate().compareTo(curDate) < 0 &&
					course.getStartDate().compareTo(curDate) > 0)
			{
				count++;
			}
		}
		return count;
	}
	
	private Course[] getCurrentCourses()
	{
		Date curDate = new Date(System.currentTimeMillis());
		Course[] cours = new Course[getCurrentCoursesCount(curDate)];
		int index = 0;
		for (Course course : this.courses)
		{
			if (course == null)
			{
				continue;
			}
			if (course.getFinalDate().compareTo(curDate) < 0 &&
					course.getStartDate().compareTo(curDate) > 0)
			{
				cours[index++] = course;
			}
		}
		return cours;
	}
	
	private int getCoursesAfterDateCount(Date date)
	{
		int count = 0;
		for (Course course : this.courses)
		{
			if (course == null)
			{
				continue;
			}
			if (course.getStartDate().compareTo(date) > 0 )
			{
				count++;
			}
		}
		return count;
	}
	
	private Course[] getCoursesAfterDate(Date date)
	{
		Course[] cours = new Course[getCoursesAfterDateCount(date)];
		int index = 0;
		for (Course course : this.courses)
		{
			if (course == null)
			{
				continue;
			}
			if (course.getFinalDate().compareTo(date) > 0 )
			{
				cours[index++] = course;
			}
		}
		return cours;
	}
	
	@Override
	public Course[] getSortedByStartDate() {
		
		Course[] cours = getNotNullCourses();
		Arrays.sort(cours, Comparator.COURSE_START_DATE_COMPARATOR);
		return cours;
	}

	@Override
	public Course[] getSortedByStudentsCount() {
		Course[] cours = getNotNullCourses();
		Arrays.sort(cours,Comparator.COURSE_STUDENT_COUNT_COMPARATOR);
		return cours;
	}

	@Override
	public Course[] getSortedByLecturer() {
		Course[] cours = getNotNullCourses();
		Arrays.sort(cours,Comparator.COURSE_LECTURER_COMPARATOR);
		return cours;
	}

	@Override
	public Course[] getSortedByName() {

		Course[] cours = getNotNullCourses();
		Arrays.sort(cours,Comparator.COURSE_NAME_COMPARATOR);
		return cours;
	}

	@Override
	public int getCount() {
		int count = 0;
		for (Course course: this.courses)
		{
			if (course != null)
			{
				count++;
			}
		}
		return count;
	}

	@Override
	public Course[] getCoursesInInterval(Date dateFrom, Date dateTo) {
		
		Course[] cours = new Course[getCoursesIntervalCount(dateFrom, dateTo)];
		int index = 0;
		for (Course course : this.courses)
		{
			if (course.getFinalDate().compareTo(dateTo) <= 0 
					&& course.getStartDate().compareTo(dateFrom) >= 0)
			{
				cours[index++]=course;
			}
		}
		return cours;
	}

	@Override
	public Course[] getCurrentCoursesSortedByStartDate() {

		Course[] cours = getCurrentCourses();
		Arrays.sort(cours, Comparator.COURSE_START_DATE_COMPARATOR);
		return cours;
	}

	@Override
	public Course[] getCurrentCoursesSortedByStudentsCount() {
		Course[] cours = getCurrentCourses();
		Arrays.sort(cours, Comparator.COURSE_STUDENT_COUNT_COMPARATOR);
		return cours;
	}

	@Override
	public Course[] getCurrentCoursesSortedByLecturer() {
		Course[] cours = getCurrentCourses();
		Arrays.sort(cours, Comparator.COURSE_LECTURER_COMPARATOR);
		return cours;
	}

	@Override
	public Course[] getCurrentCoursesSortedByName() {
		Course[] cours = getCurrentCourses();
		Arrays.sort(cours, Comparator.COURSE_NAME_COMPARATOR);
		return cours;
	}

	@Override
	public Course[] getCoursesAfterDateSortedByStartDate(Date date) {
		Course[] cours = getCoursesAfterDate(date);
		Arrays.sort(cours, Comparator.COURSE_START_DATE_COMPARATOR);
		return cours;
	}

	@Override
	public Course[] getCoursesAfterDateSortedByStudentsCount(Date date) {

		Course[] cours = getCoursesAfterDate(date);
		Arrays.sort(cours, Comparator.COURSE_STUDENT_COUNT_COMPARATOR);
		return cours;
	}

	@Override
	public Course[] getCoursesAfterDateSortedByLecturer(Date date) {
		Course[] cours = getCoursesAfterDate(date);
		Arrays.sort(cours,  Comparator.COURSE_LECTURER_COMPARATOR);
		return cours;
	}

	@Override
	public Course[] getCoursesAfterDateSortedByName(Date date) {

		Course[] cours = getCoursesAfterDate(date);
		Arrays.sort(cours, Comparator.COURSE_NAME_COMPARATOR);
		return cours;
	}

}
