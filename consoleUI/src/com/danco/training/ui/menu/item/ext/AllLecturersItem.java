package com.danco.training.ui.menu.item.ext;

import java.util.List;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lecturer;

public class AllLecturersItem extends MenuItem {

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
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
