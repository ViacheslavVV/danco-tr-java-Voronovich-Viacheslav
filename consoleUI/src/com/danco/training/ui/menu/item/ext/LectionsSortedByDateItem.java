package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class LectionsSortedByDateItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LectionsSortedByDateItem.class);

	public LectionsSortedByDateItem(Menu menu) {
		super("Get lections sorted by date item.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			List<Lection> lections = facade.getLectionsSortedByDate();
			if (lections == null){
				entityDisplayer.displayMessage("Lections not found.");
			}else if (lections.size()==0){
				entityDisplayer.displayMessage("Lections not found.");
			}
			else{
				entityDisplayer.displayLections(lections, "Lections sorted by date");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

	

}
