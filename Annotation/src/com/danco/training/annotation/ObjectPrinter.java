package com.danco.training.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ObjectPrinter {

	private static final Logger LOGGER = LogManager.getLogger(ObjectPrinter.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void printObject(Object object) {

		Class classObject = object.getClass();
		try {
			if (!classObject.isAnnotationPresent(PrintableObject.class)) {
				return;
			}
			
			PrintableObject ann = object.getClass().getAnnotation(PrintableObject.class);
			System.out.println("Class : "+ann.name());
			
			Field[] fields = classObject.getDeclaredFields();

			List<FieldOrderItem> list = new ArrayList<>();
			
			for (Field field : fields) {
				if (field.isAnnotationPresent(Printable.class)) {
					list.add(new FieldOrderItem(field, field.getAnnotation(Printable.class).order()));
				} else if (field.isAnnotationPresent(PrintableRef.class)){
					list.add( new FieldOrderItem(field, field.getAnnotation(PrintableRef.class).order()));
				}
			}

			Comparator comparator = new Comparator<FieldOrderItem>(){

				@Override
				public int compare(FieldOrderItem o1, FieldOrderItem o2) {
					
					return Integer.compare(o1.getOrder(), o2.getOrder());
				}
				
			};
			
			Collections.sort(list,comparator);
			
			for (FieldOrderItem fieldOrder : list) {
				Field field = fieldOrder.getField();
				boolean isAccesible = field.isAccessible();
				try {

					if (!isAccesible) {
						field.setAccessible(true);
					}

					if (field.isAnnotationPresent(Printable.class)) {

						Printable annotation = field.getAnnotation(Printable.class);

						String fieldName = annotation.name();
						if (fieldName == null || fieldName.isEmpty()) {
							fieldName = field.getName();
						}

						System.out.print(fieldName+" : "+getValueAsString(field, field.getType(), object));
						
						if (!annotation.isDetailedOnly()) {
							System.out.println(", Type : "+field.getType().getSimpleName());
						}
					} else {

						PrintableRef annotation = field.getAnnotation(PrintableRef.class);
						
						String fieldName = annotation.name();
						if (fieldName == null || fieldName.isEmpty()) {
							fieldName = field.getName();
						}
						
						System.out.println(fieldName+", Type : "+field.getType().getSimpleName());
					// start here
						annotation.isRecursive();
					}

				} catch (Exception e) {
					if (!isAccesible) {
						field.setAccessible(false);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	public static String getValueAsString(Field field, Type type, Object source){
		
		String result = "Null";
		
		try {
			Object value = field.get(source);
			result = value.toString();
 		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} 
		return result;
	}
}
