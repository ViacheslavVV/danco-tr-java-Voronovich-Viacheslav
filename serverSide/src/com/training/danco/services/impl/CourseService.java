package com.training.danco.services.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.configuration.manager.PropertyManager;
import com.training.danco.connection.manager.ConnectionManager;
import com.training.danco.dao.api.*;
import com.training.danco.model.*;
import com.training.danco.services.api.ICourseService;

public class CourseService implements ICourseService {

	private static final Logger LOGGER = LogManager.getLogger(CourseService.class);

	private ICourseRepository courseRepository;
	private ILecturerRepository lecturerRepository;
	private ILectionRepository lectionRepository;
	private IStudentRepository studentRepository;

	public CourseService(ICourseRepository courseRepository, ILecturerRepository lecturerRepository,
			ILectionRepository lectionRepository, IStudentRepository studentRepository) {
		this.courseRepository = courseRepository;
		this.lecturerRepository = lecturerRepository;
		this.lectionRepository = lectionRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	public boolean set(Course course) {
		boolean result = true;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			result = courseRepository.set(connection, course);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public Course get(int id) {

		Course resultCourse = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			resultCourse = this.courseRepository.get(connection, id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			ConnectionManager.closeConnection(connection);
		}

		return resultCourse;
	}

	@Override
	public boolean update(Course course) {
		boolean result = false;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			Course resultCourse = this.get(course.getId());

			if (resultCourse == null) {

				result = this.set(course);
			} else {
				result = this.courseRepository.update(connection, course);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public boolean delete(Course course) {
		boolean result = true;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			result = this.courseRepository.delete(connection, course);
			connection.commit();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public List<Course> getAll() {

		List<Course> resultCourses = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			resultCourses = this.courseRepository.getAll(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return resultCourses;
	}

	@Override
	public int getCount() {

		int count = 0;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			count = this.courseRepository.getCount(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return count;
	}

	@Override
	public boolean setLecturer(Course course, Lecturer lecturer) {
		boolean result = true;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			course.setLecturer(lecturer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;

	}

	@Override
	public boolean addLection(Course course, Lection lection) {
		boolean result = true;
		Connection connection = null;
		try {
			int studentCount = 0;
			
			for (Course tempCourse : this.courseRepository.getAll()) {
				studentCount += tempCourse.getLectionByDate(lection.getDate()).size() * tempCourse.getStudents().size();
			}
			studentCount += course.getLectionByDate(lection.getDate()).size() * course.getStudents().size();
			if (studentCount > PropertyManager.getInstance().getMaxStudentsCount()) {
				return false;
			}

			result = course.setLection(lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public boolean addStudent(Course course, Student student) {
		boolean result = true;
		Connection connection = null;
		try {
			result = course.setStudent(student);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public boolean removeLection(Course course, Lection lection) {
		boolean result = true;
		Connection connection = null;
		try {
			result = course.removeLection(lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public boolean removeStudent(Course course, Student student) {
		boolean result = true;
		Connection connection = null;
		try {
			result = course.removeStudent(student);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public List<Course> getCoursesInInterval(Date dateFrom, Date dateTo) {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getCoursesInInterval(dateFrom, dateTo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByStartDate() {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getSortedByStartDate();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByStudentsCount() {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getSortedByStudentsCount();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByLecturer() {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getSortedByLecturer();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByName() {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getSortedByName();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStartDate() {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getCurrentCoursesSortedByStartDate();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStudentsCount() {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getCurrentCoursesSortedByStudentsCount();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByLecturer() {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getCurrentCoursesSortedByLecturer();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByName() {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getCurrentCoursesSortedByName();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStartDate(Date date) {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByStartDate(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Date date) {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByStudentsCount(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByLecturer(Date date) {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByLecturer(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByName(Date date) {
		List<Course> tempCourses = null;
		Connection connection = null;
		try {
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByName(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempCourses;
	}

	@Override
	public List<Lection> getLectionsByCourse(Course course) {
		List<Lection> tempLections = null;
		Connection connection = null;
		try {
			if (course != null) {
				tempLections = course.getLections();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempLections;
	}

	@Override
	public List<Student> getStudentsByCourse(Course course) {
		List<Student> tempStudents = null;
		Connection connection = null;
		try {
			if (course != null) {
				tempStudents = course.getStudents();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempStudents = new ArrayList<Student>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempStudents;
	}

	@Override
	public Lecturer getLecturerByCourse(Course course) {

		Lecturer lecturer = null;
		Connection connection = null;
		try {
			if (course != null) {
				lecturer = course.getLecturer();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return lecturer;
	}

	@Override
	public boolean cloneCourse(Course course) {
		boolean result = true;
		Connection connection = null;
		try {
			Course newCourse = this.courseRepository.cloneCourse(course);
			if (newCourse == null) {
				result = false;
			}
			result = this.courseRepository.set(newCourse);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}

		return result;
	}

}
