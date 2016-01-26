package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lecturer;

public class LecturersSortedByCoursesCountItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LecturersSortedByCoursesCountItem.class);

	public LecturersSortedByCoursesCountItem(Menu menu, IFacade facade) {
		super("Get lecturers sorted by number of courses.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			List<Lecturer> lecturers = facade.getLecturersSortedByCoursesCount();
			if (lecturers == null || lecturers.size()==0){
				ConsoleEntityDisplayer.displayMessage("Lecturers not found.");
			}
			else{
				ConsoleEntityDisplayer.displayLecturers(lecturers, "Lecturers sorted by number of courses");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}
}
