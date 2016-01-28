package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class ExportLectionsItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(ExportLectionsItem.class);

	public ExportLectionsItem(Menu menu, IFacade facade) {
		super("Export lections", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			String fileName = ConsoleReader.getExportFileName();
			List<Object> ids = ConsoleReader.getLectionIds();
			if (facade.exportLections(fileName,ids)){
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
