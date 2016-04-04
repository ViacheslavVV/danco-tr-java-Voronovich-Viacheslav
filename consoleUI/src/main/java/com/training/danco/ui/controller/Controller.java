package com.training.danco.ui.controller;

import com.training.danco.ui.display.ConsoleMenuDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;

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
