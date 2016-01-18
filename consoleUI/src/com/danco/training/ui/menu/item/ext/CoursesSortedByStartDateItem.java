package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class CoursesSortedByStartDateItem extends MenuItem {

	public CoursesSortedByStartDateItem(Menu menu) {
		super("Get courses sorted by start date.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			Course[] courses = facade.getCoursesSortedByStartDate();
			if (courses == null){
				entityDisplayer.displayMessage("Courses not found.");
			}else if (courses.length==0){
				entityDisplayer.displayMessage("Courses not found.");
			}
			else{
				entityDisplayer.displayCourses(courses, "Courses sorted by start date");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
