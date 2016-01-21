package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class SetLecturerToCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(SetLecturerToCourseItem.class);

	public SetLecturerToCourseItem(Menu menu) {
		super("Set lecturer to course item.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int courseId = reader.getCourseId();
			int lecturerId = reader.getLecturerId();
			if (facade.setLecturerToCourse(courseId, lecturerId)){
				entityDisplayer.displayMessage("The lecturer has been apppointed.");
			} else {
				entityDisplayer.displayMessage("The lecturer hasn't been apppointed.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
