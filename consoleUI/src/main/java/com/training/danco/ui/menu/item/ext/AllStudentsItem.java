package com.training.danco.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Student;

public class AllStudentsItem extends MenuItem {

	private static final String GET_ALL_STUDENTS = "getAllStudents";
	private static final Logger LOGGER = LogManager.getLogger(AllStudentsItem.class);

	public AllStudentsItem(Menu menu, IMessageHandler messageHandler) {
		super("Get all students.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Message message = new Message(GET_ALL_STUDENTS, null);
			@SuppressWarnings("unchecked")
			List<Student> students = (List<Student>) messageHandler.sendMessage(message);
			if (students == null || students.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Students not found.");
			} else {
				ConsoleEntityDisplayer.displayStudents(students, "All students");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
