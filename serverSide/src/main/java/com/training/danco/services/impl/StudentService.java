package com.training.danco.services.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.dao.api.IStudentRepository;
import com.training.danco.model.Student;
import com.training.danco.services.api.IStudentService;
import com.training.danco.session.manager.SessionManager;

public class StudentService implements IStudentService {

	private static final Logger LOGGER = LogManager.getLogger(StudentService.class);
	
	private IStudentRepository studentRepository;
	
	public StudentService(IStudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Integer set(Student student) {
		Integer result;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			result = this.studentRepository.set(session, student);
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
	public Student get(Integer id) {
		
		Student resultStudent = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultStudent = this.studentRepository.get(session,id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeSession(session);
		}
		
		return resultStudent;
	}

	@Override
	public Boolean update(Student student) {
		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			Student persistStudent = this.studentRepository.get(session, student.getId());
			if (student.getId() == null) {
				result = this.studentRepository.set(session, student) != null;
			} else if (persistStudent != null) {
				this.copyFields(student, persistStudent);
				this.studentRepository.update(session, persistStudent);
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
	
	private void copyFields(Student student, Student persistStudent) {
		persistStudent.setAge(student.getAge());
		persistStudent.setName(student.getName());
	}

	@Override
	public Boolean delete(Integer studentId) {
		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			Student student = this.studentRepository.get(session, studentId);
			this.studentRepository.delete(session, student);
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
	public List<Student> getAll() {

		List<Student> resultStudents = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultStudents = this.studentRepository.getAll(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultStudents = new ArrayList<Student>();
		} finally {
			SessionManager.closeSession(session);
		}
		return resultStudents;
	}

	@Override
	public Integer getCount() {
		
		Integer count = 0;
		Session session = null;
		try {
			session = SessionManager.getSession();
			count = this.studentRepository.getCount(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeSession(session);
		}
		return count;
	}

	@Override
	public List<Student> getStudentsByCourse(Integer courseId) {
		List<Student> resultStudents = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultStudents = this.studentRepository.getStudentsByCourse(session, courseId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultStudents = new ArrayList<Student>();
		} finally {
			SessionManager.closeSession(session);
		}
		return resultStudents;
	}

	@Override
	public List<Student> getStudentsExceptCourse(Integer courseId) {
		List<Student> resultStudents = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultStudents = this.studentRepository.getStudentsExceptCourse(session, courseId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultStudents = new ArrayList<Student>();
		} finally {
			SessionManager.closeSession(session);
		}
		return resultStudents;
	}

}
