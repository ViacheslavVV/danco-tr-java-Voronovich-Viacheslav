package com.danco.training.ui.menu.item.ext;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class NewCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(NewCourseItem.class);

	public NewCourseItem(Menu menu) {
		super("Create course.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {

		try{
			String name = reader.getCourseName();
			Date startDate = reader.getCourseStartDate();
			Date finalDate = reader.getCourseFinalDate();
			int maxStudents = reader.getMaxStudentsNumber();
			int maxLections = reader.getMaxLectionsNumber();
			if (facade.setCourse(new Course(name, startDate, finalDate, maxStudents, maxLections))){
				entityDisplayer.displayMessage("Course has been created.");
			}else {
				entityDisplayer.displayMessage("Course hasn't been created.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		return this.menu;
	}

}
