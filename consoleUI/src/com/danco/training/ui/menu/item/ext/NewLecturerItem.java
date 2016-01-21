package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lecturer;

public class NewLecturerItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(NewLecturerItem.class);

	public NewLecturerItem(Menu menu) {
		super("Create lecturer.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {

		try{
			String name = reader.getLecturerName();
			int age = reader.getLecturerAge();
			if (facade.setLecturer(new Lecturer(name, age))){
				entityDisplayer.displayMessage("Lecturer has been created.");
			}else {
				entityDisplayer.displayMessage("Lecturer hasn't been created.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		return this.menu;
	}

}
