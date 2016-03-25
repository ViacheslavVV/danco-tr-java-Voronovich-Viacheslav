package com.danco.training.ui.menu.item.ext;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class CoursesAfterDateSortedByNameItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(CoursesAfterDateSortedByNameItem.class);

	public CoursesAfterDateSortedByNameItem(Menu menu, IFacade facade) {
		super("Get courses after date, sorted by name.", menu, facade);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Menu doWork() {
		try {
			Date date = ConsoleReader.getDate("Input date");
			List<Course> courses = facade.getCoursesAfterDateSortedByName(date);
			if (courses == null || courses.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Courses not found.");
			} else {
				ConsoleEntityDisplayer.displayCourses(courses, "Courses after date " + date.getDay() + "-"
						+ date.getMonth() + "-" + date.getYear() + " sorted by name");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
