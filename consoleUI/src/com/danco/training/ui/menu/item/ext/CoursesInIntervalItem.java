package com.danco.training.ui.menu.item.ext;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class CoursesInIntervalItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(CoursesInIntervalItem.class);

	public CoursesInIntervalItem(Menu menu) {
		super("Get courses in interval.", menu);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			entityDisplayer.displayMessage("Enter date interval");
			Date startDate = reader.getDate("Input date from");
			Date finalDate = reader.getDate("Input date to");
			List<Course> courses = facade.getCoursesInInterval(startDate, finalDate);
			if (courses == null){
				entityDisplayer.displayMessage("Courses not found.");
			}else if (courses.size()==0){
				entityDisplayer.displayMessage("Courses not found.");
			}
			else{
				entityDisplayer.displayCourses(courses, "Courses in interval from "+startDate.getDay()+"-"+startDate.getMonth()+"-"+startDate.getYear()
				+" to "+finalDate.getDay()+"-"+finalDate.getMonth()+"-"+finalDate.getYear());
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
