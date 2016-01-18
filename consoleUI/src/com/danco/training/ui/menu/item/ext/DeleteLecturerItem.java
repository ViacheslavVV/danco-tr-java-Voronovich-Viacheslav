package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class DeleteLecturerItem extends MenuItem {

	public DeleteLecturerItem(Menu menu) {
		super("Delete lecturer.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int lecturerId = reader.getLecturerId();
			
			if (facade.deleteLecturer(lecturerId)){
				entityDisplayer.displayMessage("Lecturer has been deleted.");
			}else {
				entityDisplayer.displayMessage("Lecturer hasn't been deleted.");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
