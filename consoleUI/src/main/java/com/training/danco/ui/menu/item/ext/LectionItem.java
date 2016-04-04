package com.training.danco.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Lection;

public class LectionItem extends MenuItem {

	private static final String GET_LECTION = "getLection";
	private static final Logger LOGGER = LogManager.getLogger(LectionItem.class);

	public LectionItem(Menu menu, IMessageHandler messageHandler) {
		super("Get lection.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			int lectionId = ConsoleReader.getLectionId();
			Message message = new Message(GET_LECTION, lectionId);
			Lection lection = (Lection) messageHandler.sendMessage(message);
			if (lection == null) {
				ConsoleEntityDisplayer.displayMessage("Lection wasn't created.");
			} else {
				ConsoleEntityDisplayer.displayLection(lection, "Lection");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
