package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class RemoveStudentFromCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(RemoveStudentFromCourseItem.class);
	public RemoveStudentFromCourseItem(Menu menu) {
		super("Remove student from course.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int courseId = reader.getCourseId();
			int studentId = reader.getStudentId();
			if (facade.removeStudentFromCourse(courseId, studentId)){
				entityDisplayer.displayMessage("The student has been deleted.");
			} else {
				entityDisplayer.displayMessage("The student hasn't been deleted.");
			}
		} catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}


}
