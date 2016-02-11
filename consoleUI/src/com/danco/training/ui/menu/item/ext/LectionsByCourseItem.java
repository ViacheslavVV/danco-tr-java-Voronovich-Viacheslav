package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;
import com.training.danco.model.Lection;

public class LectionsByCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LectionsByCourseItem.class);

	public LectionsByCourseItem(Menu menu, IFacade facade) {
		super("Get lections by course", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			Course course = facade.getCourse(courseId);
			List<Lection> lections = facade.getLectionsByCourse(courseId);
			if (lections == null || lections.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Lections not found.");
			} else {
				ConsoleEntityDisplayer.displayLections(lections, "Lections from course \"" + course.getName() + "\"");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
