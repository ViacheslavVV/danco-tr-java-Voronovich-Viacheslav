package com.danco.training.dim;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.dim.configuration.manager.DIMConfigManager;

public class DependencyInjectionManager {
	
	private static final Logger LOGGER = LogManager.getLogger(DependencyInjectionManager.class);

	private static Map<String, Object> objects = new HashMap<String, Object>();
	
	public static Object getClassInstance(Class<?> cls){
		Object obj = null;
		if (objects.containsKey(cls.getName())){
			obj = objects.get(cls.getName());
		} else {
			String implClassName = DIMConfigManager.getInstance().getImplClassName(cls.getName());
			try {
				Class<?> implClass = Class.forName(implClassName);
				obj = implClass.newInstance();
				addObjectToMap(implClassName, obj);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return obj;
	}
	
	private static void addObjectToMap(String implClassName, Object obj){
		try{
			objects.put(implClassName, obj);
		} catch (Exception e){
			LOGGER.error(e.getMessage());
		}
	}
	
}
