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
	private ILecturerRepository lecturerRepository;

	public CourseService(ICourseRepository courseRepository, ILectionRepository lectionRepository,
			IStudentRepository studentRepository, ILecturerRepository lecturerRepository) {
		this.courseRepository = courseRepository;
		this.lectionRepository = lectionRepository;
		this.studentRepository = studentRepository;
		this.lecturerRepository = lecturerRepository;
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
		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			Course persistCourse = this.courseRepository.get(session, course.getId());
			if (course.getId() == null) {
				result = this.courseRepository.set(session, course) != null;
			} else if (persistCourse != null) {
				this.copyFields(course,persistCourse);
				this.courseRepository.update(session, persistCourse);
			} else {
				result = false;
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			result = false;
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeSession(session);
		}
		return result;
	}

	private void copyFields(Course course, Course persistCourse) {
		persistCourse.setFinalDate(course.getFinalDate());
		persistCourse.setName(course.getName());
		persistCourse.setStartDate(course.getStartDate());
		persistCourse.setMaxStudents(course.getMaxStudents());
		persistCourse.setMaxLections(course.getMaxLections());
	}

	@Override
	public Boolean delete(Integer courseId) {
		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			Course course = this.courseRepository.get(session, courseId);
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
	public Boolean setLecturer(Integer courseId, Integer lecturerId) {
		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			Course course = this.courseRepository.get(session, courseId);
			Lecturer lecturer = this.lecturerRepository.get(session, lecturerId);
			course.setLecturer(lecturer);
			this.courseRepository.update(session, course);
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
	public Boolean addLection(Integer courseId, Integer lectionId) {
		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			Course course = this.courseRepository.get(session, courseId);
			Lection lection = this.lectionRepository.get(session, lectionId);
			lection.setCourse(course);
			this.lectionRepository.update(session, lection);
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
	public Boolean addStudent(Integer courseId, Integer studentId) {
		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			Course course = this.courseRepository.get(session, courseId);
			Student student = this.studentRepository.get(session, studentId);
			student.setCourse(course);
			this.studentRepository.update(session, student);
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
	public Boolean removeLection(Integer courseId, Integer lectionId) {
		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			Lection lection = this.lectionRepository.get(session, lectionId);
			if (lection.getCourse().getId().equals(courseId)) {
				lection.setCourse(null);
				this.lectionRepository.update(session, lection);
				transaction.commit();
			} else {
				result = false;
			}
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
	public Boolean removeStudent(Integer courseId, Integer studentId) {
		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			Student student = this.studentRepository.get(session, studentId);
			if (student.getCourse().getId().equals(courseId)) {
				student.setCourse(null);
				this.studentRepository.update(session, student);
				transaction.commit();
			} else {
				result = false;
			}
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

	@Override
	public List<Course> getCoursesByStudent(Integer studentId) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getCoursesByStudent(session, studentId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesByLecturer(Integer lecturerId) {
		List<Course> tempCourses = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempCourses = this.courseRepository.getCoursesByLecturer(session, lecturerId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempCourses;
	}

}
