package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Student;

public class StudentItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(StudentItem.class);

	public StudentItem(Menu menu) {
		super("Get student.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int studentId = reader.getStudentId();
			Student student = facade.getStudent(studentId);
			if (student == null){
				entityDisplayer.displayMessage("Student wasn't created.");
			}else {
				entityDisplayer.displayStudent(student, "Student");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
