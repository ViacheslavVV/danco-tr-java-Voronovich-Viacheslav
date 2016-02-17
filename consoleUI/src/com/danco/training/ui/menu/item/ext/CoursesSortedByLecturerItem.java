package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class CoursesSortedByLecturerItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(CoursesSortedByLecturerItem.class);

	public CoursesSortedByLecturerItem(Menu menu, IFacade facade) {
		super("Get courses sorted by lecturer.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			List<Course> courses = facade.getCoursesSortedByLecturer();
			if (courses == null || courses.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Courses not found.");
			} else {
				ConsoleEntityDisplayer.displayCourses(courses, "Courses sorted by lecturer");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
