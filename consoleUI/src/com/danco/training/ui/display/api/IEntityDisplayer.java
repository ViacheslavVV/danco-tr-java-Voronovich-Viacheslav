package com.danco.training.ui.display.api;

import com.training.danco.model.*;

public interface IEntityDisplayer {

	void displayCourse(Course course, String header);
	void displayCourses(Course[] courses, String header);
	void displayLecturer(Lecturer lecturer, String header);
	void displayLecturers(Lecturer[] lecturers, String header);
	void displayLection(Lection lection, String header);
	void displayLections(Lection[] lections, String header);
	void displayStudent(Student student, String header);
	void displayStudents(Student[] students, String header);
	void displayMessage(String message);
}
