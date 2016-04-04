package com.training.danco.services.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.connection.manager.ConnectionManager;
import com.training.danco.dao.api.ILectionRepository;
import com.training.danco.model.Lection;
import com.training.danco.services.api.ILectionService;

public class LectionService implements ILectionService {

	private static final Logger LOGGER = LogManager.getLogger(LectionService.class);

	private ILectionRepository lectionRepository;

	public LectionService(ILectionRepository lectionRepository) {
		this.lectionRepository = lectionRepository;
	}

	@Override
	public boolean set(Lection lection) {

		boolean result = true;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			result = this.lectionRepository.set(connection, lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public Lection get(int id) {

		Lection resultLection = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			resultLection = this.lectionRepository.get(connection, id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			ConnectionManager.closeConnection(connection);
		}

		return resultLection;
	}

	@Override
	public boolean update(Lection lection) {

		boolean result = false;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			connection.setAutoCommit(false);
			result = this.lectionRepository.update(connection, lection);
			if (!result) {
				result = this.lectionRepository.set(connection, lection);
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
	public boolean delete(Lection lection) {

		boolean result = true;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			result = this.lectionRepository.delete(connection, lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public List<Lection> getAll() {

		List<Lection> resultLections = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			resultLections = this.lectionRepository.getAll(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultLections = new ArrayList<Lection>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return resultLections;
	}

	@Override
	public List<Lection> getSortedByDate() {
		List<Lection> tempLections = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			tempLections = this.lectionRepository.getSortedByDate(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempLections;
	}

	@Override
	public List<Lection> getSortedByName() {
		List<Lection> tempLections = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			tempLections = this.lectionRepository.getSortedByName(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempLections;
	}

	@Override
	public List<Lection> getLectionsByDate(Date date) {
		List<Lection> tempLections = null;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			tempLections = this.lectionRepository.getLectionsByDate(connection,date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return tempLections;
	}

	@Override
	public int getCount() {
		int count = 0;
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
			count = this.lectionRepository.getCount(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			ConnectionManager.closeConnection(connection);
		}
		return count;
	}

}
