package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class LectionsSortedByNameItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LectionsSortedByNameItem.class);

	public LectionsSortedByNameItem(Menu menu, IFacade facade) {
		super("Get lections sorted by name.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			List<Lection> lections = facade.getLectionsSortedByName();
			if (lections == null || lections.size()==0){
				ConsoleEntityDisplayer.displayMessage("Lections not found.");
			}
			else{
				ConsoleEntityDisplayer.displayLections(lections, "Lections sorted by name");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}



}
