package com.training.danco.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Course;

public class CoursesSortedByLecturerItem extends MenuItem {

	private static final String GET_COURSES_SORTED_BY_LECTURER = "getCoursesSortedByLecturer";
	private static final Logger LOGGER = LogManager.getLogger(CoursesSortedByLecturerItem.class);

	public CoursesSortedByLecturerItem(Menu menu, IMessageHandler messageHandler) {
		super("Get courses sorted by lecturer.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Message message = new Message(GET_COURSES_SORTED_BY_LECTURER, null);
			@SuppressWarnings("unchecked")
			List<Course> courses = (List<Course>) messageHandler.sendMessage(message);
			if (courses == null || courses.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Courses not found.");
			} else {
				ConsoleEntityDisplayer.displayCourses(courses, "Courses sorted by lecturer");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
