package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;

public class LectionCountItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LectionCountItem.class);

	
	public LectionCountItem(Menu menu, IFacade facade) {
		super("Get number of lections.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			int count = facade.getLectionsCount();
			ConsoleEntityDisplayer.displayMessage("Number of lections : "+count);
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
