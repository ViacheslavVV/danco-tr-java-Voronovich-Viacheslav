package com.training.danco.data.manager.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.data.manager.api.ISerializer;

public class DataSerializer implements ISerializer{
	
	private static final Logger LOGGER = LogManager.getLogger(DataSerializer.class);
	private final String FILE_NAME; 
	
	public DataSerializer(String fileName) {
		super();
		this.FILE_NAME = fileName;
	}
		
	@Override
	public boolean saveData(Object obj)
	{
		boolean result = true;
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		
		try {
			fileOutputStream = new FileOutputStream(FILE_NAME,false);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(obj);
		} catch (Exception e) {
			result = false;
			LOGGER.error(e.getMessage());
		} finally {
			try {
				objectOutputStream.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}
}