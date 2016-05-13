package com.training.danco.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.training.danco.annotation.string.Constants.*;

public class PrintableObjectHandler {

	private static final Logger LOGGER = LogManager.getLogger(PrintableObjectHandler.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String printObject(Object object, Boolean isDetailed) {

		StringBuilder sb = new StringBuilder();

		Class classObject = object.getClass();
		try {
			if (!classObject.isAnnotationPresent(PrintableObject.class)) {
				return sb.toString();
			}

			PrintableObject ann = object.getClass().getAnnotation(PrintableObject.class);
			String objectName = ann.name();
			sb.append(CLASS_DECLARATION).append(objectName).append(NEW_LINE);

			Field[] fields = classObject.getDeclaredFields();

			List<FieldOrderItem> list = new ArrayList<FieldOrderItem>();

			for (Field field : fields) {
				if (field.isAnnotationPresent(Printable.class)) {
					list.add(new FieldOrderItem(field, field.getAnnotation(Printable.class).order()));
				} else if (field.isAnnotationPresent(PrintableRef.class)) {
					list.add(new FieldOrderItem(field, field.getAnnotation(PrintableRef.class).order()));
				}
			}

			Comparator comparator = new Comparator<FieldOrderItem>() {

				public int compare(FieldOrderItem o1, FieldOrderItem o2) {

					return Integer.compare(o1.getOrder(), o2.getOrder());
				}

			};

			Collections.sort(list, comparator);

			for (FieldOrderItem fieldOrder : list) {
				Field field = fieldOrder.getField();
				Boolean isAccesible = field.isAccessible();
				try {

					if (!isAccesible) {
						field.setAccessible(true);
					}
					if (field.isAnnotationPresent(Printable.class)) {

						Printable annotation = field.getAnnotation(Printable.class);

						if (annotation.isDetailedOnly() && !isDetailed) {
							continue;
						}

						sb.append(FIELD_DECLARATION);

						String fieldName = getCorrectFieldName(field, annotation.name());

						sb.append(fieldName + EQUAL_SIGN + getValueAsString(field, object));
						sb.append(NEW_LINE);

					} else {

						PrintableRef annotation = field.getAnnotation(PrintableRef.class);
						if (annotation.isDetailedView() && !isDetailed) {
							continue;
						}
						sb.append(FIELD_DECLARATION);

						String fieldName = getCorrectFieldName(field, annotation.name());

						sb.append(fieldName);

						sb.append(NEW_LINE);
						if (Collection.class.isAssignableFrom(field.getType())) {
							Object fieldValue = field.get(object);
							if (fieldValue == null) {
								continue;
							}
							appendHeader(sb, fieldName, true);
							Collection<Object> innerObjects = (Collection<Object>) fieldValue;
							Integer num = 1;
							for (Object obj : innerObjects) {
								sb.append("Object " + num++);
								sb.append(NEW_LINE);
								sb.append(printObject(obj, false));
							}

							appendFooter(sb);
						} else if (isDetailed) {
							appendHeader(sb, fieldName, false);
							sb.append(printObject(field.get(object), true));
							appendFooter(sb);
						}
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
		return sb.toString();
	}

	private static void appendHeader(StringBuilder sb, String fieldName, Boolean isCollection) {
		sb.append(OBJECT_AREA);
		sb.append(NEW_LINE);
		sb.append("field " + fieldName + " is " + (isCollection ? "collection" : "referenced object") + ".");
		sb.append(NEW_LINE);
	}

	private static void appendFooter(StringBuilder sb) {
		sb.append(OBJECT_AREA);
		sb.append(NEW_LINE);
	}

	private static String getCorrectFieldName(Field field, String fieldName) {
		if (fieldName == null || fieldName.isEmpty()) {
			fieldName = field.getName();
		}
		return fieldName;
	}

	@SuppressWarnings("deprecation")
	private static String getValueAsString(Field field, Object source) {

		String result = "Null";

		try {
			Object value = field.get(source);
			if (field.getType().getName() == DATE_TYPE) {
				Date date = (Date) value;
				result = date.getDay() + DATE_DELIMITER + date.getMonth() + DATE_DELIMITER + date.getYear() + SPACE
						+ date.getHours() + TIME_DELIMITER + date.getMinutes();
			} else {
				result = value.toString();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}
}
