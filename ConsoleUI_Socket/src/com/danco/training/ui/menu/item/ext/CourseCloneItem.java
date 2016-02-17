package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class CourseCloneItem extends MenuItem {

	private static final String CLONE_COURSE = "cloneCourse";
	private static final Logger LOGGER = LogManager.getLogger(CourseCloneItem.class);

	public CourseCloneItem(Menu menu, IMessageHandler messageHandler) {
		super("Clone course", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			Message message = new Message(CLONE_COURSE, courseId);
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("Course has been cloned.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Course hasn't been cloned.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
