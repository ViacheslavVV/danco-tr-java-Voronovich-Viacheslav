package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Student;

public class StudentItem extends MenuItem {

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
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
