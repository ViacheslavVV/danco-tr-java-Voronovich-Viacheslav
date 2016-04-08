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
import com.training.danco.model.Student;

public class StudentsByCourseItem extends MenuItem {

	private static final String GET_STUDENTS_BY_COURSE = "getStudentsByCourse";
	private static final String GET_COURSE = "getCourse";
	private static final Logger LOGGER = LogManager.getLogger(StudentsByCourseItem.class);

	public StudentsByCourseItem(Menu menu, IMessageHandler messageHandler) {
		super("Get students by course", menu, messageHandler);
	}

	@Override
	public Menu doWork() {
		try {
			Integer courseId = ConsoleReader.getCourseId();
			
			Message message = new Message(GET_COURSE, courseId);
			Course course = (Course) messageHandler.sendMessage(message);
			
			message.setText(GET_STUDENTS_BY_COURSE);
			@SuppressWarnings("unchecked")
			List<Student> students = (List<Student>) messageHandler.sendMessage(message);
			if (students == null || students.size() == 0) {
				ConsoleEntityDisplayer.displayMessage("Students not found.");
			} else {
				ConsoleEntityDisplayer.displayStudents(students, "Students from course \"" + course.getName() + "\"");
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}

		return this.menu;
	}

}
