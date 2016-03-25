package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class LectionItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LectionItem.class);

	public LectionItem(Menu menu, IFacade facade) {
		super("Get lection.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try {
			int lectionId = ConsoleReader.getLectionId();
			Lection lection = facade.getLection(lectionId);
			if (lection == null) {
				ConsoleEntityDisplayer.displayMessage("Lection wasn't created.");
			} else {
				ConsoleEntityDisplayer.displayLection(lection, "Lection");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
