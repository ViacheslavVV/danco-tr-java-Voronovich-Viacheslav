package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class ExportAllLectionsItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(ExportAllLectionsItem.class);

	public ExportAllLectionsItem(Menu menu, IFacade facade) {
		super("Export all lections", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			String fileName = ConsoleReader.getExportFileName();
			
			if (facade.exportAllLections(fileName)){
				ConsoleEntityDisplayer.displayMessage("Lections has been exported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Lections hasn't been exported.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
