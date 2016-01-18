package com.danco.training.ui.display.impl;

import java.util.List;

import com.danco.training.ui.display.api.IMenuDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;

public class ConsoleMenuDisplayer implements IMenuDisplayer {

	@Override
	public void displayMenu(Menu menu) {

		System.out.println(menu.getName());
		
		List<MenuItem> menuItems = menu.getMenuItems();
		for (int i=0, index=1; i<menuItems.size(); index++){
			System.out.println(index+". "+menuItems.get(i).getName());
			i=index;
		}

	}

}
