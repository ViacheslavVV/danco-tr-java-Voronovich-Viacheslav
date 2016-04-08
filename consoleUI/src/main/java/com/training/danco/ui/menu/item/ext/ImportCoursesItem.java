package com.training.danco.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class ImportCoursesItem extends MenuItem {

	private static final String IMPORT_COURSES = "importCourses";
	private static final Logger LOGGER = LogManager.getLogger(ImportCoursesItem.class);

	public ImportCoursesItem(Menu menu, IMessageHandler messageHandler) {
		super("Import courses", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			String fileName = ConsoleReader.getImportFileName();
			if (fileName == null) {
				return this.menu;
			}
			Message message = new Message(IMPORT_COURSES, fileName);
			Boolean result = (Boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Courses has been imported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Courses hasn't been imported.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
