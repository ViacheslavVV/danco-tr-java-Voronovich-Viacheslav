package com.danco.training.ui.menu.item.ext;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class NewLecturerItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(NewLecturerItem.class);

	public NewLecturerItem(Menu menu, IFacade facade) {
		super("Create lecturer.", menu, facade);
	}

	@Override
	public Menu doWork() {

		try{
			String name = ConsoleReader.getLecturerName();
			int age = ConsoleReader.getLecturerAge();
			if (facade.setLecturer(new ArrayList<Object>(Arrays.asList(name, age)))){
				ConsoleEntityDisplayer.displayMessage("Lecturer has been created.");
			}else {
				ConsoleEntityDisplayer.displayMessage("Lecturer hasn't been created.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		return this.menu;
	}

}
