package com.danco.training.ui.menu.item.ext;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class NewCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(NewCourseItem.class);

	public NewCourseItem(Menu menu, IFacade facade) {
		super("Create course.", menu, facade);
	}

	@Override
	public Menu doWork() {

		try{
			String name = ConsoleReader.getCourseName();
			Date startDate = ConsoleReader.getCourseStartDate();
			Date finalDate = ConsoleReader.getCourseFinalDate();
			int maxStudents = ConsoleReader.getMaxStudentsNumber();
			int maxLections = ConsoleReader.getMaxLectionsNumber();
			if (facade.setCourse(new Course(name, startDate, finalDate, maxStudents, maxLections))){
				ConsoleEntityDisplayer.displayMessage("Course has been created.");
			}else {
				ConsoleEntityDisplayer.displayMessage("Course hasn't been created.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		return this.menu;
	}

}
