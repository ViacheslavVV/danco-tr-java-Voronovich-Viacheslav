package com.training.danco.text.io.api;

import java.util.List;

import com.training.danco.facade.api.IFacade;
import com.training.danco.model.*;

public interface IImporter {

	public List<Course> importCourses(String fileName, IFacade facade);
	public List<Student> importStudents(String fileName);
	public List<Lection> importLections(String fileName);
	public List<Lecturer> importLecturers(String fileName);
	
}
