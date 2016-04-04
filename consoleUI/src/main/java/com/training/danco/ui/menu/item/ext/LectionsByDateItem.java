package com.training.danco.ui.menu.item.ext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Lection;

public class LectionsByDateItem extends MenuItem {

	private static final String GET_LECTIONS_BY_DATE = "getLectionsByDate";
	private static final Logger LOGGER = LogManager.getLogger(LectionsByDateItem.class);
	private static final DateFormat FORMATTER = new SimpleDateFormat(("dd.MM.yyyy"));
	
	public LectionsByDateItem(Menu menu, IMessageHandler messageHandler) {
		super("Get lections by date.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Date date = ConsoleReader.getLectionDate();
			Message message = new Message(GET_LECTIONS_BY_DATE, date);
			@SuppressWarnings("unchecked")
			List<Lection> lections = (List<Lection>) messageHandler.sendMessage(message);
			if (lections == null || lections.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Lections not found.");
			} else {
				ConsoleEntityDisplayer.displayLections(lections,
						"Lections by date " + FORMATTER.format(date));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
