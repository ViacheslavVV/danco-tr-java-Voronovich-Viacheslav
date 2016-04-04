package com.training.danco.ui.menu.item;

import com.training.danco.ui.menu.Menu;
import com.training.danco.message.handler.IMessageHandler;

public abstract class MenuItem {

	protected String name;
	protected IMessageHandler messageHandler;
	protected Menu menu;
	
	protected MenuItem(String name, Menu menu, IMessageHandler messageHandler) {
		this.name = name;
		this.messageHandler = messageHandler;
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
