package com.training.danco.services.api;

import java.util.Date;

import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;

public interface ICourseService {

	public boolean set(Course course);
	public Course get(int id);
	public boolean update(Course course);
	public boolean delete(Course course);
	public Course[] getAll();
	
	public int getCount();
	
	public Lecturer getLectureByCourse(Course course);
	public Lection[] getLectionsByCourse(Course course);
	public Student[] getStudentsByCourse(Course course);
	
	public boolean setLecturer(Course course, Lecturer lecturer);
	public boolean addLection(Course course, Lection lection);
	public boolean addStudent(Course course, Student student);
	public boolean removeLection(Course course, Lection lection);
	public boolean removeStudent(Course course, Student student);
	
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
