package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class CurrentCoursesSortedByStartDateItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(CurrentCoursesSortedByStartDateItem.class);

	public CurrentCoursesSortedByStartDateItem(Menu menu, IFacade facade) {
		super("Get current courses sorted by start date.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			List<Course> courses = facade.getCurrentCoursesSortedByStartDate();
			if (courses == null || courses.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Courses not found.");
			} else {
				ConsoleEntityDisplayer.displayCourses(courses, "Current Courses sorted by start date");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
