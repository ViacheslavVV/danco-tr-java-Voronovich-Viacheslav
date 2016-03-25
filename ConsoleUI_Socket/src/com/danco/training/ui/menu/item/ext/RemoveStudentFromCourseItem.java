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

public class RemoveStudentFromCourseItem extends MenuItem {

	private static final String REMOVE_STUDENT_FROM_COURSE = "removeStudentFromCourse";
	private static final Logger LOGGER = LogManager.getLogger(RemoveStudentFromCourseItem.class);

	public RemoveStudentFromCourseItem(Menu menu, IMessageHandler messageHandler) {
		super("Remove student from course.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			int studentId = ConsoleReader.getStudentId();
			Message message = new Message(REMOVE_STUDENT_FROM_COURSE,
					new ArrayList<Object>(Arrays.asList(courseId, studentId)));
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result) {
				ConsoleEntityDisplayer.displayMessage("The student has been deleted.");
			} else {
				ConsoleEntityDisplayer.displayMessage("The student hasn't been deleted.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
