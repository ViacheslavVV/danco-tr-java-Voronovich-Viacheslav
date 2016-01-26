package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class ImportLectionsItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(ImportLectionsItem.class);

	public ImportLectionsItem(Menu menu, IFacade facade) {
		super("Import lections", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			String fileName = ConsoleReader.getImportFileName();
			if (fileName == null){
				return this.menu;
			}
			if (facade.importLections(fileName)){
				ConsoleEntityDisplayer.displayMessage("Lections has been imported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Lections hasn't been imported.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
