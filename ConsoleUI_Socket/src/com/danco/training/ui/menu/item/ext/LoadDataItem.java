package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class LoadDataItem extends MenuItem {

	private static final String LOAD_DATA_FROM_FILE = "loadDataFromFile";
	private static final Logger LOGGER = LogManager.getLogger(LoadDataItem.class);

	public LoadDataItem(Menu menu, IMessageHandler messageHandler) {
		super("Load data from file.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Message message = new Message(LOAD_DATA_FROM_FILE,null);
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Data has been loaded from file succesfully!");
			} else {
				ConsoleEntityDisplayer.displayMessage("Data hasn't been loaded from file!");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Something wrong!");
		}
		return this.menu;
	}

}
