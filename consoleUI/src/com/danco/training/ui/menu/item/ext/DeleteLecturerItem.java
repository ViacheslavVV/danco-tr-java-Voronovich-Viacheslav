package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class DeleteLecturerItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(DeleteLecturerItem.class);

	public DeleteLecturerItem(Menu menu, IFacade facade) {
		super("Delete lecturer.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			int lecturerId = ConsoleReader.getLecturerId();
			
			if (facade.deleteLecturer(lecturerId)){
				ConsoleEntityDisplayer.displayMessage("Lecturer has been deleted.");
			}else {
				ConsoleEntityDisplayer.displayMessage("Lecturer hasn't been deleted.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
