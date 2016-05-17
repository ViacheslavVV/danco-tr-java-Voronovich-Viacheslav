package com.training.danco.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.training.danco.dao.api.IAuditRepository;
import com.training.danco.model.Audit;
import com.training.danco.services.api.IAuditService;
import com.training.danco.session.manager.SessionManager;

public class AuditService implements IAuditService {
	
	private static final Logger LOGGER = LogManager.getLogger(AuditService.class);

	private IAuditRepository auditRepository;

	public AuditService(IAuditRepository auditRepository) {
		this.auditRepository = auditRepository;
	}

	@Override
	public Integer set(Audit audit) {
		Integer result;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			result = auditRepository.set(session, audit);
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
	public Audit get(Integer id) {
		Audit resultAudit = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultAudit = this.auditRepository.get(session, id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			SessionManager.closeSession(session);
		}

		return resultAudit;
	}

	@Override
	public Boolean update(Audit audit) {
		Boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();

			if (audit.getId() == null) {
				this.auditRepository.set(session, audit);
			} else if (this.auditRepository.get(session, audit.getId()) != null) {
				this.auditRepository.update(session, audit);
			} else {
				this.auditRepository.set(session, audit);
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
	public Boolean delete(Integer auditId) {
		Boolean result = true;
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionManager.getSession();
			transaction = session.beginTransaction();
			Audit audit = this.auditRepository.get(session, auditId);
			this.auditRepository.delete(session, audit);
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
	public List<Audit> getAll() {
		List<Audit> resultAudits = null;
		Session session = null;
		try {
			session = SessionManager.getSession();
			resultAudits = this.auditRepository.getAll(session);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultAudits = new ArrayList<Audit>();
		} finally {
			SessionManager.closeSession(session);
		}
		return resultAudits;
	}

}
