package com.danco.training.ui.menu.item.ext;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;
import com.training.danco.model.Student;

public class StudentsByCourseItem extends MenuItem {

	private static final Logger LOGGER = LogManager.getLogger(StudentsByCourseItem.class);

	public StudentsByCourseItem(Menu menu) {
		super("Get students by course", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int courseId = reader.getCourseId();
			Course course = facade.getCourse(courseId);
			List<Student> students = facade.getStudentsByCourse(courseId);
			if (students == null){
				entityDisplayer.displayMessage("Students not found.");
			}else if (students.size()==0){
				entityDisplayer.displayMessage("Students not found.");
			}
			else{
				entityDisplayer.displayStudents(students, "Students from course \""+course.getName()+"\"");
			}
		}catch (Exception e){
			LOGGER.error(e.getMessage());
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
