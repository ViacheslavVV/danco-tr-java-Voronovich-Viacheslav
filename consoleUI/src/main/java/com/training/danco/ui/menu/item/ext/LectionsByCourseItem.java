package com.training.danco.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.ui.display.ConsoleEntityDisplayer;
import com.training.danco.ui.menu.Menu;
import com.training.danco.ui.menu.item.MenuItem;
import com.training.danco.ui.reader.ConsoleReader;
import com.training.danco.message.Message;
import com.training.danco.message.handler.IMessageHandler;
import com.training.danco.model.Course;
import com.training.danco.model.Lection;

public class LectionsByCourseItem extends MenuItem {

	private static final String GET_LECTIONS_BY_COURSE = "getLectionsByCourse";
	private static final String GET_COURSE = "getCourse";
	private static final Logger LOGGER = LogManager.getLogger(LectionsByCourseItem.class);

	public LectionsByCourseItem(Menu menu, IMessageHandler messageHandler) {
		super("Get lections by course", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			int courseId = ConsoleReader.getCourseId();
			
			Message message = new Message(GET_COURSE, courseId);
			Course course = (Course) messageHandler.sendMessage(message);
			message.setText(GET_LECTIONS_BY_COURSE);
			@SuppressWarnings("unchecked")
			List<Lection> lections = (List<Lection>) messageHandler.sendMessage(message);
			if (lections == null || lections.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Lections not found.");
			} else {
				ConsoleEntityDisplayer.displayLections(lections, "Lections from course \"" + course.getName() + "\"");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
