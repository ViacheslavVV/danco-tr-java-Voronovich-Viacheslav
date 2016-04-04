package com.training.danco.ui.display;

import java.util.List;

import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;

public class ConsoleMenuDisplayer {

	public static void displayMenu(Menu menu) {

		System.out.println(menu.getName());
		
		List<MenuItem> menuItems = menu.getMenuItems();
		for (int i=0, index=1; i<menuItems.size(); index++){
			System.out.println(index+". "+menuItems.get(i).getName());
			i=index;
		}

	}

}
