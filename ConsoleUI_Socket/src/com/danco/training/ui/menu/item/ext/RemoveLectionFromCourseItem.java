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

public class RemoveLectionFromCourseItem extends MenuItem {

	private static final String REMOVE_LECTION_FROM_COURSE = "removeLectionFromCourse";
	private static final Logger LOGGER = LogManager.getLogger(RemoveLectionFromCourseItem.class);

	public RemoveLectionFromCourseItem( Menu menu, IMessageHandler messageHandler) {
		super("Remove lection from course.", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try{
			int courseId = ConsoleReader.getCourseId();
			int lectionId = ConsoleReader.getLectionId();
			Message message = new Message(REMOVE_LECTION_FROM_COURSE,
					new ArrayList<Object>(Arrays.asList(courseId, lectionId)));
			boolean result = (boolean) messageHandler.sendMessage(message);
			if (result){
				ConsoleEntityDisplayer.displayMessage("The lection has been deleted.");
			} else {
				ConsoleEntityDisplayer.displayMessage("The lection hasn't been deleted.");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
