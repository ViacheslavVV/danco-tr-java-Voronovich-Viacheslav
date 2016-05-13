package com.training.danco.text.io.api;

import java.util.List;

import com.training.danco.model.*;

public interface IExporter {

	public Boolean exportCourses(String fileName, List<Course> courses);
	public Boolean exportStudents(String fileName, List<Student> students);
	public Boolean exportLections(String fileName, List<Lection> lections);
	public Boolean exportLecturers(String fileName, List<Lecturer> lecturers);
}
