package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lecturer;

public class LecturerItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(LecturerItem.class);

	public LecturerItem(Menu menu) {
		super("Get lecturer.",menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int lecturerId = reader.getLecturerId();
			Lecturer lecturer = facade.getLecturer(lecturerId);
			if (lecturer == null){
				entityDisplayer.displayMessage("Lecturer wasn't created.");
			}else {
				entityDisplayer.displayLecturer(lecturer, "Lecturer");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
