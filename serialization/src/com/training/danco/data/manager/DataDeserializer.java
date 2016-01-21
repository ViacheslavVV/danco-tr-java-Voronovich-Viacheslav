package com.training.danco.data.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataDeserializer {
	
	private static final Logger LOGGER = LogManager.getLogger(DataDeserializer.class);
	private final String FILE_NAME;
	
	public DataDeserializer(String fileName) {
		this.FILE_NAME = fileName;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getDataObjects()
	{
		List<Object> data = new ArrayList<Object>();
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			fileInputStream = new FileInputStream(FILE_NAME);
			objectInputStream = new ObjectInputStream(fileInputStream);
			data = (List<Object>)objectInputStream.readObject();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (objectInputStream != null){
				try {
					objectInputStream.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
		}
		return data;
		
	}
}

