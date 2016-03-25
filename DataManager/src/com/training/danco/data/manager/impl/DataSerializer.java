package com.training.danco.data.manager.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.configuration.manager.PropertyManager;
import com.training.danco.data.manager.api.ISerializer;

public class DataSerializer implements ISerializer{
	
	private static final Logger LOGGER = LogManager.getLogger(DataSerializer.class);
		
	@Override
	public boolean saveData(Object obj)
	{
		boolean result = true;
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		
		try {
			fileOutputStream = new FileOutputStream(PropertyManager.getInstance().getDataFileName(),false);
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