package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class AllLectionsItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(AllLectionsItem.class);

	public AllLectionsItem( Menu menu, IFacade facade) {
		super("Get all lections.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			List<Lection> lections = facade.getAllLections();
			if (lections == null || lections.size()==0){
				ConsoleEntityDisplayer.displayMessage("Lections not found.");
			}
			else{
				ConsoleEntityDisplayer.displayLections(lections, "All lections");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
