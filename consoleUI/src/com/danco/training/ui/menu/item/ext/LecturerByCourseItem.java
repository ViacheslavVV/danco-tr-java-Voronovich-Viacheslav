package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;
import com.training.danco.model.Lecturer;

public class LecturerByCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LecturerByCourseItem.class);

	public LecturerByCourseItem(Menu menu, IFacade facade) {
		super("Get lecturer by course", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			Course course = facade.getCourse(courseId);
			Lecturer lecturer = facade.getLecturerByCourse(courseId);
			if (lecturer == null) {
				ConsoleEntityDisplayer.displayMessage("Lecturer not found.");
			} else {
				ConsoleEntityDisplayer.displayLecturer(lecturer, "Lecturer of course \"" + course.getName() + "\"");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
