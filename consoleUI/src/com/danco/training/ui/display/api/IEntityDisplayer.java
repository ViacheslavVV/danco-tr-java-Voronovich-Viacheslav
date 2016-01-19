package com.danco.training.ui.display.api;

import java.util.List;

import com.training.danco.model.*;

public interface IEntityDisplayer {

	void displayCourse(Course course, String header);
	void displayCourses(List<Course> courses, String header);
	void displayLecturer(Lecturer lecturer, String header);
	void displayLecturers(List<Lecturer> lecturers, String header);
	void displayLection(Lection lection, String header);
	void displayLections(List<Lection> lections, String header);
	void displayStudent(Student student, String header);
	void displayStudents(List<Student> students, String header);
	void displayMessage(String message);
}
