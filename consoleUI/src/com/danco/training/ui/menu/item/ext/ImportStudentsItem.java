package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class ImportStudentsItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(ImportStudentsItem.class);

	public ImportStudentsItem( Menu menu) {
		super("Import students", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			String fileName = reader.getImportFileName();
			if (fileName == null){
				return this.menu;
			}
			if (facade.importStudents(fileName)){
				entityDisplayer.displayMessage("Students has been imported.");
			} else {
				entityDisplayer.displayMessage("Students hasn't been imported.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
