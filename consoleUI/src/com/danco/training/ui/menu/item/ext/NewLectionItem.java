package com.danco.training.ui.menu.item.ext;

import java.util.Date;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class NewLectionItem extends MenuItem {

	public NewLectionItem(Menu menu) {
		super("Create lection.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {

		try{
			String name = reader.getLectionName();
			Date date = reader.getLectionDate();
			if (facade.setLection(new Lection(name, date))){
				entityDisplayer.displayMessage("Lection has been created.");
			}else {
				entityDisplayer.displayMessage("Lection hasn't been created.");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		return this.menu;
	}

	

}
