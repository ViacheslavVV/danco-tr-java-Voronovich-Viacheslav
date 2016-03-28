package com.training.danco.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.training.danco.dao.api.ILecturerRepository;
import com.training.danco.model.Course;
import com.training.danco.model.Lecturer;

public class LecturerRepository implements ILecturerRepository {

	private static final int COURSE_ID_COLUMN_INDEX = 4;
	private static final int NULL_EQUIVALENT = 0;
	private static final int LECTURER_ID_COLUMN_INDEX = 1;
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
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		.append("SELECT * FROM Lecturer AS L ")
		.append("LEFT JOIN Course as C on L.id=C.lecturerId ")
		.append("WHERE id=").append(id)
		.append(" ORDER BY L.id;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		Lecturer lecturer = null;
		try {
			lecturer = parseResultSet(result).get(FIRST_POSITION);
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
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		.append("SELECT * FROM Lecturer AS L ")
		.append("LEFT JOIN Course as C on L.id=C.lecturerId ")
		.append(" ORDER BY L.id;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Lecturer> getSortedByName(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		.append("SELECT * FROM Lecturer AS L ")
		.append("LEFT JOIN Course as C on L.id=C.lecturerId ")
		.append(" ORDER BY L.name;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Lecturer> getSortedByCoursesCount(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
		.append("SELECT * FROM Lecturer AS L ")
		.append("LEFT JOIN Course as C on L.id=C.lecturerId ")
		.append("LEFT JOIN ")
		.append("SELECT lecturerId,count(id) as count ")
		.append("FROM course ")
		.append("GROUP BY lecturerId) as B ")
		.append("ON B.lecturerId=L.id ")
		.append("ORDER BY B.count;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet( result);
	}

	@Override
	public int getCount(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT count(id) as count FROM lecturer;");
		result.next();
		return result.getInt("count");
	}

	private List<Lecturer> parseResultSet(ResultSet resultSet) throws SQLException {
		HashMap<Integer, Lecturer> lecturersMap = new HashMap<>();
		HashMap<Integer, List<Course>> lecturerCourses = new HashMap<>();
		int lecturerId, courseId, age, maxStudents, maxLections;
		String name;
		Lecturer lecturer;
		Course course;
		Date startDate, finalDate;
		while (resultSet.next()) {
			lecturerId = resultSet.getInt(LECTURER_ID_COLUMN_INDEX);
			if (!lecturersMap.containsKey(lecturerId)) {
				name = resultSet.getString(2);
				age = resultSet.getInt(3);
				lecturer = new Lecturer(name, age);
				lecturer.setId(lecturerId);

				lecturersMap.put(lecturerId, lecturer);
				lecturerCourses.put(lecturerId, new ArrayList<>());
			}
			courseId = resultSet.getInt(COURSE_ID_COLUMN_INDEX);
			if (courseId != NULL_EQUIVALENT) {
				name = resultSet.getString(5);
				startDate = resultSet.getDate(6);
				finalDate = resultSet.getDate(7);
				maxStudents = resultSet.getInt(8);
				maxLections = resultSet.getInt(9);
				course = new Course(name, startDate, finalDate, maxStudents, maxLections);
				course.setId(courseId);
				lecturerCourses.get(lecturerId).add(course);
			}
		}

		List<Lecturer> lecturers = new ArrayList<>();
		List<Course> courses;
		for (Lecturer lect : lecturersMap.values()) {
			courses = lecturerCourses.get(lect.getId()) == null ? new ArrayList<>() : lecturerCourses.get(lect.getId());
			lect.setCourses(courses);
			lecturers.add(lect);
		}
		return lecturers;
	}

}
