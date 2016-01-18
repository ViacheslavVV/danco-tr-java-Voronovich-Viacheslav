package com.danco.training.ui.controller;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.display.api.IMenuDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.builder.MenuBuilder;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class Controller {

	private static final int NATURAL_NUMBER_INCREMENT = 1;
	
	private IMenuDisplayer menuDisplayer;
	private IEntityDisplayer entityDisplayer;
	private IFacade facade;
	private IReader reader;
	
	public Controller(IMenuDisplayer menuDisplayer, IEntityDisplayer entityDisplayer, IFacade facade,
			IReader reader) {
		this.menuDisplayer = menuDisplayer;
		this.entityDisplayer = entityDisplayer;
		this.facade = facade;
		this.reader = reader;
	}

	public void run(){
		MenuBuilder menuBuilder = new MenuBuilder();
		Menu currentMenu = menuBuilder.generateMenu();
		MenuItem currentMenuItem = null;
		int mode = 0;
		while(currentMenu != null){
			this.menuDisplayer.displayMenu(currentMenu);
			mode = getMode();	
			
			currentMenuItem = currentMenu.getMenuItem(mode-NATURAL_NUMBER_INCREMENT);
			if (currentMenuItem == null){
				continue;
			}
			
			currentMenu = currentMenuItem.doWork(this.entityDisplayer, this.reader, this.facade);
		}
	}
	
	public int getMode() {
		return this.reader.getMenuMode();
	}
}
