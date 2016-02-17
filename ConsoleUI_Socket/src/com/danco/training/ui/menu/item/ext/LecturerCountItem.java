package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class LecturerCountItem extends MenuItem {

	private static final String GET_LECTURERS_COUNT = "getLecturersCount";
	private static final Logger LOGGER = LogManager.getLogger(LecturerCountItem.class);

	public LecturerCountItem(Menu menu, IMessageHandler messageHandler) {
		super("Get number of lecturers.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Message message = new Message(GET_LECTURERS_COUNT, null);
			int count = (int) messageHandler.sendMessage(message);
			ConsoleEntityDisplayer.displayMessage("Number of lecturers : " + count);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
