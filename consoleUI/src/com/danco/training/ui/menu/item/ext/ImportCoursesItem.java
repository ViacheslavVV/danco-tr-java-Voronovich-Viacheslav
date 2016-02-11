package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class ImportCoursesItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(ImportCoursesItem.class);

	public ImportCoursesItem(Menu menu, IFacade facade) {
		super("Import courses", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			String fileName = ConsoleReader.getImportFileName();
			if (fileName == null) {
				return this.menu;
			}
			if (facade.importCourses(fileName)) {
				ConsoleEntityDisplayer.displayMessage("Courses has been imported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Courses hasn't been imported.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
