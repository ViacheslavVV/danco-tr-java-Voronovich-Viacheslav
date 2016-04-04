package com.training.danco.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Course;

public class CourseItem extends MenuItem {

	private static final String GET_COURSE = "getCourse";
	private static final Logger LOGGER = LogManager.getLogger(CourseItem.class);

	public CourseItem(Menu menu, IMessageHandler messageHandler) {
		super("Get course.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			Message message = new Message(GET_COURSE, courseId);
			Course course = (Course) messageHandler.sendMessage(message);
			if (course == null) {
				ConsoleEntityDisplayer.displayMessage("Course wasn't created.");
			} else {
				ConsoleEntityDisplayer.displayCourse(course, "Course");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
