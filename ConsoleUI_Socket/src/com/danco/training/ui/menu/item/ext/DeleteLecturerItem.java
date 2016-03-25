package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class DeleteLecturerItem extends MenuItem {

	private static final String DELETE_LECTURER = "deleteLecturer";
	private static final Logger LOGGER = LogManager.getLogger(DeleteLecturerItem.class);

	public DeleteLecturerItem(Menu menu, IMessageHandler messageHandler) {
		super("Delete lecturer.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			int lecturerId = ConsoleReader.getLecturerId();
			Message message = new Message(DELETE_LECTURER, lecturerId);
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Lecturer has been deleted.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Lecturer hasn't been deleted.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
