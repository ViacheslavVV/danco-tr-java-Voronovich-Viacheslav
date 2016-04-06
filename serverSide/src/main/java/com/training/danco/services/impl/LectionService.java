package com.training.danco.services.impl;

import org.hibernate.Session;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.dao.api.ILectionRepository;
import com.training.danco.model.Lection;
import com.training.danco.services.api.ILectionService;
import com.training.danco.session.manager.SessionManager;

public class LectionService implements ILectionService {

	private static final Logger LOGGER = LogManager.getLogger(LectionService.class);

	private ILectionRepository lectionRepository;

	public LectionService(ILectionRepository lectionRepository) {
		this.lectionRepository = lectionRepository;
	}

	@Override
	public boolean set(Lection lection) {

		boolean result = true;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			result = this.lectionRepository.set(connection, lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public Lection get(int id) {

		Lection resultLection = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			resultLection = this.lectionRepository.get(connection, id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeConnection(connection);
		}

		return resultLection;
	}

	@Override
	public boolean update(Lection lection) {

		boolean result = false;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
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
			SessionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public boolean delete(Lection lection) {

		boolean result = true;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			result = this.lectionRepository.delete(connection, lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		} finally {
			SessionManager.closeConnection(connection);
		}
		return result;
	}

	@Override
	public List<Lection> getAll() {

		List<Lection> resultLections = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			resultLections = this.lectionRepository.getAll(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultLections = new ArrayList<Lection>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return resultLections;
	}

	@Override
	public List<Lection> getSortedByDate() {
		List<Lection> tempLections = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempLections = this.lectionRepository.getSortedByDate(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempLections;
	}

	@Override
	public List<Lection> getSortedByName() {
		List<Lection> tempLections = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempLections = this.lectionRepository.getSortedByName(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempLections;
	}

	@Override
	public List<Lection> getLectionsByDate(Date date) {
		List<Lection> tempLections = null;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			tempLections = this.lectionRepository.getLectionsByDate(connection,date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		} finally {
			SessionManager.closeConnection(connection);
		}
		return tempLections;
	}

	@Override
	public int getCount() {
		int count = 0;
		Session session = null;
		try {
			connection = SessionManager.getConnection();
			count = this.lectionRepository.getCount(connection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeConnection(connection);
		}
		return count;
	}

}
