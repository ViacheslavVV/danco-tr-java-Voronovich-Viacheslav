package com.danco.training.ui.display.impl;

import com.danco.training.ui.display.api.IEntityDisplayer;
import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;

public class ConsoleEntityDisplayer implements IEntityDisplayer {

	@SuppressWarnings("deprecation")
	@Override
	public void displayCourse(Course course, String header) {
		if (header != null){
			System.out.println(header);
			System.out.printf("Id:    Name:       StartDate:    Final Date   MaxLections  MaxStudents\n");
		}
		System.out.printf("%d     %s          %d-%d-%d       %d-%d-%d       %d            %d\n", course.getId(), course.getName(), 
				course.getStartDate().getDay(),course.getStartDate().getMonth(),course.getStartDate().getYear(), 
				course.getFinalDate().getDay(),course.getFinalDate().getMonth(),course.getFinalDate().getYear(),
				course.getMaxLections(), course.getMaxStudents());
	}

	@Override
	public void displayCourses(Course[] courses, String header) {
		System.out.println(header);
		System.out.printf("Id:    Name:       StartDate:    Final Date   MaxLections  MaxStudents\n");
		for (Course course : courses){
			displayCourse(course, null);
		}

	}

	@Override
	public void displayLecturer(Lecturer lecturer, String header) {
		if (header != null){
			System.out.println(header);
			System.out.printf("Id        Name:       Age:        \n");
		}
		System.out.printf("%d         %s       %d years      \n", lecturer.getId(), lecturer.getName(),lecturer.getAge());
	}

	@Override
	public void displayLecturers(Lecturer[] lecturers, String header) {
		System.out.println(header);			
		System.out.printf("Id        Name:       Age:        \n");
		for (Lecturer lecturer : lecturers){
			displayLecturer(lecturer, null);
		}
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void displayLection(Lection lection, String header) {
		if (header != null){
			System.out.println(header);
			System.out.printf("Id        Name:       Date:           \n");
		}
		System.out.printf("%d         %s       %d-%d-%d          \n", lection.getId(), lection.getName(), 
				lection.getDate().getDay(),lection.getDate().getMonth(),lection.getDate().getYear());
	}

	@Override
	public void displayLections(Lection[] lections, String header) {
		System.out.println(header);
		System.out.printf("Id        Name:       Date:           \n");
		for (Lection lection : lections){
			displayLection(lection, null);
		}
	}

	@Override
	public void displayStudent(Student student, String header) {
		if (header != null){
			System.out.println(header);
			System.out.printf("Id        Name:       Age:            \n");
		}
		System.out.printf("%d         %s       %d years          \n", student.getId(), student.getName(), student.getAge());
	}

	@Override
	public void displayStudents(Student[] students, String header) {
		System.out.println(header);			
		System.out.printf("Id        Name:       Age:            \n");
		for (Student student : students){
			displayStudent(student, null);
		}

	}

	@Override
	public void displayMessage(String message) {
		System.out.println(message);
		
	}

}
