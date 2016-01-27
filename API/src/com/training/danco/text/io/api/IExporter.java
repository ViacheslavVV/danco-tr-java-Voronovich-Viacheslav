package com.training.danco.text.io.api;

import java.util.List;

import com.training.danco.model.*;

public interface IExporter {

	public boolean exportCourses(String fileName, List<Course> courses);
	public boolean exportStudents(String fileName, List<Student> students);
	public boolean exportLections(String fileName, List<Lection> lections);
	public boolean exportLecturers(String fileName, List<Lecturer> lecturers);
}
