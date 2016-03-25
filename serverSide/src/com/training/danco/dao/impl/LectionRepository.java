package com.training.danco.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.training.danco.dao.api.ILectionRepository;
import com.training.danco.model.Course;
import com.training.danco.model.Lection;

public class LectionRepository implements ILectionRepository {

	private static final int FIRST_POSITION = 0;

	public LectionRepository() {
	}

	@Override
	public boolean set(Connection connection, Lection lection) throws SQLException {
		Statement statement = connection.createStatement();
		Course course = lection.getCourse();
		return statement.executeUpdate("INSERT INTO `mydb`.`course` " + "(`id`, `name`, `date`, `courseId`) "
				+ "VALUES (NULL, " + lection.getName() + ", " + lection.getDate() + ", " + course == null ? "NULL"
						: (course.getId() == 0) ? "NULL" : course.getId() + ");") == 1;
	}

	@Override
	public Lection get(Connection connection, int id) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Lection WHERE id=" + id + ";");
		Lection lection = null;
		try {
			lection = parseResultSet(connection, result).get(FIRST_POSITION);
		} catch (NullPointerException | IndexOutOfBoundsException e) {
		}
		return lection;
	}

	@Override
	public boolean update(Connection connection, Lection lection) throws SQLException {
		Statement statement = connection.createStatement();
		Course course = lection.getCourse();
		return statement.executeUpdate("UPDATE  Lection SET name = " + lection.getName() + ", date = '"
				+ lection.getDate() + "', courseId = " + course == null ? "NULL"
						: (course.getId() == 0) ? "NULL" : course.getId() + " WHERE id=" + lection.getId() + ";") == 1;
	}

	@Override
	public boolean delete(Connection connection, Lection lection) throws SQLException {
		Statement statement = connection.createStatement();

		return statement.executeUpdate("DELETE FROM Lection WHERE id = " + lection.getId() + ";") == 1;
	}

	@Override
	public List<Lection> getAll(Connection connection) throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Lection;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Lection> getSortedByDate(Connection connection) throws SQLException {

		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Lection ORDER BY date;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Lection> getSortedByName(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Lection ORDER BY name;");
		return parseResultSet(connection, result);
	}

	@Override
	public List<Lection> getLectionsByDate(Connection connection, Date date) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Lection WHERE date=" + date + ";");
		return parseResultSet(connection, result);
	}

	@Override
	public int getCount(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT count(id) as count FROM lection;");
		result.next();
		return result.getInt("count");
	}

	@Override
	public List<Lection> parseResultSet(Connection connection, ResultSet resultSet) throws SQLException {
		List<Lection> lections = new ArrayList<Lection>();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			Date date = resultSet.getDate("date");

			Lection lection = new Lection(name, date);
			lection.setId(id);
			lections.add(lection);
		}
		return lections;
	}

	@Override
	public List<Lection> getLectionsByCourse(Connection connection, Course course) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM Lection WHERE courseId=" + course.getId() + ";");
		return parseResultSet(connection, result);
	}

}
