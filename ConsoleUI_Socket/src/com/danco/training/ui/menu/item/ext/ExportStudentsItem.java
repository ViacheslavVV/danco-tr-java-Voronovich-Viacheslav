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

public class ExportStudentsItem extends MenuItem {

	private static final String EXPORT_STUDENTS = "exportStudents";
	private static final Logger LOGGER = LogManager.getLogger(ExportStudentsItem.class);

	public ExportStudentsItem(Menu menu, IMessageHandler messageHandler) {
		super("Export students", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			String fileName = ConsoleReader.getExportFileName();
			List<Object> ids = ConsoleReader.getStudentIds();
			Message message = new Message(EXPORT_STUDENTS, new ArrayList<Object>(Arrays.asList(fileName, ids)));
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Students has been exported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Students hasn't been exported.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
