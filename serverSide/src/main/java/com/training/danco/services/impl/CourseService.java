package com.training.danco.services.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.dao.api.*;
import com.training.danco.model.*;
import com.training.danco.services.api.ICourseService;
import com.training.danco.session.manager.SessionManager;

public class CourseService implements ICourseService {

	private static final Logger LOGGER = LogManager.getLogger(CourseService.class);

	private ICourseRepository courseRepository;
	private ILectionRepository lectionRepository;
	private IStudentRepository studentRepository;

	public CourseService(ICourseRepository courseRepository, ILectionRepository lectionRepository,
			IStudentRepository studentRepository) {
		this.courseRepository = courseRepository;
		this.lectionRepository = lectionRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	public Integer set(Course course) {
		Integer result;
		Session session = null;
		try {
			session = SessionManager.getSession();
			result = courseRepository.set(session, course);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = null;
		} finally {
			SessionManager.closeSession(session);
		}
		return result;
	}

	@Override
	public Course get(int id) {

		Course resultCourse = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultCourse = this.courseRepository.get(session, id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeSession(session);
		}

		return resultCourse;
	}

	@Override
	public boolean update(Course course) {
		boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();

			if (course.getId() == null) {
				this.courseRepository.set(session, course);
			} else if (this.courseRepository.get(session, course.getId()) != null) {
				this.courseRepository.update(session, course);
			} else {
				this.courseRepository.set(session, course);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeSession(session);
		}
		return result;
	}

	@Override
	public boolean delete(Course course) {
		boolean result = true;
		Session session = null;
		try {
			session = SessionManager.getSession();
			this.courseRepository.delete(session, course);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeSession(session);
		}
		return result;
	}

	@Override
	public List<Course> getAll() {

		List<Course> resultCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultCourses = this.courseRepository.getAll(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return resultCourses;
	}

	@Override
	public int getCount() {

		int count = 0;
		Session session = null;
		try {
			session = SessionManager.getSession();
			count = this.courseRepository.getCount(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeSession(session);
		}
		return count;
	}

	@Override
	public boolean setLecturer(Course course, Lecturer lecturer) {
		boolean result = true;
		Session session = null;
		try {
			session = SessionManager.getSession();
			course.setLecturer(lecturer);
			this.courseRepository.update(session, course);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeSession(session);
		}
		return result;

	}

	@Override
	public boolean addLection(Course course, Lection lection) {
		boolean result = true;
		Session session = null;
		try {
			session = SessionManager.getSession();
			lection.setCourse(course);
			this.lectionRepository.update(session, lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeSession(session);
		}
		return result;
	}

	@Override
	public boolean addStudent(Course course, Student student) {
		boolean result = true;
		Session session = null;
		try {
			session = SessionManager.getSession();
			student.setCourse(course);
			this.studentRepository.update(session, student);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeSession(session);
		}
		return result;
	}

	@Override
	public boolean removeLection(Course course, Lection lection) {
		boolean result = true;
		Session session = null;
		try {
			session = SessionManager.getSession();
			lection.setCourse(null);
			this.lectionRepository.update(session, lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeSession(session);
		}
		return result;
	}

	@Override
	public boolean removeStudent(Course course, Student student) {
		boolean result = true;
		Session session = null;
		try {
			session = SessionManager.getSession();
			student.setCourse(null);
			this.studentRepository.update(session, student);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeSession(session);
		}
		return result;
	}

	@Override
	public List<Course> getCoursesInInterval(Date dateFrom, Date dateTo) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getCoursesInInterval(session, dateFrom, dateTo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByStartDate() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getSortedByStartDate(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByStudentsCount() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getSortedByStudentsCount(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByLecturer() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getSortedByLecturer(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByName() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getSortedByName(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStartDate() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getCurrentCoursesSortedByStartDate(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStudentsCount() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getCurrentCoursesSortedByStudentsCount(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByLecturer() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getCurrentCoursesSortedByLecturer(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByName() {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getCurrentCoursesSortedByName(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStartDate(Date date) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByStartDate(session, date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Date date) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByStudentsCount(session, date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByLecturer(Date date) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByLecturer(session, date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByName(Date date) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByName(session, date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public boolean cloneCourse(Course course) {
		boolean result = true;
		Session session = null;
		try {
			session = SessionManager.getSession();
			Course newCourse = this.courseRepository.cloneCourse(session, course);
			result = newCourse != null;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeSession(session);
		}

		return result;
	}

}
