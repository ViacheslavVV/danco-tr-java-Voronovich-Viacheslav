package com.training.danco.ui.menu.item.ext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class NewCourseItem extends MenuItem {

	private static final String SET_COURSE = "setCourse";
	private static final Logger LOGGER = LogManager.getLogger(NewCourseItem.class);

	public NewCourseItem(Menu menu, IMessageHandler messageHandler) {
		super("Create course.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {

		try {
			String name = ConsoleReader.getCourseName();
			Date startDate = ConsoleReader.getCourseStartDate();
			Date finalDate = ConsoleReader.getCourseFinalDate();
			int maxStudents = ConsoleReader.getMaxStudentsNumber();
			int maxLections = ConsoleReader.getMaxLectionsNumber();

			Message message = new Message(SET_COURSE,
					new ArrayList<Object>(Arrays.asList(name, startDate, finalDate, maxStudents, maxLections)));
			boolean result = (boolean) messageHandler.sendMessage(message);

			if (result) {
				ConsoleEntityDisplayer.displayMessage("Course has been created.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Course hasn't been created.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		return this.menu;
	}

}
