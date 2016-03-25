package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class CourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(CourseItem.class);

	public CourseItem(Menu menu, IFacade facade) {
		super("Get course.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			Course course = facade.getCourse(courseId);
			if (course == null) {
				ConsoleEntityDisplayer.displayMessage("Course wasn't created.");
			} else {
				ConsoleEntityDisplayer.displayCourse(course, "Course");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
