package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class RemoveLectionFromCourseItem extends MenuItem {

	public RemoveLectionFromCourseItem( Menu menu) {
		super("Remove lection from course.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int courseId = reader.getCourseId();
			int lectionId = reader.getLectionId();
			if (facade.removeLectionFromCourse(courseId, lectionId)){
				entityDisplayer.displayMessage("The lection has been deleted.");
			} else {
				entityDisplayer.displayMessage("The lection hasn't been deleted.");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
