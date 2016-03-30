package com.training.danco.dao.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;

public interface ICourseRepository extends IBaseRepository<Course>{

	public Course cloneCourse(Connection connection, Course course) throws SQLException;
	
	public List<Course> getCoursesInInterval(Connection connection, Date dateFrom, Date dateTo) throws SQLException;
	
	public boolean setLecturer(Connection connection, Course course, Lecturer lecturer) throws SQLException;
	public boolean addLection(Connection connection, Course course, Lection lection) throws SQLException;
	public boolean addStudent(Connection connection, Course course, Student student) throws SQLException;
	public boolean removeLection(Connection connection, Course course, Lection lection) throws SQLException;
	public boolean removeStudent(Connection connection, Course course, Student student) throws SQLException;

	public List<Course> getSortedByStartDate(Connection connection) throws SQLException;
	public List<Course> getSortedByStudentsCount(Connection connection) throws SQLException;
	public List<Course> getSortedByLecturer(Connection connection) throws SQLException;
	public List<Course> getSortedByName(Connection connection) throws SQLException;
	
	public List<Course> getCurrentCoursesSortedByStartDate(Connection connection) throws SQLException;
	public List<Course> getCurrentCoursesSortedByStudentsCount(Connection connection) throws SQLException;
	public List<Course> getCurrentCoursesSortedByLecturer(Connection connection) throws SQLException;
	public List<Course> getCurrentCoursesSortedByName(Connection connection) throws SQLException;

	public List<Course> getCoursesAfterDateSortedByStartDate(Connection connection, Date date) throws SQLException;
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Connection connection, Date date) throws SQLException;
	public List<Course> getCoursesAfterDateSortedByLecturer(Connection connection, Date date) throws SQLException;
	public List<Course> getCoursesAfterDateSortedByName(Connection connection, Date date) throws SQLException;
	
	public List<Student> getStudentsByCourse(Connection connection, Course course) throws SQLException;
	public List<Lection> getLectionsByCourse(Connection connection, Course course) throws SQLException;
	public Lecturer getLecturerByCourse(Connection connection, Course course) throws SQLException;
}
