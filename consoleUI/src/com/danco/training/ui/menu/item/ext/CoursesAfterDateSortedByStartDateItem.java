package com.danco.training.ui.menu.item.ext;

import java.util.Date;
import java.util.List;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class CoursesAfterDateSortedByStartDateItem extends MenuItem {

	public CoursesAfterDateSortedByStartDateItem(Menu menu) {
		super("Get courses after date, sorted by start date.", menu);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			Date date = reader.getDate("Input date");
			List<Course> courses = facade.getCoursesAfterDateSortedByStartDate(date);
			if (courses == null){
				entityDisplayer.displayMessage("Courses not found.");
			}else if (courses.size()==0){
				entityDisplayer.displayMessage("Courses not found.");
			}
			else{
				entityDisplayer.displayCourses(courses, "Courses after date "+date.getDay()+"-"+date.getMonth()+"-"+date.getYear()+" sorted by start date");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
