package com.danco.training.ui.menu.item.ext;

import java.util.Date;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class CoursesAfterDateSortedByLecturerItem extends MenuItem {

	public CoursesAfterDateSortedByLecturerItem(Menu menu) {
		super("Get courses after date, sorted by lecturer.", menu);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			Date date = reader.getDate("Input date");
			Course[] courses = facade.getCoursesAfterDateSortedByLecturer(date);
			if (courses == null){
				entityDisplayer.displayMessage("Courses not found.");
			}else if (courses.length==0){
				entityDisplayer.displayMessage("Courses not found.");
			}
			else{
				entityDisplayer.displayCourses(courses, "Courses after date "+date.getDay()+"-"+date.getMonth()+"-"+date.getYear()+" sorted by lecturer");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
