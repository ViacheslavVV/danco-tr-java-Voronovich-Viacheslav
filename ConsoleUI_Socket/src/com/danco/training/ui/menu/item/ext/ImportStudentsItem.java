package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class ImportStudentsItem extends MenuItem {

	private static final String IMPORT_STUDENTS = "importStudents";
	private static final Logger LOGGER = LogManager.getLogger(ImportStudentsItem.class);

	public ImportStudentsItem(Menu menu, IMessageHandler messageHandler) {
		super("Import students", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			String fileName = ConsoleReader.getImportFileName();
			if (fileName == null) {
				return this.menu;
			}
			Message message = new Message(IMPORT_STUDENTS, fileName);
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Students has been imported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Students hasn't been imported.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
