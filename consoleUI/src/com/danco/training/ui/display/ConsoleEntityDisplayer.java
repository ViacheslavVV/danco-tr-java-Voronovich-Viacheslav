package com.danco.training.ui.display;

import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;

public class ConsoleEntityDisplayer{

	@SuppressWarnings("deprecation")
	public static void displayCourse(Course course, String header) {
		if (header != null){
			System.out.println(header);
			System.out.printf("Id:    Name:       StartDate:       Final Date:   MaxLections  MaxStudents\n");
		}
		System.out.printf("%d     %s          %d-%d-%d %d:%d   %d-%d-%d %d:%d     %d            %d\n", course.getId(), course.getName(), 
				course.getStartDate().getDay(),course.getStartDate().getMonth(),course.getStartDate().getYear(),course.getStartDate().getHours(),course.getStartDate().getMinutes(), 
				course.getFinalDate().getDay(),course.getFinalDate().getMonth(),course.getFinalDate().getYear(),course.getFinalDate().getHours(),course.getFinalDate().getMinutes(),
				course.getMaxLections(), course.getMaxStudents());
	}

	public static void displayCourses(List<Course> courses, String header) {
		System.out.println(header);
		System.out.printf("Id:    Name:       StartDate:    Final Date   MaxLections  MaxStudents\n");
		for (Course course : courses){
			displayCourse(course, null);
		}

	}


	public static void displayLecturer(Lecturer lecturer, String header) {
		if (header != null){
			System.out.println(header);
			System.out.printf("Id        Name:       Age:        \n");
		}
		System.out.printf("%d         %s       %d years      \n", lecturer.getId(), lecturer.getName(),lecturer.getAge());
	}

	public static void displayLecturers(List<Lecturer> lecturers, String header) {
		System.out.println(header);			
		System.out.printf("Id        Name:       Age:        \n");
		for (Lecturer lecturer : lecturers){
			displayLecturer(lecturer, null);
		}
		
	}

	@SuppressWarnings("deprecation")
	public static void displayLection(Lection lection, String header) {
		if (header != null){
			System.out.println(header);
			System.out.printf("Id        Name:       Date:           \n");
		}
		System.out.printf("%d         %s       %d-%d-%d %d:%d      \n", lection.getId(), lection.getName(), 
				lection.getDate().getDay(),lection.getDate().getMonth(),lection.getDate().getYear(),lection.getDate().getHours(),lection.getDate().getMinutes());
	}

	public static void displayLections(List<Lection> lections, String header) {
		System.out.println(header);
		System.out.printf("Id        Name:       Date:           \n");
		for (Lection lection : lections){
			displayLection(lection, null);
		}
	}

	public static void displayStudent(Student student, String header) {
		if (header != null){
			System.out.println(header);
			System.out.printf("Id        Name:       Age:            \n");
		}
		System.out.printf("%d         %s       %d years          \n", student.getId(), student.getName(), student.getAge());
	}

	public static void displayStudents(List<Student> students, String header) {
		System.out.println(header);			
		System.out.printf("Id        Name:       Age:            \n");
		for (Student student : students){
			displayStudent(student, null);
		}

	}

	public static void displayMessage(String message) {
		System.out.println(message);
		
	}

}
