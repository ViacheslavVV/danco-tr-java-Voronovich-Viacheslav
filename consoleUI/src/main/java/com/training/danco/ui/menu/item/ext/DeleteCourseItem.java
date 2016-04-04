package com.training.danco.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class DeleteCourseItem extends MenuItem {

	private static final String DELETE_COURSE = "deleteCourse";
	private static final Logger LOGGER = LogManager.getLogger(DeleteCourseItem.class);

	public DeleteCourseItem(Menu menu, IMessageHandler messageHandler) {
		super("Delete course.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			Message message = new Message(DELETE_COURSE, courseId);
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Course has been deleted.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Course hasn't been deleted.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
