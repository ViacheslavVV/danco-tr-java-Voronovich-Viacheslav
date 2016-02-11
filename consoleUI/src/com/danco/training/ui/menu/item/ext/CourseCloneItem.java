package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class CourseCloneItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(CourseCloneItem.class);

	public CourseCloneItem(Menu menu, IFacade facade) {
		super("Clone course", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			if (facade.cloneCourse(courseId)) {
				ConsoleEntityDisplayer.displayMessage("Course has been cloned.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Course hasn't been cloned.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
