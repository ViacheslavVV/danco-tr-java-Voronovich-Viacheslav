package com.training.danco.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Lection;

public class LectionsSortedByNameItem extends MenuItem {

	private static final String GET_LECTIONS_SORTED_BY_NAME = "getLectionsSortedByName";
	private static final Logger LOGGER = LogManager.getLogger(LectionsSortedByNameItem.class);

	public LectionsSortedByNameItem(Menu menu, IMessageHandler messageHandler) {
		super("Get lections sorted by name.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Message message = new Message(GET_LECTIONS_SORTED_BY_NAME, null);
			@SuppressWarnings("unchecked")
			List<Lection> lections = (List<Lection>) messageHandler.sendMessage(message);
			if (lections == null || lections.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Lections not found.");
			} else {
				ConsoleEntityDisplayer.displayLections(lections, "Lections sorted by name");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
