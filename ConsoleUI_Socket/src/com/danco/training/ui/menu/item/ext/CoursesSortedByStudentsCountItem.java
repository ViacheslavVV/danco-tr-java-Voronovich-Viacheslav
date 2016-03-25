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

public class CoursesSortedByStudentsCountItem extends MenuItem {

	private static final String GET_COURSES_SORTED_BY_STUDENTS_COUNT = "getCoursesSortedByStudentsCount";
	private static final Logger LOGGER = LogManager.getLogger(CoursesSortedByStudentsCountItem.class);

	public CoursesSortedByStudentsCountItem(Menu menu, IMessageHandler messageHandler) {
		super("Get courses sorted by number of students.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Message message = new Message(GET_COURSES_SORTED_BY_STUDENTS_COUNT, null);
			@SuppressWarnings("unchecked")
			List<Course> courses = (List<Course>) messageHandler.sendMessage(message);
			if (courses == null || courses.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Courses not found.");
			} else {
				ConsoleEntityDisplayer.displayCourses(courses, "Courses sorted by number of students");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
