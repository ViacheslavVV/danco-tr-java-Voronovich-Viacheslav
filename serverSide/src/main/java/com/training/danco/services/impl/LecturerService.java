package com.training.danco.services.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.dao.api.ILecturerRepository;
import com.training.danco.model.Lecturer;
import com.training.danco.params.SortingParam;
import com.training.danco.services.api.ILecturerService;
import com.training.danco.session.manager.SessionManager;

public class LecturerService implements ILecturerService {

	private static final Logger LOGGER = LogManager.getLogger(LecturerService.class);

	private ILecturerRepository lecturerRepository;

	public LecturerService(ILecturerRepository lecturerRepository) {
		this.lecturerRepository = lecturerRepository;
	}

	@Override
	public Integer set(Lecturer lecturer) {

		Integer result;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			result = this.lecturerRepository.set(session, lecturer);
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
	public Lecturer get(Integer id) {

		Lecturer resultLecturer = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultLecturer = this.lecturerRepository.get(session, id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeSession(session);
		}

		return resultLecturer;
	}

	@Override
	public Boolean update(Lecturer lecturer) {
		Boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();

			if (lecturer.getId() == null) {
				this.lecturerRepository.set(session, lecturer);
			} else if (this.lecturerRepository.get(session, lecturer.getId()) != null) {
				this.lecturerRepository.update(session, lecturer);
			} else {
				this.lecturerRepository.set(session, lecturer);
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
	public Boolean delete(Integer lecturerId) {

		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			Lecturer lecturer = this.lecturerRepository.get(session, lecturerId);
			this.lecturerRepository.delete(session, lecturer);
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
	public List<Lecturer> getAll() {

		List<Lecturer> resultLecturers = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultLecturers = this.lecturerRepository.getAll(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultLecturers = new ArrayList<Lecturer>();
		} finally {
			SessionManager.closeSession(session);
		}
		return resultLecturers;
	}

	@Override
	public Integer getCount() {
		Integer count = 0;
		Session session = null;
		try {
			session = SessionManager.getSession();
			count = this.lecturerRepository.getCount(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeSession(session);
		}
		return count;
	}

	@Override
	public List<Lecturer> getSorted(SortingParam sortingParam) {
		List<Lecturer> tempLecturers = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			tempLecturers = this.lecturerRepository.getSorted(session,sortingParam);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLecturers = new ArrayList<Lecturer>();
		} finally {
			SessionManager.closeSession(session);
		}
		return tempLecturers;
	}

}
