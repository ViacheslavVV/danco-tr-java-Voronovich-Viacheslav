package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class SaveDataItem extends MenuItem {

	private static final String SAVE_DATA_TO_FILE = "saveDataToFile";
	private static final Logger LOGGER = LogManager.getLogger(SaveDataItem.class);
	public SaveDataItem(Menu menu, IMessageHandler messageHandler) {
		super("Save data to file.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Message message = new Message(SAVE_DATA_TO_FILE, null);
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result){
				ConsoleEntityDisplayer.displayMessage("Data has been saved to file succesfully!");
			} else {
				ConsoleEntityDisplayer.displayMessage("Data hasn't been saved to file!");
			}
			
		} catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Something wrong!");
		}
		return this.menu;
	}

}
