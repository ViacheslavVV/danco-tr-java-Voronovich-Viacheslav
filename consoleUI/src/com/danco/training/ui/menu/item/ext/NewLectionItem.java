package com.danco.training.ui.menu.item.ext;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class NewLectionItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(NewLectionItem.class);

	public NewLectionItem(Menu menu, IFacade facade) {
		super("Create lection.", menu, facade);
	}

	@Override
	public Menu doWork() {

		try{
			String name = ConsoleReader.getLectionName();
			Date date = ConsoleReader.getLectionDate();
			if (facade.setLection(new Lection(name, date))){
				ConsoleEntityDisplayer.displayMessage("Lection has been created.");
			}else {
				ConsoleEntityDisplayer.displayMessage("Lection hasn't been created.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		return this.menu;
	}

	

}
