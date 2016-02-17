package com.danco.training.ui.menu.item.ext;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class AddStudentToCourseItem extends MenuItem {

	private static final String ADD_STUDENT_TO_COURSE = "addStudentToCourse";
	private static final Logger LOGGER = LogManager.getLogger(AddStudentToCourseItem.class);

	public AddStudentToCourseItem(Menu menu, IMessageHandler messageHandler) {
		super("Add student to course.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			int studentId = ConsoleReader.getStudentId();
			Message message = new Message(ADD_STUDENT_TO_COURSE, new ArrayList<Object>(Arrays.asList(courseId, studentId)));
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("The student has been added.");
			} else {
				ConsoleEntityDisplayer.displayMessage("The student hasn't been added.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
