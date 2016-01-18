package com.danco.training.ui.menu.item.ext;

import java.util.Date;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class NewCourseItem extends MenuItem {

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
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		return this.menu;
	}

}
