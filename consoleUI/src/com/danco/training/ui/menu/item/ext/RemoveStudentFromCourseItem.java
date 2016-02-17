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

public class RemoveStudentFromCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(RemoveStudentFromCourseItem.class);
	public RemoveStudentFromCourseItem(Menu menu, IFacade facade) {
		super("Remove student from course.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			int courseId = ConsoleReader.getCourseId();
			int studentId = ConsoleReader.getStudentId();
			if (facade.removeStudentFromCourse(new ArrayList<Object>(Arrays.asList(courseId, studentId)))){
				ConsoleEntityDisplayer.displayMessage("The student has been deleted.");
			} else {
				ConsoleEntityDisplayer.displayMessage("The student hasn't been deleted.");
			}
		} catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}


}
