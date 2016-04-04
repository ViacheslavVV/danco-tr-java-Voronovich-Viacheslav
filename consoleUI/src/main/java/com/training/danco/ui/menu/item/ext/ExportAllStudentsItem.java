package com.training.danco.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class ExportAllStudentsItem extends MenuItem {

	private static final String EXPORT_ALL_STUDENTS = "exportAllStudents";
	private static final Logger LOGGER = LogManager.getLogger(ExportAllStudentsItem.class);

	public ExportAllStudentsItem(Menu menu, IMessageHandler messageHandler) {
		super("Export all students", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			String fileName = ConsoleReader.getExportFileName();
			Message message = new Message(EXPORT_ALL_STUDENTS, fileName);
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Students has been exported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Students hasn't been exported.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
