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
import com.training.danco.model.Lecturer;

public class LecturerByCourseItem extends MenuItem {

	private static final String GET_LECTURER_BY_COURSE = "getLecturerByCourse";
	private static final String GET_COURSE = "getCourse";
	private static final Logger LOGGER = LogManager.getLogger(LecturerByCourseItem.class);

	public LecturerByCourseItem(Menu menu, IMessageHandler messageHandler) {
		super("Get lecturer by course", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			Message message = new Message(GET_COURSE, courseId);
			Course course = (Course) messageHandler.sendMessage(message);
			message.setText(GET_LECTURER_BY_COURSE);
			Lecturer lecturer = (Lecturer) messageHandler.sendMessage(message);
			if (lecturer == null) {
				ConsoleEntityDisplayer.displayMessage("Lecturer not found.");
			} else {
				ConsoleEntityDisplayer.displayLecturer(lecturer, "Lecturer of course \"" + course.getName() + "\"");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
