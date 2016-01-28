package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class DeleteCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(DeleteCourseItem.class);

	public DeleteCourseItem(Menu menu, IFacade facade) {
		super("Delete course.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			int courseId = ConsoleReader.getCourseId();
			
			if (facade.deleteCourse(courseId)){
				ConsoleEntityDisplayer.displayMessage("Course has been deleted.");
			}else {
				ConsoleEntityDisplayer.displayMessage("Course hasn't been deleted.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}
	
}
