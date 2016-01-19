package com.danco.training.ui.menu.item.ext;

import java.util.List;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Student;

public class AllStudentsItem extends MenuItem {

	public AllStudentsItem( Menu menu) {
		super("Get all students.", menu);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			List<Student> students = facade.getAllStudents();
			if (students == null){
				entityDisplayer.displayMessage("Students not found.");
			}else if (students.size()==0){
				entityDisplayer.displayMessage("Students not found.");
			}
			else{
				entityDisplayer.displayStudents(students, "All students");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
