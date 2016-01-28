package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;

public class NavigationItem extends MenuItem {

	public NavigationItem(Menu menu) {
		super(menu.getName(), menu);
	}

	public NavigationItem(String name, Menu menu) {
		super(name, menu);
	}

	@Override
	public Menu doWork() {

		return this.menu;
	}

}
