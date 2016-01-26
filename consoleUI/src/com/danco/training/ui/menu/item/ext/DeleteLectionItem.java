package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class DeleteLectionItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(DeleteLectionItem.class);

	public DeleteLectionItem(Menu menu, IFacade facade) {
		super("Delete lection.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			int lectionId = ConsoleReader.getLectionId();
			
			if (facade.deleteLection(lectionId)){
				ConsoleEntityDisplayer.displayMessage("Lection has been deleted.");
			}else {
				ConsoleEntityDisplayer.displayMessage("Lection hasn't been deleted.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
