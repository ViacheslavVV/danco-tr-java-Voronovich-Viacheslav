package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class NavigationItem extends MenuItem{

	public NavigationItem(Menu menu) {
		super(menu.getName(),menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		
		return this.menu;
	}

	

}
