package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.ConsoleEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.ConsoleReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;
import com.training.danco.model.Student;

public class StudentsByCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(StudentsByCourseItem.class);

	public StudentsByCourseItem(Menu menu, IFacade facade) {
		super("Get students by course", menu, facade);
	}

	@Override
	public Menu doWork() {
		try{
			int courseId = ConsoleReader.getCourseId();
			Course course = facade.getCourse(courseId);
			List<Student> students = facade.getStudentsByCourse(courseId);
			if (students == null){
				ConsoleEntityDisplayer.displayMessage("Students not found.");
			}else if (students.size()==0){
				ConsoleEntityDisplayer.displayMessage("Students not found.");
			}
			else{
				ConsoleEntityDisplayer.displayStudents(students, "Students from course \""+course.getName()+"\"");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			ConsoleEntityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
