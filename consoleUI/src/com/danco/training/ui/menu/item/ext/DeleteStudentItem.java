package com.danco.training.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;

public class DeleteStudentItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(DeleteStudentItem.class);

	public DeleteStudentItem(Menu menu, IFacade facade) {
		super("Delete student.", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			int studentId = ConsoleReader.getStudentId();
			
			if (facade.deleteStudent(studentId)){
				ConsoleEntityDisplayer.displayMessage("Student has been deleted.");
			}else {
				ConsoleEntityDisplayer.displayMessage("Student hasn't been deleted.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}
}
