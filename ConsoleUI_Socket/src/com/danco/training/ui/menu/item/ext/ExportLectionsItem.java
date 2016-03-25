package com.danco.training.ui.menu.item.ext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class ExportLectionsItem extends MenuItem {

	private static final String EXPORT_LECTIONS = "exportLections";
	private static final Logger LOGGER = LogManager.getLogger(ExportLectionsItem.class);

	public ExportLectionsItem(Menu menu, IMessageHandler messageHandler) {
		super("Export lections", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			String fileName = ConsoleReader.getExportFileName();
			List<Object> ids = ConsoleReader.getLectionIds();
			Message message = new Message(EXPORT_LECTIONS, new ArrayList<Object>(Arrays.asList(fileName, ids)));
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Lections has been exported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Lections hasn't been exported.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
