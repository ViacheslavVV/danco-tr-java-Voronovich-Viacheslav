package com.training.danco.text.converter;

import java.io.IOException;
import java.lang.StringBuilder;
import java.util.Date;

import com.training.danco.controller.*;
import com.training.danco.model.*;
import com.danco.training.TextFileWorker;

public class DataWriter {
	
	private CourseController courseController;
	private LectionController lectionController;
	private LecturerController lecturerController;
	private StudentController studentController;
	private TextFileWorker textFileWorker;
	
	public DataWriter(CourseController courseController, LectionController lectionController,
			LecturerController lecturerController, StudentController studentController, TextFileWorker textFileWorker) {
		super();
		this.courseController = courseController;
		this.lectionController = lectionController;
		this.lecturerController = lecturerController;
		this.studentController = studentController;
		this.textFileWorker = textFileWorker;
	}

	private String[] convertCoursesToStrings(Course[] courses)
	{
		String[] result = new String[courses.length+2];
		int rowNumber = 0;
		
		result[rowNumber++] = "<courses>";
		
		for (int i=0; i < courses.length; i++)
		{
			if (courses[i] != null)
			{
				result[rowNumber++] = convertCourseToString(courses[i]);
			}
		}
		
		result[rowNumber] = "</courses>";
		return result;
	}
	
	private String convertCourseToString(Course course)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(course.getName());
		sb.append('|');
		appendDateAsString(sb, course.getStartDate(), "|");
		sb.append('|');
		appendDateAsString(sb, course.getFinalDate(), "|");
		sb.append('|');
		sb.append(course.getMaxStudents());
		sb.append('|');
		sb.append(course.getMaxLections());
		sb.append('|');
		sb.append(course.getId());
		sb.append('|');
		sb.append((course.getLecturer() == null) ? (" ") : course.getLecturer().getId());
		sb.append('|');
		appendArrayAsString(sb, course.getStudents(), " ");
		sb.append('|');
		appendArrayAsString(sb, course.getLections(), " ");
		return sb.toString();
	}
	
	private void appendArrayAsString(StringBuilder sb, BaseModel[] array, String delimiter)
	{
		boolean isEmpty = true;
		for(BaseModel bm : array)
		{
			if (bm != null)
			{
				sb.append(bm.getId());
				sb.append(delimiter);
			}
		}
		if (!isEmpty)
		{
			sb.append(" ");
		}
	}
	
	private String[] convertLectionsToStrings(Lection[] lections)
	{
		String[] result = new String[lections.length+2];
		int rowNumber = 0;
		
		result[rowNumber++] = "<lections>";
		
		for (int i=0; i<lections.length; i++)
		{
			if (lections[i] != null)
			{
				result[rowNumber++] = convertLectionToString(lections[i]);
			}
		}
		
		result[rowNumber] = "</lections>";
		return result;
	}
		
	private String convertLectionToString(Lection lection)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(lection.getName());
		sb.append('|');
		appendDateAsString(sb, lection.getDate(), "|");
		sb.append('|');
		sb.append(lection.getId());
		return sb.toString();
	}
	
	@SuppressWarnings("deprecation")
	private void appendDateAsString(StringBuilder sb, Date date, String delimiter)
	{
		sb.append(date.getYear());
		sb.append(delimiter);
		sb.append(date.getMonth());
		sb.append(delimiter);
		sb.append(date.getDay());
		sb.append(delimiter);
		sb.append(date.getHours());
		sb.append(delimiter);
		sb.append(date.getMinutes());
	}
	
	private String[] convertLecturersToStrings(Lecturer[] lecturers)
	{
		String[] result = new String[lecturers.length+2];
		int rowNumber = 0;
		
		result[rowNumber++] = "<lecturers>";
		
		for (int i=0; i<lecturers.length; i++)
		{
			if (lecturers[i] != null)
			{
				result[rowNumber++] = convertLecturerToString(lecturers[i]);
			}
		}
		
		result[rowNumber] = "</lecturers>";
		return result;
	}
	
	private String convertLecturerToString(Lecturer lecturer)
	{
		
		return lecturer.getName()+'|'+lecturer.getAge()+'|'+lecturer.getId();
	}
	
	private String[] convertStudentsToStrings(Student[] students)
	{
		String[] result = new String[students.length+2];
		int rowNumber = 0;
		
		result[rowNumber++] = "<students>";
		
		for (int i=0; i<students.length; i++)
		{
			if (students[i] != null)
			{
				result[rowNumber++] = convertStudentToString(students[i]);
			}
		}
		
		result[rowNumber] = "</students>";
		return result;
	}

	private String convertStudentToString(Student student)
	{
		
		return student.getName()+'|'+student.getAge()+'|'+student.getId();
	}

	public void saveData() throws IOException
	{
		String[] lecturersData = convertLecturersToStrings(lecturerController.getAll());
		String[] lectionsData= convertLectionsToStrings(lectionController.getAll());
		String[] studentsData = convertStudentsToStrings(studentController.getAll());
		String[] coursesData = convertCoursesToStrings(courseController.getAll());
		
		String[] data = new String[lecturersData.length + lectionsData.length + studentsData.length+coursesData.length];
		
		int destPos = 0;
		System.arraycopy(lecturersData, 0, data, destPos, lecturersData.length);
		destPos += lecturersData.length;
		
		System.arraycopy(lectionsData, 0, data, destPos, lectionsData.length);
		destPos += lectionsData.length;
		
		System.arraycopy(studentsData, 0, data, destPos, studentsData.length);
		destPos += studentsData.length;
		
		System.arraycopy(coursesData, 0, data, destPos, coursesData.length);
		
		
		textFileWorker.writeToFile(data);
	}
}