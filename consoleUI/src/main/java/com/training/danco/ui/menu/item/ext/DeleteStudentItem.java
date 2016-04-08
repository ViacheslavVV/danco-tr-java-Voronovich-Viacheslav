package com.training.danco.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class DeleteStudentItem extends MenuItem {

	private static final String DELETE_STUDENT = "deleteStudent";
	private static final Logger LOGGER = LogManager.getLogger(DeleteStudentItem.class);

	public DeleteStudentItem(Menu menu, IMessageHandler messageHandler) {
		super("Delete student.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Integer studentId = ConsoleReader.getStudentId();
			Message message = new Message(DELETE_STUDENT, studentId);
			Boolean result = (Boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Student has been deleted.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Student hasn't been deleted.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}
}
