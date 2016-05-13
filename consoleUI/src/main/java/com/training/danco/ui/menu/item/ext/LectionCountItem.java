package com.training.danco.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class LectionCountItem extends MenuItem {

	private static final String GET_LECTIONS_COUNT = "getLectionsCount";
	private static final Logger LOGGER = LogManager.getLogger(LectionCountItem.class);

	public LectionCountItem(Menu menu, IMessageHandler messageHandler) {
		super("Get number of lections.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Message message = new Message(GET_LECTIONS_COUNT, null);
			Integer count = (Integer) messageHandler.sendMessage(message);
			ConsoleEntityDisplayer.displayMessage("Number of lections : " + count);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
