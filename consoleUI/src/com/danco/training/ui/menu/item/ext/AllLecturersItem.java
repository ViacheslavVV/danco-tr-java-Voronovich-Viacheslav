package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lecturer;

public class AllLecturersItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(AllLecturersItem.class);

	public AllLecturersItem(Menu menu) {
		super("Get all lecturers.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			List<Lecturer> lecturers = facade.getAllLecturers();
			if (lecturers == null){
				entityDisplayer.displayMessage("Lecturers not found.");
			}else if (lecturers.size()==0){
				entityDisplayer.displayMessage("Lecturers not found.");
			}
			else{
				entityDisplayer.displayLecturers(lecturers, "All lecturers");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
