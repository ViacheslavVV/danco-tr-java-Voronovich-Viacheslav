package com.danco.training.configuration.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyManager {


	private static final Logger LOGGER = LogManager.getLogger(PropertyManager.class);
	
	private static PropertyManager propertyManger;
	private Properties properties;
	private FileInputStream fileInputStream;
	
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
		
		try {
			fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally{
			if (fileInputStream != null) {
			}
			try {
				fileInputStream.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}

	}
	
	public static PropertyManager getInstance  (){
		if (propertyManger == null) {
			propertyManger = new PropertyManager();
		}
		return propertyManger;	
	}
	
	
	public String getDataFileName() {
		String fileName = properties.getProperty("dataFileName");
		return fileName == null ? "file.txt" : fileName;
		
	}
}
