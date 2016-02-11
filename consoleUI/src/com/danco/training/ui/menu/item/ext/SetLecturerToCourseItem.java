package com.danco.training.ui.menu.item.ext;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class SetLecturerToCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(SetLecturerToCourseItem.class);

	public SetLecturerToCourseItem(Menu menu, IFacade facade) {
		super("Set lecturer to course.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			int lecturerId = ConsoleReader.getLecturerId();

			if (facade.setLecturerToCourse(new ArrayList<Object>(Arrays.asList(courseId, lecturerId)))) {
				ConsoleEntityDisplayer.displayMessage("The lecturer has been apppointed.");
			} else {
				ConsoleEntityDisplayer.displayMessage("The lecturer hasn't been apppointed.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
