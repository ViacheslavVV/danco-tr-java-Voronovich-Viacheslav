package com.training.danco.ui.display;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;

public class ConsoleEntityDisplayer {

	private static final DateFormat FORMATTER = new SimpleDateFormat(("dd.MM.yyyy HH:mm"));
	
	public static void displayCourse(Course course, String header) {
		if (header != null) {
			System.out.println(header);
			System.out.printf("Id:    Name:       StartDate:       Final Date:   MaxLections  MaxStudents\n");
		}

		System.out.printf("%d     %s          %s   %s     %d            %d\n", course.getId(), course.getName(),
				FORMATTER.format(course.getStartDate()), FORMATTER.format(course.getFinalDate()),
				course.getMaxLections(), course.getMaxStudents());
	}

	public static void displayCourses(List<Course> courses, String header) {
		System.out.println(header);
		System.out.printf("Id:    Name:       StartDate:    Final Date   MaxLections  MaxStudents\n");
		for (Course course : courses) {
			displayCourse(course, null);
		}

	}

	public static void displayLecturer(Lecturer lecturer, String header) {
		if (header != null) {
			System.out.println(header);
			System.out.printf("Id        Name:       Age:        \n");
		}
		System.out.printf("%d         %s       %d years      \n", lecturer.getId(), lecturer.getName(),
				lecturer.getAge());
	}

	public static void displayLecturers(List<Lecturer> lecturers, String header) {
		System.out.println(header);
		System.out.printf("Id        Name:       Age:        \n");
		for (Lecturer lecturer : lecturers) {
			displayLecturer(lecturer, null);
		}

	}

	public static void displayLection(Lection lection, String header) {
		if (header != null) {
			System.out.println(header);
			System.out.printf("Id        Name:       Date:           \n");
		}
		System.out.printf("%d         %s       %s      \n", lection.getId(), lection.getName(),
				FORMATTER.format(lection.getDate()));
	}

	public static void displayLections(List<Lection> lections, String header) {
		System.out.println(header);
		System.out.printf("Id        Name:       Date:           \n");
		for (Lection lection : lections) {
			displayLection(lection, null);
		}
	}

	public static void displayStudent(Student student, String header) {
		if (header != null) {
			System.out.println(header);
			System.out.printf("Id        Name:       Age:            \n");
		}
		System.out.printf("%d         %s       %d years          \n", student.getId(), student.getName(),
				student.getAge());
	}

	public static void displayStudents(List<Student> students, String header) {
		System.out.println(header);
		System.out.printf("Id        Name:       Age:            \n");
		for (Student student : students) {
			displayStudent(student, null);
		}

	}

	public static void displayMessage(String message) {
		System.out.println(message);

	}

}
