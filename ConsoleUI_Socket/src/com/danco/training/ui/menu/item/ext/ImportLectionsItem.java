package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class ImportLectionsItem extends MenuItem {

	private static final String IMPORT_LECTIONS = "importLections";
	private static final Logger LOGGER = LogManager.getLogger(ImportLectionsItem.class);

	public ImportLectionsItem(Menu menu, IMessageHandler messageHandler) {
		super("Import lections", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			String fileName = ConsoleReader.getImportFileName();
			if (fileName == null) {
				return this.menu;
			}
			Message message = new Message(IMPORT_LECTIONS, fileName);
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Lections has been imported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Lections hasn't been imported.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
