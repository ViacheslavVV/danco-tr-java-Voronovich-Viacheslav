package com.training.danco.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.training.danco.dao.api.*;
import com.training.danco.model.*;

public class CourseRepository implements ICourseRepository {

	private static final int FIRST_POSITION = 0;

	public CourseRepository() {
	}

	@Override
	public boolean set(Connection connection, Course course) throws SQLException {

		Statement statement = connection.createStatement();
		Lecturer lecturer = course.getLecturer();
		return statement.executeUpdate("INSERT INTO `mydb`.`course` "
				+ "(`id`, `name`, `startDate`, `finalDate`, `maxStudents`, `maxLections`, `lecturerId`) "
				+ "VALUES (NULL, " + course.getStartDate() + ", " + course.getFinalDate() + ", "
				+ course.getMaxStudents() + ", " + course.getMaxLections() + ", " + lecturer == null ? "NULL"
						: (lecturer.getId() == 0) ? "NULL" : lecturer.getId() + ");") == 1;

	}

	@Override
	public Course get(Connection connection, int id) throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Course WHERE id =" + id + ";");
		Course course = null;
		try {
			course = parseResultSet(connection, result).get(FIRST_POSITION);
		} catch (NullPointerException | IndexOutOfBoundsException e) {
		}
		return course;
	}

	@Override
	public boolean update(Connection connection, Course course) throws SQLException {
		Statement statement = connection.createStatement();
		Lecturer lecturer = course.getLecturer();
		return statement.executeUpdate(
				"UPDATE  Course SET name = " + course.getName() + ", startDate = '" + course.getStartDate()
						+ "', finalDate = '" + course.getFinalDate() + "', maxStudents = " + course.getMaxStudents()
						+ ", maxLections = " + course.getMaxLections() + ", lecturerId = " + lecturer == null ? "NULL"
								: (lecturer.getId() == 0) ? "NULL"
										: lecturer.getId() + " WHERE id=" + course.getId() + ";") == 1;
	}

	@Override
	public boolean delete(Connection connection, Course course) throws SQLException {

		Statement statement = connection.createStatement();

		return statement.executeUpdate("DELETE FROM Course WHERE id = " + course.getId() + ";") == 1;
	}

	@Override
	public List<Course> getAll(Connection connection) throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Course;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Course> getSortedByStartDate(Connection connection) throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Course ORDER BY startDate;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Course> getSortedByStudentsCount(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(
				"SELECT id,name,startDate,finalDate,maxStudents,maxLections,lecturerId FROM course AS C JOIN (SELECT courseId,count(courseId) as count FROM student GROUP BY courseId) as B ON B.courseId=C.id ORDER BY count;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Course> getSortedByLecturer(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(
				"SELECT C.id,C.name,startDate,finalDate,maxStudents,maxLections,lecturerId FROM course AS C JOIN lecturer as B ON B.id=C.lecturerId ORDER BY B.name;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Course> getSortedByName(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM course ORDER BY name;");
		return parseResultSet(connection, result);
	}

	@Override
	public int getCount(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT count(id) as count FROM course;");
		result.next();
		return result.getInt("count");
	}

	@Override
	public List<Course> getCoursesInInterval(Connection connection, Date dateFrom, Date dateTo) throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet result = statement
				.executeQuery("SELECT * FROM course WHERE startDate>=" + dateFrom + " AND finalDate<=" + dateTo + ";");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStartDate(Connection connection) throws SQLException {

		Date curDate = new Date(System.currentTimeMillis());
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM course WHERE startDate<=" + curDate
				+ " AND finalDate>=" + curDate + " ORDER BY startDate;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStudentsCount(Connection connection) throws SQLException {
		Date curDate = new Date(System.currentTimeMillis());
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(
				"SELECT id,name,startDate,finalDate,maxStudents,maxLections,lecturerId FROM course AS C JOIN (SELECT courseId,count(courseId) as count FROM student GROUP BY courseId) as B ON B.courseId=C.id WHERE startDate<="
						+ curDate + " AND finalDate>=" + curDate + " ORDER BY count;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Course> getCurrentCoursesSortedByLecturer(Connection connection) throws SQLException {
		Date curDate = new Date(System.currentTimeMillis());
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(
				"SELECT C.id,C.name,startDate,finalDate,maxStudents,maxLections,lecturerId FROM course AS C JOIN lecturer as B ON B.id=C.lecturerId WHERE startDate<="
						+ curDate + " AND finalDate>=" + curDate + " ORDER BY B.name;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Course> getCurrentCoursesSortedByName(Connection connection) throws SQLException {
		Date curDate = new Date(System.currentTimeMillis());
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(
				"SELECT * FROM course WHERE startDate<=" + curDate + " AND finalDate>=" + curDate + " ORDER BY name;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStartDate(Connection connection, Date date) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement
				.executeQuery("SELECT * FROM course WHERE startDate>" + date + " ORDER BY startDate;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Connection connection, Date date) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(
				"SELECT id,name,startDate,finalDate,maxStudents,maxLections,lecturerId FROM course AS C JOIN (SELECT courseId,count(courseId) as count FROM student GROUP BY courseId) as B ON B.courseId=C.id WHERE startDate>"
						+ date + " ORDER BY count;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByLecturer(Connection connection, Date date) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(
				"SELECT C.id,C.name,startDate,finalDate,maxStudents,maxLections,lecturerId FROM course AS C JOIN lecturer as B ON B.id=C.lecturerId WHERE startDate>"
						+ date + " ORDER BY B.name;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByName(Connection connection, Date date) throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM course WHERE startDate>=" + date + " ORDER BY name;");
		return parseResultSet(connection, result);
	}

	@Override
	public Course cloneCourse(Connection connection, Course course) throws SQLException {
		Course clone = course.clone();
		if (!set(connection, clone)) {
			return null;
		}
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM course WHERE id=last_insert_id();");
		try {
			clone = parseResultSet(connection, result).get(FIRST_POSITION);
		} catch (NullPointerException | IndexOutOfBoundsException e) {
		}
		return clone;
	}

	@Override
	public List<Course> parseResultSet(Connection connection, ResultSet resultSet) throws SQLException {
		List<Course> courses = new ArrayList<Course>();
		
		while (resultSet.next()){
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			Date startDate = resultSet.getDate("startDate");
			Date finalDate = resultSet.getDate("finalDate");
			int maxStudents = resultSet.getInt("maxStudents");
			int maxLections = resultSet.getInt("maxLections");
			
			Course course = new Course(name, startDate, finalDate, maxStudents, maxLections);
			course.setId(id);
			
			courses.add(course);
			}
		return courses;
	}

}
