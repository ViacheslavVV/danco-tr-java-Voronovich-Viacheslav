package com.danco.training.ui.controller;

import com.danco.training.ui.display.ConsoleMenuDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;

public class Controller {

	private static final int NATURAL_NUMBER_INCREMENT = 1;

	public Controller() {
		
	}

	public void run(Menu currentMenu){
		MenuItem currentMenuItem = null;
		int mode = 0;
		while(currentMenu != null){
			ConsoleMenuDisplayer.displayMenu(currentMenu);
			mode = getMode();	
			
			currentMenuItem = currentMenu.getMenuItem(mode-NATURAL_NUMBER_INCREMENT);
			if (currentMenuItem == null){
				continue;
			}
			
			currentMenu = currentMenuItem.doWork();
		}
	}
	
	public int getMode() {
		return ConsoleReader.getMenuMode();
	}
}
