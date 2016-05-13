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

public class SetLecturerToCourseItem extends MenuItem {

	private static final String SET_LECTURER_TO_COURSE = "setLecturerToCourse";
	private static final Logger LOGGER = LogManager.getLogger(SetLecturerToCourseItem.class);

	public SetLecturerToCourseItem(Menu menu, IMessageHandler messageHandler) {
		super("Set lecturer to course.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Integer courseId = ConsoleReader.getCourseId();
			Integer lecturerId = ConsoleReader.getLecturerId();

			Message message = new Message(SET_LECTURER_TO_COURSE, new ArrayList<Object>(Arrays.asList(courseId, lecturerId)));
			Boolean result = (Boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("The lecturer has been apppointed.");
			} else {
				ConsoleEntityDisplayer.displayMessage("The lecturer hasn't been apppointed.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
