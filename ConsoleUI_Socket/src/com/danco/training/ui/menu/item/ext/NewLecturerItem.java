package com.danco.training.ui.menu.item.ext;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;

public class NewLecturerItem extends MenuItem {

	private static final String SET_LECTURER = "setLecturer";
	private static final Logger LOGGER = LogManager.getLogger(NewLecturerItem.class);

	public NewLecturerItem(Menu menu, IMessageHandler messageHandler) {
		super("Create lecturer.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {

		try{
			String name = ConsoleReader.getLecturerName();
			int age = ConsoleReader.getLecturerAge();
			Message message = new Message(SET_LECTURER, new ArrayList<Object>(Arrays.asList(name, age)));
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result){
				ConsoleEntityDisplayer.displayMessage("Lecturer has been created.");
			}else {
				ConsoleEntityDisplayer.displayMessage("Lecturer hasn't been created.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		return this.menu;
	}

}
