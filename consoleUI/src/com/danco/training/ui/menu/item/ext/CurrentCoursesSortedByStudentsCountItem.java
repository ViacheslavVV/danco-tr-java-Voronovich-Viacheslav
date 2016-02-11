package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class CurrentCoursesSortedByStudentsCountItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(CurrentCoursesSortedByStudentsCountItem.class);

	public CurrentCoursesSortedByStudentsCountItem(Menu menu, IFacade facade) {
		super("Get current courses sorted by number of students.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			List<Course> courses = facade.getCurrentCoursesSortedByStudentsCount();
			if (courses == null || courses.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Courses not found.");
			} else {
				ConsoleEntityDisplayer.displayCourses(courses, "Current Courses sorted by number of students");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
