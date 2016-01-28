package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class ExportAllCoursesItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(ExportAllCoursesItem.class);

	public ExportAllCoursesItem(Menu menu, IFacade facade) {
		super("Export all courses", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			String fileName = ConsoleReader.getExportFileName();
			
			if (facade.exportAllCourses(fileName)){
				ConsoleEntityDisplayer.displayMessage("Courses has been exported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Courses hasn't been exported.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
