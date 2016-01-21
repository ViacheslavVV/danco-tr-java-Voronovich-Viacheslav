package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;
import com.training.danco.model.Lecturer;

public class LecturerByCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LecturerByCourseItem.class);

	public LecturerByCourseItem(Menu menu) {
		super("Get lecturer by course", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int courseId = reader.getCourseId();
			Course course = facade.getCourse(courseId);
			Lecturer lecturer = facade.getLecturerByCourse(courseId);
			if (lecturer == null){
				entityDisplayer.displayMessage("Lecturer not found.");
			}
			else{
				entityDisplayer.displayLecturer(lecturer, "Lecturer of course \""+course.getName()+"\"");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
