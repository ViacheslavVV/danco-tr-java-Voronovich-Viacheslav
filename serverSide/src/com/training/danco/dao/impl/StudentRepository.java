package com.training.danco.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.training.danco.dao.api.IStudentRepository;
import com.training.danco.model.*;

public class StudentRepository implements IStudentRepository {

	private static final int NULL_EQUIVALENT = 0;
	private static final int COURSE_ID_COLUMN_INDEX = 5;
	private static final int STUDENT_ID_COLUMN_INDEX = 1;
	private static final int FIRST_POSITION = 0;

	public StudentRepository() {
	}

	@Override
	public boolean set(Connection connection, Student student) throws SQLException {
		Statement statement = connection.createStatement();
		Course course = student.getCourse();
		return statement.executeUpdate("INSERT INTO `mydb`.`student` " + "(`id`, `name`, `age`, `courseId`) "
				+ "VALUES (NULL, " + student.getName() + ", " + student.getAge() + course == null ? "NULL"
						: (course.getId() == 0) ? "NULL" : course.getId() + ");") == 1;
	}

	@Override
	public Student get(Connection connection, int id) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Student WHERE id=" + id + ";");
		Student student = null;
		try {
			student = parseResultSet(result).get(FIRST_POSITION);
		} catch (NullPointerException | IndexOutOfBoundsException e) {
		}
		return student;
	}

	@Override
	public boolean update(Connection connection, Student student) throws SQLException {
		Statement statement = connection.createStatement();
		Course course = student.getCourse();
		return statement.executeUpdate("UPDATE  Student SET name = " + student.getName() + ", age = " + student.getAge()
				+ " , courseId=" + course == null ? "NULL"
						: (course.getId() == 0) ? "NULL" : course.getId() + " WHERE id=" + student.getId() + ";") == 1;
	}

	@Override
	public boolean delete(Connection connection, Student student) throws SQLException {
		Statement statement = connection.createStatement();

		return statement.executeUpdate("DELETE FROM Student WHERE id = " + student.getId() + ";") == 1;
	}

	@Override
	public List<Student> getAll(Connection connection) throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Student;");
		return parseResultSet(result);
	}

	@Override
	public int getCount(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT count(id) as count FROM student;");
		result.next();
		return result.getInt("count");
	}

	public List<Student> parseResultSet(ResultSet resultSet) throws SQLException {
		List<Student> students = new ArrayList<Student>();

		int age, studentId, courseId, maxStudents, maxLections;
		Date startDate, finalDate;
		String name;
		Student student;
		Course course;
		while (resultSet.next()) {

			studentId = resultSet.getInt(STUDENT_ID_COLUMN_INDEX);
			name = resultSet.getString(2);
			age = resultSet.getInt(3);
			student = new Student(name, age);
			student.setId(studentId);

			courseId = resultSet.getInt(COURSE_ID_COLUMN_INDEX);
			if (courseId != NULL_EQUIVALENT) {
				name = resultSet.getString(6);
				startDate = resultSet.getDate(7);
				finalDate = resultSet.getDate(8);
				maxStudents = resultSet.getInt(9);
				maxLections = resultSet.getInt(10);
				course = new Course(name, startDate, finalDate, maxStudents, maxLections);
				course.setId(courseId);
				student.setCourse(course);
			}
			students.add(student);
		}
		return students;
	}

}
