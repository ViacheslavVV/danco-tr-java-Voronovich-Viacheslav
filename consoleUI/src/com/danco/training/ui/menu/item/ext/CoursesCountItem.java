package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;

public class CoursesCountItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(CoursesCountItem.class);
	
	public CoursesCountItem(Menu menu, IFacade facade) {
		super("Get number of courses.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			int count = facade.getCoursesCount();
			ConsoleEntityDisplayer.displayMessage("Number of courses : "+count);
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
