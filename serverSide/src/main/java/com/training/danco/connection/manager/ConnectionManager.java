package com.training.danco.connection.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.configuration.manager.PropertyManager;

public class ConnectionManager {
	
	{
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	private static final Logger LOGGER = LogManager.getLogger(ConnectionManager.class);

	private static final String driver= PropertyManager.getInstance().getDriverName();
	private static final String url= PropertyManager.getInstance().getURL();
	private static final String userName= PropertyManager.getInstance().getUserName();
	private static final String userPassvord= PropertyManager.getInstance().getUserPassword();

	private static Connection connection;

	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(url, userName, userPassvord);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return connection;
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}
}
