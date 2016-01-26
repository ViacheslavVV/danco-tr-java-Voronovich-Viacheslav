package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;

public class SaveDataItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(SaveDataItem.class);
	public SaveDataItem(Menu menu, IFacade facade) {
		super("Save data to file.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			if (facade.saveDataToFile()){
				ConsoleEntityDisplayer.displayMessage("Data has been saved to file succesfully!");
			} else {
				ConsoleEntityDisplayer.displayMessage("Data hasn't been saved to file!");
			}
			
		} catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Something wrong!");
		}
		return this.menu;
	}

}
