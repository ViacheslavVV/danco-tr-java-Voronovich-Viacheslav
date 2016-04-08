package com.training.danco.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class ImportLecturersItem extends MenuItem {

	private static final String IMPORT_LECTURERS = "importLecturers";
	private static final Logger LOGGER = LogManager.getLogger(ImportLecturersItem.class);

	public ImportLecturersItem(Menu menu, IMessageHandler messageHandler) {
		super("Import lecturers", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			String fileName = ConsoleReader.getImportFileName();
			if (fileName == null) {
				return this.menu;
			}
			Message message = new Message(IMPORT_LECTURERS, fileName);
			Boolean result = (Boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Lecturers has been imported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Lecturers hasn't been imported.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
