package com.training.danco.services.api;

import java.util.Date;
import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;

public interface ICourseService {

	public boolean set(Course course);
	public Course get(int id);
	public boolean update(Course course);
	public boolean delete(Course course);
	public List<Course> getAll();
	
	public int getCount();
	
	public Lecturer getLectureByCourse(Course course);
	public List<Lection> getLectionsByCourse(Course course);
	public List<Student> getStudentsByCourse(Course course);
	
	public boolean setLecturer(Course course, Lecturer lecturer);
	public boolean addLection(Course course, Lection lection);
	public boolean addStudent(Course course, Student student);
	public boolean removeLection(Course course, Lection lection);
	public boolean removeStudent(Course course, Student student);
	
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
