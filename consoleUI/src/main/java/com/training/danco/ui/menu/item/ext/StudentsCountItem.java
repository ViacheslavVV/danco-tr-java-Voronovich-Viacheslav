package com.training.danco.ui.menu.item.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class StudentsCountItem extends MenuItem {

	private static final String GET_STUDENTS_COUNT = "getStudentsCount";
	private static final Logger LOGGER = LogManager.getLogger(StudentsCountItem.class);

	public StudentsCountItem(Menu menu, IMessageHandler messageHandler) {
		super("Get number of students.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try{
			Message msg = new Message(GET_STUDENTS_COUNT, null);
			Integer count = (Integer)messageHandler.sendMessage(msg);
			ConsoleEntityDisplayer.displayMessage("Number of students : "+count);
		} catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
