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

public class AllCoursesItem extends MenuItem {

	private static final String GET_ALL_COURSES = "getAllCourses";
	private static final Logger LOGGER = LogManager.getLogger(AllCoursesItem.class);

	public AllCoursesItem(Menu menu, IMessageHandler messageHandler) {
		super("Get all courses.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Message message = new Message(GET_ALL_COURSES, null);
			@SuppressWarnings("unchecked")
			List<Course> courses = (List<Course>) messageHandler.sendMessage(message);
			if (courses == null || courses.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Courses not found.");
			} else {
				ConsoleEntityDisplayer.displayCourses(courses, "All courses");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
