package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;

public class DeleteStudentItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(DeleteStudentItem.class);

	public DeleteStudentItem(Menu menu) {
		super("Delete student.", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int studentId = reader.getStudentId();
			
			if (facade.deleteStudent(studentId)){
				entityDisplayer.displayMessage("Student has been deleted.");
			}else {
				entityDisplayer.displayMessage("Student hasn't been deleted.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}
}
