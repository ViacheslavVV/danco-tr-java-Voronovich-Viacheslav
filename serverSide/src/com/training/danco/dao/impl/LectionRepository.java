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

	private static final int NULL_EQUIVALENT = 0;
	private static final int COURSE_ID_COLUMN_INDEX = 5;
	private static final int LECTION_ID_COLUMN_INDEX = 1;
	private static final int FIRST_POSITION = 0;

	public LectionRepository() {
	}

	@Override
	public boolean set(Connection connection, Lection lection) throws SQLException {
		Statement statement = connection.createStatement();
		Course course = lection.getCourse();
		return statement.executeUpdate("INSERT INTO `mydb`.`course` " + "(`id`, `name`, `date`, `courseId`) "
				+ "VALUES (NULL, '" + lection.getName() + "', " + lection.getDate() + ", " + course == null ? "NULL"
						: (course.getId() == 0) ? "NULL" : course.getId() + ");") == 1;
	}

	@Override
	public Lection get(Connection connection, int id) throws SQLException {
		Statement statement = connection.createStatement();
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		.append("SELECT * FROM Lection as L ")
		.append("JOIN Course as C on L.courseId=C.id ")
		.append("WHERE L.id=").append(id)
		.append(" ORDER BY L.id;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		Lection lection = null;
		try {
			lection = parseResultSet(result).get(FIRST_POSITION);
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
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		.append("SELECT * FROM Lection as L ")
		.append("JOIN Course as C on L.courseId=C.id ")
		.append(" ORDER BY L.id;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Lection> getSortedByDate(Connection connection) throws SQLException {

		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		.append("SELECT * FROM Lection as L ")
		.append("JOIN Course as C on L.courseId=C.id ")
		.append(" ORDER BY L.date;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Lection> getSortedByName(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		.append("SELECT * FROM Lection as L ")
		.append("JOIN Course as C on L.courseId=C.id ")
		.append(" ORDER BY L.name;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Lection> getLectionsByDate(Connection connection, Date date) throws SQLException {
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		.append("SELECT * FROM Lection as L ")
		.append("JOIN Course as C on L.courseId=C.id ")
		.append("WHERE date=").append(date)
		.append(" ORDER BY L.id;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public int getCount(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT count(id) as count FROM Lection;");
		result.next();
		return result.getInt("count");
	}

	private List<Lection> parseResultSet(ResultSet resultSet) throws SQLException {
		List<Lection> lections = new ArrayList<Lection>();

		int lectionId, courseId, maxStudents, maxLections;
		String name;
		Date startDate, finalDate;
		Lection lection;
		Course course;
		while (resultSet.next()) {
			lectionId = resultSet.getInt(LECTION_ID_COLUMN_INDEX);
			name = resultSet.getString(2);
			startDate = resultSet.getDate(3);
			lection = new Lection(name, startDate);
			lection.setId(lectionId);

			courseId = resultSet.getInt(COURSE_ID_COLUMN_INDEX);
			if (courseId != NULL_EQUIVALENT) {
				name = resultSet.getString(6);
				startDate = resultSet.getDate(7);
				finalDate = resultSet.getDate(8);
				maxStudents = resultSet.getInt(9);
				maxLections = resultSet.getInt(10);
				course = new Course(name, startDate, finalDate, maxStudents, maxLections);
				course.setId(courseId);
				lection.setCourse(course);
			}

			lections.add(lection);
		}
		return lections;
	}
}
