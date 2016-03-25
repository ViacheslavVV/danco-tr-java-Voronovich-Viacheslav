package com.training.danco.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.training.danco.dao.api.ILecturerRepository;
import com.training.danco.model.Lecturer;

public class LecturerRepository implements ILecturerRepository {

	private static final int FIRST_POSITION = 0;

	public LecturerRepository() {
	}

	@Override
	public boolean set(Connection connection, Lecturer lecturer) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeUpdate("INSERT INTO `mydb`.`lecturer` " + "(`id`, `name`, `age`) " + "VALUES (NULL, "
				+ lecturer.getName() + ", " + lecturer.getAge() + ");") == 1;
	}

	@Override
	public Lecturer get(Connection connection, int id) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Lecturer WHERE id=" + id + ";");
		Lecturer lecturer = null;
		try {
			lecturer = parseResultSet(connection, result).get(FIRST_POSITION);
		} catch (NullPointerException | IndexOutOfBoundsException e) {
		}
		return lecturer;
	}

	@Override
	public boolean update(Connection connection, Lecturer lecturer) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeUpdate("UPDATE  Lection SET name = " + lecturer.getName() + ", age = "
				+ lecturer.getAge() + " WHERE id=" + lecturer.getId() + ";") == 1;
	}

	@Override
	public boolean delete(Connection connection, Lecturer lecturer) throws SQLException {
		Statement statement = connection.createStatement();

		return statement.executeUpdate("DELETE FROM Lecturer WHERE id = " + lecturer.getId() + ";") == 1;
	}

	@Override
	public List<Lecturer> getAll(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Lecturer;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Lecturer> getSortedByName(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Lecturer ORDER BY name;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Lecturer> getSortedByCoursesCount(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery(
				"SELECT  id,name,age FROM lecturer AS C JOIN (SELECT lecturerId,count(id) as count FROM course GROUP BY lecturerId) as B ON B.lecturerId=C.id ORDER BY count;");
		return parseResultSet(connection, result);
	}

	@Override
	public int getCount(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT count(id) as count FROM lecturer;");
		result.next();
		return result.getInt("count");
	}

	@Override
	public List<Lecturer> parseResultSet(Connection connection, ResultSet resultSet) throws SQLException {
		List<Lecturer> lecturers = new ArrayList<Lecturer>();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int age = resultSet.getInt("age");

			Lecturer lecturer = new Lecturer(name, age);
			lecturer.setId(id);
			lecturers.add(lecturer);
		}
		return lecturers;
	}

}
