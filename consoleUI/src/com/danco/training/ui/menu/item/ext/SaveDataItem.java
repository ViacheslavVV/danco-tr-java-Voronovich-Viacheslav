package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
public class SaveDataItem extends MenuItem {

	public SaveDataItem(Menu menu) {
		super("Save data to file.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try {
			facade.saveDataToFile();
			entityDisplayer.displayMessage("Data has been saved to file succesfully!");
		} catch (RuntimeException e) {
			entityDisplayer.displayMessage("Error while saving data to file!("+e.getMessage()+")");
		} catch (Exception e){
			entityDisplayer.displayMessage("Something wrong!");
		}
		return this.menu;
	}

}
