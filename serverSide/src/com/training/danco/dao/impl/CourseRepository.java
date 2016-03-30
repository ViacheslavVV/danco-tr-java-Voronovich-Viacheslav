package com.training.danco.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.training.danco.dao.api.*;
import com.training.danco.model.*;

public class CourseRepository implements ICourseRepository {

	private static final int STUDENT_ID_COLUMN_INDEX = 11;
	private static final int LECTION_ID_COLUMN_INDEX = 15;
	private static final int NULL_EQUIVALENT = 0;
	private static final int LECTURER_ID_COLUMN_INDEX = 8;
	private static final int COURSE_ID_COLUMN_INDEX = 1;
	private static final int FIRST_POSITION = 0;

	public CourseRepository() {
	}

	@Override
	public boolean set(Connection connection, Course course) throws SQLException {

		Statement statement = connection.createStatement();
		Lecturer lecturer = course.getLecturer();
		return statement.executeUpdate("INSERT INTO `mydb`.`course` "
				+ "(`id`, `name`, `startDate`, `finalDate`, `maxStudents`, `maxLections`, `lecturerId`) "
				+ "VALUES (NULL, '"+course.getName()+"', " + course.getStartDate() + ", " + course.getFinalDate() + ", "
				+ course.getMaxStudents() + ", " + course.getMaxLections() + ", " + lecturer == null ? "NULL"
						: (lecturer.getId() == 0) ? "NULL" : lecturer.getId() + ");") == 1;

	}

