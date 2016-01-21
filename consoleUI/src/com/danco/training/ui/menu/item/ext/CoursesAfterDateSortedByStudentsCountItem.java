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

public class CoursesAfterDateSortedByStudentsCountItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(CoursesAfterDateSortedByStudentsCountItem.class);

	public CoursesAfterDateSortedByStudentsCountItem(Menu menu) {
		super("Get courses after date, sorted by number of students.", menu);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			Date date = reader.getDate("Input date");
			List<Course> courses = facade.getCoursesAfterDateSortedByStudentsCount(date);
			if (courses == null){
				entityDisplayer.displayMessage("Courses not found.");
			}else if (courses.size() == 0){
				entityDisplayer.displayMessage("Courses not found.");
			}
			else{
				entityDisplayer.displayCourses(courses, "Courses after date "+date.getDay()+"-"+date.getMonth()+"-"+date.getYear()+" sorted by number of students");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
