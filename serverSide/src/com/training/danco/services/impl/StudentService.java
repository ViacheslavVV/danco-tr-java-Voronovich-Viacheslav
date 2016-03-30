package com.training.danco.services.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.connection.manager.ConnectionManager;
import com.training.danco.dao.api.IStudentRepository;
import com.training.danco.model.Student;
import com.training.danco.services.api.IStudentService;

public class StudentService implements IStudentService {

	private static final Logger LOGGER = LogManager.getLogger(StudentService.class);
	
	private IStudentRepository studentRepository;
	
	public StudentService(IStudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public boolean set(Student student) {
		boolean result = true;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			result = studentRepository.set(connection, student);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public Student get(int id) {
		
		Student resultStudent = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			resultStudent = this.studentRepository.get(connection,id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		
		return resultStudent;
	}

	@Override
	public boolean update(Student student) {
		
		boolean result = false;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			result = this.studentRepository.update(connection, student);
			if (!result) {
				result = this.studentRepository.set(connection, student);
			}

			if (result) {
				connection.commit();
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public boolean delete(Student student) {
		boolean result = true;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			result = this.studentRepository.delete(connection, student);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public List<Student> getAll() {

		List<Student> resultStudents = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			resultStudents = this.studentRepository.getAll(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultStudents = new ArrayList<Student>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return resultStudents;
	}

	@Override
	public int getCount() {
		
		int count = 0;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			count = this.studentRepository.getCount(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return count;
	}

}
