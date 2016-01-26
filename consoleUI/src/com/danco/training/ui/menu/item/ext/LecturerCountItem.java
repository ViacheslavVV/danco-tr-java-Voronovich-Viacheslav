package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;

public class LecturerCountItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LecturerCountItem.class);

	public LecturerCountItem(Menu menu, IFacade facade) {
		super("Get number of lecturers.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			int count = facade.getLecturersCount();
			ConsoleEntityDisplayer.displayMessage("Number of lecturers : "+count);
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
