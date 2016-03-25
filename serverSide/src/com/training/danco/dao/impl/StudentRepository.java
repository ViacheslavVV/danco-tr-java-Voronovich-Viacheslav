package com.training.danco.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.training.danco.dao.api.IStudentRepository;
import com.training.danco.model.*;

public class StudentRepository implements IStudentRepository {


	private static final int FIRST_POSITION = 0;
	public StudentRepository() {
	}

	@Override
	public boolean set(Connection connection, Student student) throws SQLException {
		Statement statement = connection.createStatement();
		Course course = student.getCourse();
		return statement.executeUpdate("INSERT INTO `mydb`.`student` " + "(`id`, `name`, `age`, `courseId`) " + "VALUES (NULL, "
				+ student.getName() + ", " + student.getAge()+ course == null ? "NULL"
						: (course.getId() == 0) ? "NULL" : course.getId() + ");") == 1;
	}

	@Override
	public Student get(Connection connection, int id) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Student WHERE id=" + id + ";");
		Student student = null;
		try {
			student = parseResultSet(connection, result).get(FIRST_POSITION);
		} catch (NullPointerException | IndexOutOfBoundsException e) {
		}
		return student;
	}

	@Override
	public boolean update(Connection connection, Student student) throws SQLException {
		Statement statement = connection.createStatement();
		Course course = student.getCourse();
		return statement.executeUpdate("UPDATE  Student SET name = " + student.getName() + ", age = "
				+ student.getAge()+" , courseId=" + course == null ? "NULL"
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
		return parseResultSet(connection, result);
	}

	@Override
	public int getCount(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT count(id) as count FROM student;");
		result.next();
		return result.getInt("count");
	}

	@Override
	public List<Student> parseResultSet(Connection connection, ResultSet resultSet) throws SQLException {
		List<Student> students = new ArrayList<Student>();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int age = resultSet.getInt("age");

			Student student = new Student(name, age);
			student.setId(id);
			students.add(student);
		}
		return students;
	}

	@Override
	public List<Student> getStudentsByCourse(Connection connection, Course course) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Student WHERE courseId="+course.getId()+";");
		return parseResultSet(connection, result);
	}
	
	

}
