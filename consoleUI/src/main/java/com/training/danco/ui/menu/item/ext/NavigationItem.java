package com.training.danco.ui.menu.item.ext;

import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;

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
