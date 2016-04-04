package com.training.danco.ui.menu.item.ext;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class AddLectionToCourseItem extends MenuItem {

	private static final String ADD_LECTION_TO_COURSE = "addLectionToCourse";
	private static final Logger LOGGER = LogManager.getLogger(AddLectionToCourseItem.class);

	public AddLectionToCourseItem(Menu menu, IMessageHandler messageHandler) {
		super("Add lection to course.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			int lectionId = ConsoleReader.getLectionId();
			Message message = new Message(ADD_LECTION_TO_COURSE, new ArrayList<Object>(Arrays.asList(courseId, lectionId)));
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("The lection has been added.");
			} else {
				ConsoleEntityDisplayer.displayMessage("The lection hasn't been added.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
