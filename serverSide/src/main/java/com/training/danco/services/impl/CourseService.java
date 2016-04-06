package com.training.danco.services.impl;

import org.hibernate.Session;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.configuration.manager.PropertyManager;
import com.training.danco.dao.api.*;
import com.training.danco.model.*;
import com.training.danco.services.api.ICourseService;
import com.training.danco.session.manager.SessionManager;

public class CourseService implements ICourseService {

	private static final Logger LOGGER = LogManager.getLogger(CourseService.class);

	private ICourseRepository courseRepository;

	public CourseService(ICourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public boolean set(Course course) {
		boolean result = true;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			result = courseRepository.set(connection, course);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public Course get(int id) {

		Course resultCourse = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			resultCourse = this.courseRepository.get(connection, id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeConnection(connection);
		}

		return resultCourse;
	}

	@Override
	public boolean update(Course course) {
		boolean result = false;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			connection.setAutoCommit(false);
			result = this.courseRepository.update(connection, course);
			if (!result) {
				result = this.courseRepository.set(connection, course);
			}
			
			if (result){
				connection.commit();
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public boolean delete(Course course) {
		boolean result = true;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			result = this.courseRepository.delete(connection, course);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public List<Course> getAll() {

		List<Course> resultCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			resultCourses = this.courseRepository.getAll(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return resultCourses;
	}

	@Override
	public int getCount() {

		int count = 0;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			count = this.courseRepository.getCount(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeConnection(connection);
		}
		return count;
	}

	@Override
	public boolean setLecturer(Course course, Lecturer lecturer) {
		boolean result = true;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			this.courseRepository.setLecturer(connection, course, lecturer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeConnection(connection);
		}
		return result;

	}

	@Override
	public boolean addLection(Course course, Lection lection) {
		boolean result = true;
		Session session = null;
		try {
			int studentCount = 0;
			connection = SessionManager.getConnection();
			connection.setAutoCommit(false);
			for (Course tempCourse : this.courseRepository.getAll(connection)) {
				studentCount += tempCourse.getLectionByDate(lection.getDate()).size() * tempCourse.getStudents().size();
			}
			studentCount += course.getLectionByDate(lection.getDate()).size() * course.getStudents().size();
			if (studentCount > PropertyManager.getInstance().getMaxStudentsCount()) {
				return false;
			}
			result = this.courseRepository.addLection(connection, course, lection);
			connection.commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public boolean addStudent(Course course, Student student) {
		boolean result = true;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			result = this.courseRepository.addStudent(connection, course, student);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public boolean removeLection(Course course, Lection lection) {
		boolean result = true;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			result = this.courseRepository.removeLection(connection, course, lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public boolean removeStudent(Course course, Student student) {
		boolean result = true;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			result = this.courseRepository.removeStudent(connection, course, student);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public List<Course> getCoursesInInterval(Date dateFrom, Date dateTo) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getCoursesInInterval(connection, dateFrom, dateTo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByStartDate() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getSortedByStartDate(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByStudentsCount() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getSortedByStudentsCount(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByLecturer() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getSortedByLecturer(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByName() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getSortedByName(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStartDate() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getCurrentCoursesSortedByStartDate(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStudentsCount() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getCurrentCoursesSortedByStudentsCount(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByLecturer() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getCurrentCoursesSortedByLecturer(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByName() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getCurrentCoursesSortedByName(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStartDate(Date date) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByStartDate(connection, date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Date date) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByStudentsCount(connection, date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByLecturer(Date date) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByLecturer(connection, date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByName(Date date) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByName(connection, date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public boolean cloneCourse(Course course) {
		boolean result = true;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			connection.setAutoCommit(false);
			Course newCourse = this.courseRepository.cloneCourse(connection, course);
			if (newCourse == null) {
				result = false;
			}
			connection.commit();
			result = course.getId() == newCourse.getId();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeConnection(connection);
		}

		return result;
	}

	@Override
	public List<Student> getStudentsByCourse(Course course) {
		List<Student> students = new ArrayList<>();
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			students = this.courseRepository.getStudentsByCourse(connection, course);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeConnection(connection);
		}
		return students;
	}

	@Override
	public List<Lection> getLectionsByCourse(Course course) {
		List<Lection> lections = new ArrayList<>();
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			lections = this.courseRepository.getLectionsByCourse(connection, course);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeConnection(connection);
		}
		return lections;
	}

	@Override
	public Lecturer getLecturerByCourse(Course course) {
		Lecturer lecturer = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			lecturer = this.courseRepository.getLecturerByCourse(connection, course);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeConnection(connection);
		}
		return lecturer;
	}

}
