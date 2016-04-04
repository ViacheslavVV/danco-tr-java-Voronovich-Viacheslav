package com.training.danco.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Lecturer;

public class LecturersSortedByCoursesCountItem extends MenuItem {

	private static final String GET_LECTURERS_SORTED_BY_COURSES_COUNT = "getLecturersSortedByCoursesCount";
	private static final Logger LOGGER = LogManager.getLogger(LecturersSortedByCoursesCountItem.class);

	public LecturersSortedByCoursesCountItem(Menu menu, IMessageHandler messageHandler) {
		super("Get lecturers sorted by number of courses.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {

			Message message = new Message(GET_LECTURERS_SORTED_BY_COURSES_COUNT, null);
			@SuppressWarnings("unchecked")
			List<Lecturer> lecturers = (List<Lecturer>) messageHandler.sendMessage(message);
			if (lecturers == null || lecturers.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Lecturers not found.");
			} else {
				ConsoleEntityDisplayer.displayLecturers(lecturers, "Lecturers sorted by number of courses");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}
}
