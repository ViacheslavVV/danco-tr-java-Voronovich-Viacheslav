package com.training.danco.services.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.dao.api.ILectionRepository;
import com.training.danco.model.Lection;
import com.training.danco.params.SortingParam;
import com.training.danco.services.api.ILectionService;
import com.training.danco.session.manager.SessionManager;

public class LectionService implements ILectionService {

	private static final Logger LOGGER = LogManager.getLogger(LectionService.class);

	private ILectionRepository lectionRepository;

	public LectionService(ILectionRepository lectionRepository) {
		this.lectionRepository = lectionRepository;
	}

	@Override
	public Integer set(Lection lection) {

		Integer result;
		Session session = null;	
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			result = this.lectionRepository.set(session, lection);
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
	public Lection get(Integer id) {

		Lection resultLection = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultLection = this.lectionRepository.get(session, id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeSession(session);
		}

		return resultLection;
	}

	@Override
	public Boolean update(Lection lection) {

		Boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();

			if (lection.getId() == null) {
				this.lectionRepository.set(session, lection);
			} else if (this.lectionRepository.get(session, lection.getId()) != null) {
				this.lectionRepository.update(session, lection);
			} else {
				this.lectionRepository.set(session, lection);
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
	public Boolean delete(Lection lection) {

		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			this.lectionRepository.delete(session, lection);
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
	public List<Lection> getAll() {

		List<Lection> resultLections = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultLections = this.lectionRepository.getAll(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultLections = new ArrayList<Lection>();
		} finally {
			SessionManager.closeSession(session);
		}
		return resultLections;
	}

	@Override
	public List<Lection> getLectionsByDate(Date date) {
		List<Lection> tempLections = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempLections = this.lectionRepository.getLectionsByDate(session,date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempLections;
	}

	@Override
	public Integer getCount() {
		Integer count = 0;
		Session session = null;
		try {
			session = SessionManager.getSession();
			count = this.lectionRepository.getCount(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeSession(session);
		}
		return count;
	}

	@Override
	public List<Lection> getSorted(SortingParam sortingParam) {
		List<Lection> tempLections = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempLections = this.lectionRepository.getSorted(session,sortingParam);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempLections;
	}

}
