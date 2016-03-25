package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Student;

public class StudentItem extends MenuItem {

	private static final String GET_STUDENT = "getStudent";
	private static final Logger LOGGER = LogManager.getLogger(StudentItem.class);

	public StudentItem(Menu menu, IMessageHandler messageHandler) {
		super("Get student.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			int studentId = ConsoleReader.getStudentId();
			Message message = new Message(GET_STUDENT, studentId);
			Student student = (Student) messageHandler.sendMessage(message);
			if (student == null) {
				ConsoleEntityDisplayer.displayMessage("Student wasn't created.");
			} else {
				ConsoleEntityDisplayer.displayStudent(student, "Student");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
