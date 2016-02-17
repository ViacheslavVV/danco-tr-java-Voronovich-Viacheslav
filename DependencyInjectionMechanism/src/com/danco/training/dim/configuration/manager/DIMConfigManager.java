package com.danco.training.dim.configuration.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.configuration.manager.PropertyManager;

public class DIMConfigManager {

	private static final Logger LOGGER = LogManager.getLogger(DIMConfigManager.class);

	private static DIMConfigManager configManager;
	private Properties properties;

	private DIMConfigManager() {
		properties = new Properties();
		File file = new File(PropertyManager.getInstance().getConfigFileName());

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
		}

	}

	public static DIMConfigManager getInstance() {
		if (configManager == null) {
			configManager = new DIMConfigManager();
		}
		return configManager;
	}

	public String getImplClassName(String interfaceName) {
		return properties.getProperty(interfaceName);

	}
}
