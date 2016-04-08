package com.training.danco.ui.menu.item.ext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class NewLectionItem extends MenuItem {

	private static final String SET_LECTION = "setLection";
	private static final Logger LOGGER = LogManager.getLogger(NewLectionItem.class);

	public NewLectionItem(Menu menu, IMessageHandler messageHandler) {
		super("Create lection.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {

		try {
			String name = ConsoleReader.getLectionName();
			Date date = ConsoleReader.getLectionDate();
			Message message = new Message(SET_LECTION, new ArrayList<Object>(Arrays.asList(name, date)));
			Integer result = (Integer) messageHandler.sendMessage(message);
			if (result != null) {
				ConsoleEntityDisplayer.displayMessage("Lection has been created.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Lection hasn't been created.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		return this.menu;
	}

}
