package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;

public class LoadDataItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LoadDataItem.class);
	public LoadDataItem(Menu menu, IFacade facade) {
		super("Load data from file.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			if (facade.loadDataFromFIle()){
				ConsoleEntityDisplayer.displayMessage("Data has been loaded from file succesfully!");
			}else {
				ConsoleEntityDisplayer.displayMessage("Data hasn't been loaded from file!");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Something wrong!");
		}
		return this.menu;
	}

	

}
