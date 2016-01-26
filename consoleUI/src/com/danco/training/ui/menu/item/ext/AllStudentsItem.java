package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Student;

public class AllStudentsItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(AllStudentsItem.class);

	public AllStudentsItem( Menu menu, IFacade facade) {
		super("Get all students.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			List<Student> students = facade.getAllStudents();
			if (students == null || students.size()==0){
				ConsoleEntityDisplayer.displayMessage("Students not found.");
			}
			else{
				ConsoleEntityDisplayer.displayStudents(students, "All students");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
