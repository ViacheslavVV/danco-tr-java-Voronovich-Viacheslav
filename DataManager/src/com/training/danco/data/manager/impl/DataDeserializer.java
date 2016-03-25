package com.training.danco.data.manager.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.configuration.manager.PropertyManager;
import com.training.danco.data.manager.api.IDeserializer;

public class DataDeserializer implements IDeserializer{
	
	private static final Logger LOGGER = LogManager.getLogger(DataDeserializer.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getDataObjects()
	{
		List<Object> data = new ArrayList<Object>();
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			fileInputStream = new FileInputStream(PropertyManager.getInstance().getDataFileName());
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

