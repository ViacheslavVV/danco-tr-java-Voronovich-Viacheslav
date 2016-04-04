package com.training.danco.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class CoursesCountItem extends MenuItem {

	private static final String GET_COURSES_COUNT = "getCoursesCount";
	private static final Logger LOGGER = LogManager.getLogger(CoursesCountItem.class);

	public CoursesCountItem(Menu menu, IMessageHandler messageHandler) {
		super("Get number of courses.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Message message = new Message(GET_COURSES_COUNT, null);
			int count = (int) messageHandler.sendMessage(message);
			ConsoleEntityDisplayer.displayMessage("Number of courses : " + count);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
