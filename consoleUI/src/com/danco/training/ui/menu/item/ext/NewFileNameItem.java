package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class NewFileNameItem extends MenuItem {

	public NewFileNameItem( Menu menu) {
		super("Set new file name.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try {
			String fileName = reader.getString("New file name");
			if (facade.setFileName(fileName)){
				entityDisplayer.displayMessage("File name has been changed succesfully");
			}else{
				entityDisplayer.displayMessage("File name hasn't been changed");
			}
		}  catch (Exception e){
			entityDisplayer.displayMessage("Something wrong!");
		}
		return this.menu;
	}

}
