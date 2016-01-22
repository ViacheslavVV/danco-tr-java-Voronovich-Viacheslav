package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class ExportLecturersItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(ExportLecturersItem.class);

	public ExportLecturersItem(Menu menu) {
		super("Export lecturers", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			String fileName = reader.getExportFileName();
			List<Object> ids = reader.getLecturerIds();
			if (facade.exportLecturers(fileName,ids)){
				entityDisplayer.displayMessage("Lecturers has been exported.");
			} else {
				entityDisplayer.displayMessage("Lecturers hasn't been exported.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
