package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class AddLectionToCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(AddLectionToCourseItem.class);

	public AddLectionToCourseItem(Menu menu, IFacade facade) {
		super("Add lection to course.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			int courseId = ConsoleReader.getCourseId();
			int lectionId = ConsoleReader.getLectionId();
			if (facade.addLectionToCourse(courseId, lectionId)){
				ConsoleEntityDisplayer.displayMessage("The lection has been added.");
			} else {
				ConsoleEntityDisplayer.displayMessage("The lection hasn't been added.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
