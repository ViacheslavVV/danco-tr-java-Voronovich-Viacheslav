package com.danco.training.ui.menu.item;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public abstract class MenuItem {

	protected String name;
	protected Menu menu;
	
	protected MenuItem(String name, Menu menu) {
		this.name = name;
		this.menu = menu;
	}
	
	protected MenuItem(String name) {
		this.name = name;
	}
	
	public abstract Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade);
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
