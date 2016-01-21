package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class DeleteLectionItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(DeleteLectionItem.class);

	public DeleteLectionItem(Menu menu) {
		super("Delete lection.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int lectionId = reader.getLectionId();
			
			if (facade.deleteLection(lectionId)){
				entityDisplayer.displayMessage("Lection has been deleted.");
			}else {
				entityDisplayer.displayMessage("Lection hasn't been deleted.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
