package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Student;

public class NewStudentItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(NewStudentItem.class);

	public NewStudentItem(Menu menu) {
		super("Create student.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {

		try{
			String name = reader.getStudentName();
			int age = reader.getStudentAge();
			if (facade.setStudent(new Student(name, age))){
				entityDisplayer.displayMessage("Student has been created.");
			}else {
				entityDisplayer.displayMessage("Student hasn't been created.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		return this.menu;
	}

}