	@Override
	public Course get(Connection connection, int id) throws SQLException {

		Statement statement = connection.createStatement();

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM course as C ").append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("WHERE C.id=").append(id)
				.append(" ORDER BY C.id;");

		ResultSet result = statement.executeQuery(stringBuilder.toString());
		Course course = null;
		try {
			course = parseResultSet(result).get(FIRST_POSITION);
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
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("SELECT * FROM course as C ")
				.append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ")
				.append("ORDER BY C.id;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Course> getSortedByStartDate(Connection connection) throws SQLException {

		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("SELECT * FROM course as C ")
				.append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("ORDER BY C.startDate;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Course> getSortedByStudentsCount(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("SELECT * FROM course as C ")
				.append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ")
				.append("left JOIN ")
				.append("(SELECT courseId,count(courseId) as count ")
				.append("FROM student ")
				.append("GROUP BY courseId) as Cc ")
				.append("ON Cc.courseId=C.id ")
				.append("ORDER BY Cc.count;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());

		return parseResultSet(result);
	}

	@Override
	public List<Course> getSortedByLecturer(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("SELECT * FROM course as C ")
				.append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("ORDER BY L.name;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Course> getSortedByName(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM course as C ").append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("ORDER BY C.name;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
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
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM course as C ").append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("WHERE C.startDate>=").append(dateFrom)
				.append(" AND C.finalDate<=").append(dateTo).append(" ORDER BY C.id;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStartDate(Connection connection) throws SQLException {

		Date curDate = new Date(System.currentTimeMillis());
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM course as C ").append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("WHERE C.startDate<=").append(curDate)
				.append(" AND C.finalDate>=").append(curDate).append(" ORDER BY C.startDate;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStudentsCount(Connection connection) throws SQLException {
		Date curDate = new Date(System.currentTimeMillis());
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM course as C ").append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("left join ")
				.append("(SELECT courseId,count(courseId) as count ").append("FROM student ")
				.append("GROUP BY courseId) as Cc ").append("ON Cc.courseId=C.id ").append("WHERE C.startDate<=")
				.append(curDate).append(" AND C.finalDate>=").append(curDate).append(" ORDER BY Cc.count;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Course> getCurrentCoursesSortedByLecturer(Connection connection) throws SQLException {
		Date curDate = new Date(System.currentTimeMillis());
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM course as C ").append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("WHERE C.startDate<=").append(curDate)
				.append(" AND C.finalDate>=").append(curDate).append(" ORDER BY L.name;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Course> getCurrentCoursesSortedByName(Connection connection) throws SQLException {
		Date curDate = new Date(System.currentTimeMillis());
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM course as C ").append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("WHERE C.startDate<=").append(curDate)
				.append(" AND C.finalDate>=").append(curDate).append(" ORDER BY C.name;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStartDate(Connection connection, Date date) throws SQLException {
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM course as C ").append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("WHERE C.startDate>").append(date)
				.append(" ORDER BY C.startDate;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Connection connection, Date date) throws SQLException {
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM course as C ").append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("left join ")
				.append("(SELECT courseId,count(courseId) as count ").append("FROM student ")
				.append("GROUP BY courseId) as Cc ").append("ON Cc.courseId=C.id ").append("WHERE C.startDate>")
				.append(date).append(" ORDER BY Cc.count;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByLecturer(Connection connection, Date date) throws SQLException {
		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM course as C ").append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("WHERE C.startDate>").append(date)
				.append(" ORDER BY L.name;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByName(Connection connection, Date date) throws SQLException {

		Statement statement = connection.createStatement();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM course as C ").append("left join lecturer as L on C.lecturerId=L.id ")
				.append("left join student as S on S.courseId=C.id ")
				.append("left join lection as Le on Le.courseId=C.id ").append("WHERE C.startDate>").append(date)
				.append(" ORDER BY C.name;");
		ResultSet result = statement.executeQuery(stringBuilder.toString());
		return parseResultSet(result);
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
			clone = parseResultSet(result).get(FIRST_POSITION);
		} catch (NullPointerException | IndexOutOfBoundsException e) {
		}
		return clone;
	}

	@Override
	public boolean setLecturer(Connection connection, Course course, Lecturer lecturer) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeUpdate(
				"UPDATE  Course SET lecturerId = " + lecturer.getId() + " WHERE id=" + course.getId() + ";") == 1;
	}

	@Override
	public boolean addLection(Connection connection, Course course, Lection lection) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeUpdate(
				"UPDATE  Lection SET courseId = " + course.getId() + " WHERE id=" + lection.getId() + ";") == 1;
	}

	@Override
	public boolean addStudent(Connection connection, Course course, Student student) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeUpdate(
				"UPDATE  Student SET courseId = " + course.getId() + " WHERE id=" + student.getId() + ";") == 1;
	}

	@Override
	public boolean removeLection(Connection connection, Course course, Lection lection) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeUpdate(
				"DELETE FROM Lection WHERE id = " + lection.getId() + " AND courseId=" + course.getId() + ";") == 1;
	}

	@Override
	public boolean removeStudent(Connection connection, Course course, Student student) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeUpdate("UPDATE  Student SET courseId = NULL WHERE id=" + student.getId()
				+ " AND courseId=" + course.getId() + ";") == 1;
	}

	@Override
	public List<Student> getStudentsByCourse(Connection connection, Course course) throws SQLException {
		Statement statement = connection.createStatement(); 

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("SELECT S.id as id, S.name as name, S.age as age FROM course as C ")
				.append("join student as S on S.courseId=C.id ").append("WHERE C.id=").append(course.getId())
				.append(" ORDER BY C.id;");

		ResultSet resultSet = statement.executeQuery(stringBuilder.toString());
		return parseStudentSet(resultSet);
	}

	@Override
	public List<Lection> getLectionsByCourse(Connection connection, Course course) throws SQLException {
		Statement statement = connection.createStatement(); 

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("SELECT Le.id as id, Le.name as name, Le.date as date FROM course as C ")
				.append("join lection as Le on Le.courseId=C.id ").append("WHERE C.id=").append(course.getId())
				.append(" ORDER BY C.id;");

		ResultSet resultSet = statement.executeQuery(stringBuilder.toString());
		return parseLectionSet(resultSet);
	}

	@Override
	public Lecturer getLecturerByCourse(Connection connection, Course course) throws SQLException {
		Statement statement = connection.createStatement(); 

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("Select * FROM Lecturer ")
				.append("WHERE id IN ")
				.append("(SELECT lecturerId FROM Course ")
				.append("WHERE id=").append(course.getId()).append(");");

		ResultSet resultSet = statement.executeQuery(stringBuilder.toString());
		if (!resultSet.next()){
			return null;
		}
		
		String name = resultSet.getString("name");
		int age = resultSet.getInt("age"), id = resultSet.getInt("id");
		Lecturer lecturer = new Lecturer(name, age);
		lecturer.setId(id);
		return lecturer;
	}


	private List<Course> parseResultSet(ResultSet resultSet) throws SQLException {
		List<Course> courses = new ArrayList<Course>();

		HashMap<Integer, Course> courseMap = new HashMap<>();
		HashMap<Integer, Lection> lectionMap = new HashMap<>();
		lectionMap.put(NULL_EQUIVALENT, null);
		HashMap<Integer, Lecturer> lecturerMap = new HashMap<>();
		lecturerMap.put(NULL_EQUIVALENT, null);
		HashMap<Integer, Student> studentMap = new HashMap<>();
		studentMap.put(NULL_EQUIVALENT, null);

		HashMap<Integer, List<Integer>> courseLections = new HashMap<>();
		HashMap<Integer, List<Integer>> courseStudents = new HashMap<>();
		HashMap<Integer, Integer> courseLecturer = new HashMap<>();

		List<Integer> courseOrder = new ArrayList<>();

		this.fillByEntities(courseMap, lectionMap, lecturerMap, studentMap, courseLections, courseStudents,
				courseLecturer, resultSet, courseOrder);

		Course course = null;
		List<Student> students;
		List<Lection> lections;
		for (Integer courseId : courseOrder) {

			course = courseMap.get(courseId);
			lections = new ArrayList<>();
			students = new ArrayList<>();

			for (Integer lectionId : courseLections.get(courseId)) {
				lections.add(lectionMap.get(lectionId));
			}
			course.setStudents(students);

			for (Integer studentId : courseStudents.get(courseId)) {
				students.add(studentMap.get(studentId));
			}
			course.setLections(lections);

			course.setLecturer(lecturerMap.get(courseId));

			courses.add(course);
		}

		return courses;
	}

	private void fillByEntities(HashMap<Integer, Course> courses, HashMap<Integer, Lection> lections,
			HashMap<Integer, Lecturer> lecturers, HashMap<Integer, Student> students,
			HashMap<Integer, List<Integer>> courseLections, HashMap<Integer, List<Integer>> courseStudents,
			HashMap<Integer, Integer> courseLecturer, ResultSet resultSet, List<Integer> courseOrder)
					throws SQLException {

		String name=null;
		Date startDate, finalDate;
		int maxStudents, maxLections, age, courseId, lecturerId, studentId, lectionId;
		Course tempCourse;
		Lecturer lecturer;
		Lection lection;
		Student student;
		while (resultSet.next()) {
			courseId = resultSet.getInt(COURSE_ID_COLUMN_INDEX);

			if (!courses.containsKey(courseId)) {
				name = resultSet.getString(2);
				startDate = resultSet.getDate(3);
				finalDate = resultSet.getDate(4);
				maxStudents = resultSet.getInt(5);
				maxLections = resultSet.getInt(6);
				tempCourse = new Course(name, startDate, finalDate, maxStudents, maxLections);
				tempCourse.setId(courseId);
				courses.put(courseId, tempCourse);
				courseLections.put(courseId, new ArrayList<>());
				courseLecturer.put(courseId, null);
				courseStudents.put(courseId, new ArrayList<>());
				courseOrder.add(courseId);
			}

			lecturerId = resultSet.getInt(LECTURER_ID_COLUMN_INDEX);
			if (!lecturers.containsKey(lecturerId)) {
				name = resultSet.getString(9);
				age = resultSet.getInt(10);
				lecturer = new Lecturer(name, age);
				lecturer.setId(lecturerId);

				lecturers.put(lecturerId, lecturer);
			}
			if (lecturerId != NULL_EQUIVALENT && courseLecturer.get(courseId) == null) {
				courseLecturer.put(courseId, lecturerId);
			}

			studentId = resultSet.getInt(STUDENT_ID_COLUMN_INDEX);
			if (!students.containsKey(studentId)) {
				name = resultSet.getString(12);
				age = resultSet.getInt(13);
				student = new Student(name, age);
				student.setId(studentId);

				students.put(studentId, student);
				if (!courseStudents.get(courseId).contains(studentId)) {
					courseStudents.get(courseId).add(studentId);
				}
			}

			lectionId = resultSet.getInt(LECTION_ID_COLUMN_INDEX);
			if (!lections.containsKey(lectionId)) {
				name = resultSet.getString(16);
				startDate = resultSet.getDate(17);
				lection = new Lection(name, startDate);
				lection.setId(lectionId);

				lections.put(lectionId, lection);
				if (!courseLections.get(courseId).contains(lectionId)) {
					courseLections.get(courseId).add(lectionId);
				}
			}

		}
		resultSet.beforeFirst();
	}

	private List<Student> parseStudentSet(ResultSet resultSet) throws SQLException{
		List<Student> students = new ArrayList<Student>();

		int id,age;
		String name;
		Student student;
		while (resultSet.next()) {

			id = resultSet.getInt("id");
			name = resultSet.getString("name");
			age = resultSet.getInt("age");
			student = new Student(name, age);
			student.setId(id);

			students.add(student);
		}
		return students;
	}

	private List<Lection> parseLectionSet(ResultSet resultSet) throws SQLException{
		List<Lection> lections = new ArrayList<Lection>();

		int id;
		String name;
		Date date;
		Lection lection;
		while (resultSet.next()) {
			id = resultSet.getInt("id");
			name = resultSet.getString("name");
			date = resultSet.getDate("date");
			lection = new Lection(name, date);
			lection.setId(id);

			lections.add(lection);
		}
		return lections;
	}
}
