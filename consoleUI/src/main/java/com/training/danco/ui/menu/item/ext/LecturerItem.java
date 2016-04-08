package com.training.danco.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Lecturer;

public class LecturerItem extends MenuItem {

	private static final String GET_LECTURER = "getLecturer";
	private static final Logger LOGGER = LogManager.getLogger(LecturerItem.class);

	public LecturerItem(Menu menu, IMessageHandler messageHandler) {
		super("Get lecturer.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Integer lecturerId = ConsoleReader.getLecturerId();
			Message message = new Message(GET_LECTURER,lecturerId);
			Lecturer lecturer = (Lecturer) messageHandler.sendMessage(message);
			if (lecturer == null) {
				ConsoleEntityDisplayer.displayMessage("Lecturer wasn't created.");
			} else {
				ConsoleEntityDisplayer.displayLecturer(lecturer, "Lecturer");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
