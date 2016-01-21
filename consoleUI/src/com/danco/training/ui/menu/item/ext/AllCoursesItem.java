package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class AllCoursesItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(AllCoursesItem.class);

	public AllCoursesItem( Menu menu) {
		super("Get all courses.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			List<Course> courses = facade.getAllCourses();
			if (courses == null){
				entityDisplayer.displayMessage("Courses not found.");
			}else if (courses.size()==0){
				entityDisplayer.displayMessage("Courses not found.");
			}
			else{
				entityDisplayer.displayCourses(courses, "All courses");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

	
}
