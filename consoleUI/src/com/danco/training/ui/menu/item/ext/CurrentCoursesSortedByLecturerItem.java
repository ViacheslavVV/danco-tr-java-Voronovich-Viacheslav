package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class CurrentCoursesSortedByLecturerItem extends MenuItem {

	public CurrentCoursesSortedByLecturerItem(Menu menu) {
		super("Get current courses sorted by lecturer.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			Course[] courses = facade.getCurrentCoursesSortedByLecturer();
			if (courses == null){
				entityDisplayer.displayMessage("Courses not found.");
			}else if (courses.length==0){
				entityDisplayer.displayMessage("Courses not found.");
			}
			else{
				entityDisplayer.displayCourses(courses, "Current Courses sorted by lecturer");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
