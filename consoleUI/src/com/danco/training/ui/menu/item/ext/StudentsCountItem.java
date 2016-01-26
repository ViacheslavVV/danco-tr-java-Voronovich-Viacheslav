package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;

public class StudentsCountItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(StudentsCountItem.class);

	public StudentsCountItem(Menu menu, IFacade facade) {
		super("Get number of students.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			int count = facade.getStudentsCount();
			ConsoleEntityDisplayer.displayMessage("Number of students : "+count);
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
