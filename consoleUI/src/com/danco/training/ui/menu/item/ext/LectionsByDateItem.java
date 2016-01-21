package com.danco.training.ui.menu.item.ext;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class LectionsByDateItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LectionsByDateItem.class);

	public LectionsByDateItem(Menu menu) {
		super("Get lections by date.", menu);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			Date date = reader.getLectionDate();
			List<Lection> lections = facade.getLectionsByDate(date);
			if (lections == null){
				entityDisplayer.displayMessage("Lections not found.");
			}else if (lections.size() == 0){
				entityDisplayer.displayMessage("Lections not found.");
			}
			else{
				entityDisplayer.displayLections(lections, "Lections by date "+date.getDay()+"-"+date.getMonth()+"-"+date.getYear());
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
