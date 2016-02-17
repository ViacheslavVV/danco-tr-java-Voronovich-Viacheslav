package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Lecturer;

public class AllLecturersItem extends MenuItem {

	private static final String GET_ALL_LECTURERS = "getAllLecturers";
	private static final Logger LOGGER = LogManager.getLogger(AllLecturersItem.class);

	public AllLecturersItem(Menu menu, IMessageHandler messageHandler) {
		super("Get all lecturers.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Message message = new Message(GET_ALL_LECTURERS, null);
			@SuppressWarnings("unchecked")
			List<Lecturer> lecturers = (List<Lecturer>) messageHandler.sendMessage(message);
			if (lecturers == null || lecturers.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Lecturers not found.");
			} else {
				ConsoleEntityDisplayer.displayLecturers(lecturers, "All lecturers");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
