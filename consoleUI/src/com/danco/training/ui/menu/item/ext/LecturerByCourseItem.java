package com.danco.training.ui.menu.item.ext;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.MenuItem;
import com.danco.training.ui.reader.api.IReader;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;
import com.training.danco.model.Lecturer;

public class LecturerByCourseItem extends MenuItem {

	public LecturerByCourseItem(Menu menu) {
		super("Get lecturer by course", menu);
	}

	@Override
	public Menu doWork(IEntityDisplayer entityDisplayer, IReader reader, IFacade facade) {
		try{
			int courseId = reader.getCourseId();
			Course course = facade.getCourse(courseId);
			Lecturer lecturer = facade.getLecturerByCourse(courseId);
			if (lecturer == null){
				entityDisplayer.displayMessage("Lecturer not found.");
			}
			else{
				entityDisplayer.displayLecturer(lecturer, "Lecturer of course \""+course.getName()+"\"");
			}
		}catch (RuntimeException e){
			entityDisplayer.displayMessage(e.getMessage());
		}catch (Exception e){
			entityDisplayer.displayMessage("Technical error.");
		}
		
		return this.menu;
	}

}
