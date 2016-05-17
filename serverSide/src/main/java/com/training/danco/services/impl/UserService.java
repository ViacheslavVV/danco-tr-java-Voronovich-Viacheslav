package com.training.danco.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.training.danco.dao.api.IUserRepository;
import com.training.danco.model.User;
import com.training.danco.services.api.IUserService;
import com.training.danco.session.manager.SessionManager;

public class UserService implements IUserService{

	private static final Logger LOGGER = LogManager.getLogger(UserService.class);
	
	private IUserRepository userRepository;
	
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Integer set(User user) {
		Integer result;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			result = userRepository.set(session, user);
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
	public User get(Integer id) {
		User resultUser = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultUser = this.userRepository.get(session, id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeSession(session);
		}

		return resultUser;
	}

	@Override
	public Boolean update(User user) {
		Boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();

			if (user.getId() == null) {
				this.userRepository.set(session, user);
			} else if (this.userRepository.get(session, user.getId()) != null) {
				this.userRepository.update(session, user);
			} else {
				this.userRepository.set(session, user);
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
	public Boolean delete(Integer userId) {
		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			User user = this.userRepository.get(session, userId);
			this.userRepository.delete(session, user);
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
	public List<User> getAll() {
		List<User> resultUsers = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultUsers = this.userRepository.getAll(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultUsers = new ArrayList<User>();
		} finally {
			SessionManager.closeSession(session);
		}
		return resultUsers;
	}

}
