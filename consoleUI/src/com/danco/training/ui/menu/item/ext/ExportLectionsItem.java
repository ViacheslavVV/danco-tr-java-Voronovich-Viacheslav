package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class ExportLectionsItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(ExportLectionsItem.class);

	public ExportLectionsItem(Menu menu) {
		super("Export lections", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			String fileName = reader.getExportFileName();
			List<Object> ids = reader.getLectionIds();
			if (facade.exportLections(fileName,ids)){
				entityDisplayer.displayMessage("Lections has been exported.");
			} else {
				entityDisplayer.displayMessage("Lections hasn't been exported.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
