package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class ExportLecturersItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(ExportLecturersItem.class);

	public ExportLecturersItem(Menu menu, IFacade facade) {
		super("Export lecturers", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			String fileName = ConsoleReader.getExportFileName();
			List<Object> ids = ConsoleReader.getLecturerIds();
			if (facade.exportLecturers(fileName,ids)){
				ConsoleEntityDisplayer.displayMessage("Lecturers has been exported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Lecturers hasn't been exported.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
