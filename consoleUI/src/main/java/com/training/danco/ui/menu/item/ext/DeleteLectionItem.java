package com.training.danco.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class DeleteLectionItem extends MenuItem {

	private static final String DELETE_LECTION = "deleteLection";
	private static final Logger LOGGER = LogManager.getLogger(DeleteLectionItem.class);

	public DeleteLectionItem(Menu menu, IMessageHandler messageHandler) {
		super("Delete lection.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			int lectionId = ConsoleReader.getLectionId();
			Message message = new Message(DELETE_LECTION, lectionId);
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Lection has been deleted.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Lection hasn't been deleted.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
