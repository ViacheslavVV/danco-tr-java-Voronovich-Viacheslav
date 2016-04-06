package com.training.danco.session.manager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionManager {

	{
		try {
			Configuration configuration = new Configuration().configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	private static final Logger LOGGER = LogManager.getLogger(SessionManager.class);

	private static SessionFactory sessionFactory;

	public static Session getSession() {
		return sessionFactory.openSession();
	}

	public static void closeSession(Session session) {
		try {
			if (session != null) {
				session.close();
			}
		} catch (HibernateException e) {
			LOGGER.error(e.getMessage());
		}
	}
}
