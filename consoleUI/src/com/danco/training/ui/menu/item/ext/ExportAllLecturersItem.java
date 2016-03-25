package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class ExportAllLecturersItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(ExportAllLecturersItem.class);

	public ExportAllLecturersItem(Menu menu, IFacade facade) {
		super("Export all lecturers", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			String fileName = ConsoleReader.getExportFileName();

			if (facade.exportAllLecturers(fileName)) {
				ConsoleEntityDisplayer.displayMessage("Lecturers has been exported.");
			} else {
				ConsoleEntityDisplayer.displayMessage("Lecturers hasn't been exported.");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
