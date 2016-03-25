package com.danco.training.ui.menu.item.ext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Course;

public class CoursesInIntervalItem extends MenuItem {

	private static final String GET_COURSES_IN_INTERVAL = "getCoursesInInterval";
	private static final Logger LOGGER = LogManager.getLogger(CoursesInIntervalItem.class);

	public CoursesInIntervalItem(Menu menu, IMessageHandler messageHandler) {
		super("Get courses in interval.", menu, messageHandler);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Menu doWork() {
		try {
			ConsoleEntityDisplayer.displayMessage("Enter date interval");
			Date startDate = ConsoleReader.getDate("Input date from");
			Date finalDate = ConsoleReader.getDate("Input date to");
			Message message = new Message(GET_COURSES_IN_INTERVAL, new ArrayList<Object>(Arrays.asList(startDate, finalDate)));
			@SuppressWarnings("unchecked")
			List<Course> courses = (List<Course>) messageHandler.sendMessage(message);
			if (courses == null || courses.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Courses not found.");
			} else {
				ConsoleEntityDisplayer.displayCourses(courses,
						"Courses in interval from " + startDate.getDay() + "-" + startDate.getMonth() + "-"
								+ startDate.getYear() + " to " + finalDate.getDay() + "-" + finalDate.getMonth() + "-"
								+ finalDate.getYear());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
