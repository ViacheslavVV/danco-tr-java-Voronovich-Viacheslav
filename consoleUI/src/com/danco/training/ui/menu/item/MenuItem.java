package com.danco.training.ui.menu.item;

import com.danco.training.ui.menu.Menu;
import com.training.danco.facade.api.IFacade;

public abstract class MenuItem {

	protected String name;
	protected IFacade facade;
	protected Menu menu;
	
	protected MenuItem(String name, Menu menu, IFacade facade) {
		this.name = name;
		this.facade = facade;
		this.menu = menu;
	}
	
	protected MenuItem(String name, Menu menu) {
		this.name = name;
		this.menu = menu;
	}
	
	public abstract Menu doWork();
	
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
