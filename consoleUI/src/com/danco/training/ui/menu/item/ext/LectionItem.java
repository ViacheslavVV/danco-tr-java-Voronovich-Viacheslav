package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class LectionItem extends MenuItem {

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
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
