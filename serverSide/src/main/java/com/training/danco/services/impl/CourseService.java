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
import com.training.danco.params.CourseDateParam;
import com.training.danco.params.SortingParam;
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
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			result = courseRepository.set(session, course);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			LOGGER.error(e.getMessage());
			result = null;
		} finally {
			SessionManager.closeSession(session);
		}
		return result;
	}

	@Override
	public Course get(Integer id) {

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
	public Boolean update(Course course) {
		Boolean result = false;
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
	public Boolean delete(Course course) {
		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			this.courseRepository.delete(session, course);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
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
	public Integer getCount() {

		Integer count = 0;
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
	public Boolean setLecturer(Course course, Lecturer lecturer) {
		Boolean result = true;
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
	public Boolean addLection(Course course, Lection lection) {
		Boolean result = true;
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
	public Boolean addStudent(Course course, Student student) {
		Boolean result = true;
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
	public Boolean removeLection(Course course, Lection lection) {
		Boolean result = true;
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
	public Boolean removeStudent(Course course, Student student) {
		Boolean result = true;
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
	public Boolean cloneCourse(Course course) {
		Boolean result = true;
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

	@Override
	public List<Course> getSorted(CourseDateParam courseDateParam, SortingParam sortingParam, Date date) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getSorted(session, courseDateParam, sortingParam, date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

}
