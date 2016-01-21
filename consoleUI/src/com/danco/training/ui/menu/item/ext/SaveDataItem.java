package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class SaveDataItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(SaveDataItem.class);
	public SaveDataItem(Menu menu) {
		super("Save data to file.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try {
			if (facade.saveDataToFile()){
				entityDisplayer.displayMessage("Data has been saved to file succesfully!");
			} else {
				entityDisplayer.displayMessage("Data hasn't been saved to file!");
			}
			
		} catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Something wrong!");
		}
		return this.menu;
	}

}
