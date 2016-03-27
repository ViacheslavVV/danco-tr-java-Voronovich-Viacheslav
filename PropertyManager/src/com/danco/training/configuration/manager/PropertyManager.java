package com.danco.training.configuration.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyManager {


	private static final Logger LOGGER = LogManager.getLogger(PropertyManager.class);
	
	private static PropertyManager propertyManager;
	private Properties properties;
	
	private PropertyManager() {
		properties = new Properties();
		File file = new File("properties.txt");
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally{
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
			
		}

	}
	
	public static PropertyManager getInstance  (){
		if (propertyManager == null) {
			propertyManager = new PropertyManager();
		}
		return propertyManager;	
	}
	
	public String getDataFileName() {
		String fileName = properties.getProperty("dataFileName");
		return fileName == null ? "file.txt" : fileName;
	}
	
	public String getConfigFileName(){
		String fileName = properties.getProperty("DIMConfigFileName");
		return fileName == null ? "DIMconfig.txt" : fileName;
	}
	
	public int getMaxStudentsCount(){
		String number = properties.getProperty("maxStudentsAtDay");
		int value = 0;
		try{
			value = Integer.parseInt(number);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return value;
	}
	
	public String getDriverName() {
		return properties.getProperty("driver");
	}
	
	public String getURL() {
		return properties.getProperty("url");
	}
	
	public String getUserName() {
		return properties.getProperty("userName");
	}
	
	public String getUserPassword() {
		return properties.getProperty("userPassword");
	}
}
