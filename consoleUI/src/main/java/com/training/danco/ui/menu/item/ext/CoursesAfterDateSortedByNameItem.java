package com.training.danco.ui.menu.item.ext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Course;

public class CoursesAfterDateSortedByNameItem extends MenuItem {

	private static final String GET_COURSES_AFTER_DATE_SORTED_BY_NAME = "getCoursesAfterDateSortedByName";
	private static final Logger LOGGER = LogManager.getLogger(CoursesAfterDateSortedByNameItem.class);
	private static final DateFormat FORMATTER = new SimpleDateFormat(("dd.MM.yyyy"));

	public CoursesAfterDateSortedByNameItem(Menu menu, IMessageHandler messageHandler) {
		super("Get courses after date, sorted by name.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Date date = ConsoleReader.getDate("Input date");
			Message message = new Message(GET_COURSES_AFTER_DATE_SORTED_BY_NAME, date);
			@SuppressWarnings("unchecked")
			List<Course> courses = (List<Course>) messageHandler.sendMessage(message);
			if (courses == null || courses.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Courses not found.");
			} else {
				ConsoleEntityDisplayer.displayCourses(courses, "Courses after date " + FORMATTER.format(date) + " sorted by name");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
