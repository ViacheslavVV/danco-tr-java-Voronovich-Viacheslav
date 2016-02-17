package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Course;

public class CurrentCoursesSortedByStartDateItem extends MenuItem {

	private static final String GET_CURRENT_COURSES_SORTED_BY_START_DATE = "getCurrentCoursesSortedByStartDate";
	private static final Logger LOGGER = LogManager.getLogger(CurrentCoursesSortedByStartDateItem.class);

	public CurrentCoursesSortedByStartDateItem(Menu menu, IMessageHandler messageHandler) {
		super("Get current courses sorted by start date.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Message message = new Message(GET_CURRENT_COURSES_SORTED_BY_START_DATE, null);
			@SuppressWarnings("unchecked")
			List<Course> courses = (List<Course>) messageHandler.sendMessage(message);
			if (courses == null || courses.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Courses not found.");
			} else {
				ConsoleEntityDisplayer.displayCourses(courses, "Current Courses sorted by start date");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
