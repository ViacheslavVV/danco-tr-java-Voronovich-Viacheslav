package com.danco.training.ui.menu.item.ext;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class LectionsByDateItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LectionsByDateItem.class);

	public LectionsByDateItem(Menu menu, IFacade facade) { 
		super("Get lections by date.", menu, facade);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Menu doWork() {
		try{
			Date date = ConsoleReader.getLectionDate();
			List<Lection> lections = facade.getLectionsByDate(date);
			if (lections == null || lections.size() == 0){
				ConsoleEntityDisplayer.displayMessage("Lections not found.");
			}
			else{
				ConsoleEntityDisplayer.displayLections(lections, "Lections by date "+date.getDay()+"-"+date.getMonth()+"-"+date.getYear());
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
