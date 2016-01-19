package com.danco.training.ui.menu.item.ext;

import java.util.List;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class CurrentCoursesSortedByStudentsCountItem extends MenuItem {

	public CurrentCoursesSortedByStudentsCountItem(Menu menu) {
		super("Get current courses sorted by number of students.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			List<Course> courses = facade.getCurrentCoursesSortedByStudentsCount();
			if (courses == null){
				entityDisplayer.displayMessage("Courses not found.");
			}else if (courses.size()==0){
				entityDisplayer.displayMessage("Courses not found.");
			}
			else{
				entityDisplayer.displayCourses(courses, "Current Courses sorted by number of students");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
