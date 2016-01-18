package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class LoadDataItem extends MenuItem {

	public LoadDataItem(Menu menu) {
		super("Load data from file.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try {
			facade.loadDataFromFIle();
			entityDisplayer.displayMessage("Data has been loaded from file succesfully!");
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}
		catch (Exception e){
			entityDisplayer.displayMessage("Something wrong!");
		}
		return this.menu;
	}

	

}
