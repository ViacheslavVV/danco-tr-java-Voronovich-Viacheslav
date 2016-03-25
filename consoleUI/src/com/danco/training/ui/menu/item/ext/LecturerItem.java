package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lecturer;

public class LecturerItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LecturerItem.class);

	public LecturerItem(Menu menu, IFacade facade) {
		super("Get lecturer.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			int lecturerId = ConsoleReader.getLecturerId();
			Lecturer lecturer = facade.getLecturer(lecturerId);
			if (lecturer == null) {
				ConsoleEntityDisplayer.displayMessage("Lecturer wasn't created.");
			} else {
				ConsoleEntityDisplayer.displayLecturer(lecturer, "Lecturer");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
