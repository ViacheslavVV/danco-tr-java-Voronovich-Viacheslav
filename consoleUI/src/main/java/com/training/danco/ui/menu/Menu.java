package com.training.danco.ui.menu;

import java.util.ArrayList;
import java.util.List;

import com.training.danco.ui.menu.item.MenuItem;

public class Menu {

	protected String name;
	protected List<MenuItem> menuItems;

	public Menu(String name, List<MenuItem> menuItems){
	
		this.name = name;
		this.menuItems = menuItems;
	}
	
	public Menu(String name){
		this.name = name;
		this.menuItems = new ArrayList<MenuItem>();
	}

	public void addMenuItem(MenuItem menuItem){
		
		menuItems.add(menuItem);
	}
	
	public MenuItem getMenuItem(Integer index){
		if (index < this.menuItems.size() && index >= 0){
			return this.menuItems.get(index);
		}
		return null;
	}
	
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
