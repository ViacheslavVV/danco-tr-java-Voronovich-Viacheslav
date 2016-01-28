package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Student;

public class StudentItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(StudentItem.class);

	public StudentItem(Menu menu, IFacade facade) {
		super("Get student.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			int studentId = ConsoleReader.getStudentId();
			Student student = facade.getStudent(studentId);
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
