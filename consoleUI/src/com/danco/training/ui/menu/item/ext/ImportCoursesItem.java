package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class ImportCoursesItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(ImportCoursesItem.class);

	public ImportCoursesItem(Menu menu) {
		super("Import courses", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			String fileName = reader.getImportFileName();
			if (fileName == null){
				return this.menu;
			}
			if (facade.importCourses(fileName)){
				entityDisplayer.displayMessage("Courses has been imported.");
			} else {
				entityDisplayer.displayMessage("Courses hasn't been imported.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
