package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Student;

public class NewStudentItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(NewStudentItem.class);

	public NewStudentItem(Menu menu, IFacade facade) {
		super("Create student.", menu, facade);
	}

	@Override
	public Menu doWork() {

		try{
			String name = ConsoleReader.getStudentName();
			int age = ConsoleReader.getStudentAge();
			if (facade.setStudent(new Student(name, age))){
				ConsoleEntityDisplayer.displayMessage("Student has been created.");
			}else {
				ConsoleEntityDisplayer.displayMessage("Student hasn't been created.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		return this.menu;
	}

}
