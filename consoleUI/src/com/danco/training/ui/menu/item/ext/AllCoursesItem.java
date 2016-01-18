package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class AllCoursesItem extends MenuItem {

	public AllCoursesItem( Menu menu) {
		super("Get all courses.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			Course[] courses = facade.getAllCourses();
			if (courses == null){
				entityDisplayer.displayMessage("Courses not found.");
			}else if (courses.length==0){
				entityDisplayer.displayMessage("Courses not found.");
			}
			else{
				entityDisplayer.displayCourses(courses, "All courses");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

	
}
