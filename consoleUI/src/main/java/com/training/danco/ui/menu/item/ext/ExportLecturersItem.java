package com.training.danco.ui.menu.item.ext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class ExportLecturersItem extends MenuItem {

	private static final String EXPORT_LECTURERS = "exportLecturers";
	private static final Logger LOGGER = LogManager.getLogger(ExportLecturersItem.class);

	public ExportLecturersItem(Menu menu, IMessageHandler messageHandler) {
		super("Export lecturers", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			String fileName = ConsoleReader.getExportFileName();
			List<Object> ids = ConsoleReader.getLecturerIds();
			Message message = new Message(EXPORT_LECTURERS, new ArrayList<Object>(Arrays.asList(fileName, ids)));
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Lecturers has been exported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Lecturers hasn't been exported.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
