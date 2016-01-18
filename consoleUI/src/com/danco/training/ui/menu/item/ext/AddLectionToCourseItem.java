package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class AddLectionToCourseItem extends MenuItem {

	public AddLectionToCourseItem(Menu menu) {
		super("Add lection to course.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int courseId = reader.getCourseId();
			int lectionId = reader.getLectionId();
			if (facade.addLectionToCourse(courseId, lectionId)){
				entityDisplayer.displayMessage("The lection has been added.");
			} else {
				entityDisplayer.displayMessage("The lection hasn't been added.");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
