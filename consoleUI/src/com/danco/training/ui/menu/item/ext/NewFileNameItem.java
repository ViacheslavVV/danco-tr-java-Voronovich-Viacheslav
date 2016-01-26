package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class NewFileNameItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(NewFileNameItem.class);

	public NewFileNameItem( Menu menu, IFacade facade) {
		super("Set new file name.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			String fileName = ConsoleReader.getString("New file name");
			if (facade.setFileName(fileName)){
				ConsoleEntityDisplayer.displayMessage("File name has been changed succesfully");
			}else{
				ConsoleEntityDisplayer.displayMessage("File name hasn't been changed");
			}
		}  catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Something wrong!");
		}
		return this.menu;
	}

}
