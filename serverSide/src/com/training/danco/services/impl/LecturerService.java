package com.training.danco.services.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.connection.manager.ConnectionManager;
import com.training.danco.dao.api.ILecturerRepository;
import com.training.danco.model.Lecturer;
import com.training.danco.services.api.ILecturerService;

public class LecturerService implements ILecturerService {

	private static final Logger LOGGER = LogManager.getLogger(LecturerService.class);

	private ILecturerRepository lecturerRepository;

	public LecturerService(ILecturerRepository lecturerRepository) {
		this.lecturerRepository = lecturerRepository;
	}

	@Override
	public boolean set(Lecturer lecturer) {

		boolean result = true;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			result = this.lecturerRepository.set(connection, lecturer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public Lecturer get(int id) {

		Lecturer resultLecturer = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			resultLecturer = this.lecturerRepository.get(connection, id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			ConnectionManager.closeConnection(connection);
		}

		return resultLecturer;
	}

	@Override
	public boolean update(Lecturer lecturer) {
		boolean result = false;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
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
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public boolean delete(Lecturer lecturer) {

		boolean result = true;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			result = this.lecturerRepository.delete(connection, lecturer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public List<Lecturer> getAll() {

		List<Lecturer> resultLecturers = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			resultLecturers = this.lecturerRepository.getAll(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultLecturers = new ArrayList<Lecturer>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return resultLecturers;
	}

	@Override
	public List<Lecturer> getSortedByName() {

		List<Lecturer> tempLecturers = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			tempLecturers = this.lecturerRepository.getSortedByName(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLecturers = new ArrayList<Lecturer>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempLecturers;
	}

	@Override
	public List<Lecturer> getSortedByCoursesCount() {

		List<Lecturer> tempLecturers = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			tempLecturers = this.lecturerRepository.getSortedByCoursesCount(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLecturers = new ArrayList<Lecturer>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempLecturers;
	}

	@Override
	public int getCount() {
		int count = 0;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			count = this.lecturerRepository.getCount(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return count;
	}

}
