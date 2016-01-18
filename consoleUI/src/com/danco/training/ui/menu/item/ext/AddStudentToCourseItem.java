package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class AddStudentToCourseItem extends MenuItem {

	public AddStudentToCourseItem(Menu menu) {
		super("Add student to course.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int courseId = reader.getCourseId();
			int studentId = reader.getStudentId();
			if (facade.addStudentToCourse(courseId, studentId)){
				entityDisplayer.displayMessage("The student has been added.");
			} else {
				entityDisplayer.displayMessage("The student hasn't been added.");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

	

}
