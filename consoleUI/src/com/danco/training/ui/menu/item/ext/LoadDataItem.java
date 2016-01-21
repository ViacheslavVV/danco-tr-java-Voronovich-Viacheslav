package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class LoadDataItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LoadDataItem.class);
	public LoadDataItem(Menu menu) {
		super("Load data from file.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try {
			if (facade.loadDataFromFIle()){
				entityDisplayer.displayMessage("Data has been loaded from file succesfully!");
			}else {
				entityDisplayer.displayMessage("Data hasn't been loaded from file!");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Something wrong!");
		}
		return this.menu;
	}

	

}
