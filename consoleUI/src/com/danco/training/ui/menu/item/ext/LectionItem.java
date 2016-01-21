package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class LectionItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LectionItem.class);

	public LectionItem(Menu menu) {
		super("Get lection.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int lectionId = reader.getLectionId();
			Lection lection = facade.getLection(lectionId);
			if (lection == null){
				entityDisplayer.displayMessage("Lection wasn't created.");
			}else {
				entityDisplayer.displayLection(lection, "Lection");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
