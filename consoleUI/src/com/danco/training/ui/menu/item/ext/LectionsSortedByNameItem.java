package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class LectionsSortedByNameItem extends MenuItem {

	public LectionsSortedByNameItem(Menu menu) {
		super("Get lections sorted by name.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			Lection[] lections = facade.getLectionsSortedByName();
			if (lections == null){
				entityDisplayer.displayMessage("Lections not found.");
			}else if (lections.length==0){
				entityDisplayer.displayMessage("Lections not found.");
			}
			else{
				entityDisplayer.displayLections(lections, "Lections sorted by name");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}



}
