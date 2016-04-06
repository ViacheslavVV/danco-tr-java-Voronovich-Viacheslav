package com.training.danco.services.impl;

import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.dao.api.ILecturerRepository;
import com.training.danco.model.Lecturer;
import com.training.danco.services.api.ILecturerService;
import com.training.danco.session.manager.SessionManager;

public class LecturerService implements ILecturerService {

	private static final Logger LOGGER = LogManager.getLogger(LecturerService.class);

	private ILecturerRepository lecturerRepository;

	public LecturerService(ILecturerRepository lecturerRepository) {
		this.lecturerRepository = lecturerRepository;
	}

	@Override
	public boolean set(Lecturer lecturer) {

		boolean result = true;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			result = this.lecturerRepository.set(connection, lecturer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public Lecturer get(int id) {

		Lecturer resultLecturer = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			resultLecturer = this.lecturerRepository.get(connection, id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeConnection(connection);
		}

		return resultLecturer;
	}

	@Override
	public boolean update(Lecturer lecturer) {
		boolean result = false;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			connection.setAutoCommit(false);
			result = this.lecturerRepository.update(connection, lecturer);
			if (!result) {
				result = this.lecturerRepository.set(connection, lecturer);
			}

			if (result) {
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
	public boolean delete(Lecturer lecturer) {

		boolean result = true;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			result = this.lecturerRepository.delete(connection, lecturer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public List<Lecturer> getAll() {

		List<Lecturer> resultLecturers = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			resultLecturers = this.lecturerRepository.getAll(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultLecturers = new ArrayList<Lecturer>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return resultLecturers;
	}

	@Override
	public List<Lecturer> getSortedByName() {

		List<Lecturer> tempLecturers = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempLecturers = this.lecturerRepository.getSortedByName(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLecturers = new ArrayList<Lecturer>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempLecturers;
	}

	@Override
	public List<Lecturer> getSortedByCoursesCount() {

		List<Lecturer> tempLecturers = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempLecturers = this.lecturerRepository.getSortedByCoursesCount(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLecturers = new ArrayList<Lecturer>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempLecturers;
	}

	@Override
	public int getCount() {
		int count = 0;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			count = this.lecturerRepository.getCount(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeConnection(connection);
		}
		return count;
	}

}
