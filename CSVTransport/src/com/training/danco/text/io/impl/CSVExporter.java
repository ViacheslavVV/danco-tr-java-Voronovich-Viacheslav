package com.training.danco.text.io.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.model.*;
import com.training.danco.text.io.api.IExporter;

public class CSVExporter implements IExporter{

	private static final String DELIMITER = ";";
	private static final String SPACE = " ";
	private static final Logger LOGGER = LogManager.getLogger(CSVExporter.class);

	@Override
	public boolean exportCourses(String fileName, List<Course> courses) {
		
		List<String> lines = new ArrayList<String>();
		for (Course course : courses){
			lines.add(convertCourseToString(course));
		}
		
		return writeStringsToFile(lines, fileName);
	}

	@Override
	public boolean exportStudents(String fileName, List<Student> students) {
		List<String> lines = new ArrayList<String>();
		for (Student student : students){
			lines.add(convertStudentToString(student));
		}
		
		return writeStringsToFile(lines, fileName);
	}

	@Override
	public boolean exportLections(String fileName, List<Lection> lections) {
		List<String> lines = new ArrayList<String>();
		for (Lection lection : lections){
			lines.add(convertLectionToString(lection));
		}
		
		return writeStringsToFile(lines, fileName);
	}

	@Override
	public boolean exportLecturers(String fileName, List<Lecturer> lecturers) {
		List<String> lines = new ArrayList<String>();
		for (Lecturer lecturer : lecturers){
			lines.add(convertLecturerToString(lecturer));
		}
		
		return writeStringsToFile(lines, fileName);
	}

	private String convertCourseToString(Course course)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(course.getName());
		sb.append(DELIMITER);
		appendDateAsString(sb, course.getStartDate(), DELIMITER);
		sb.append(DELIMITER);
		appendDateAsString(sb, course.getFinalDate(), DELIMITER);
		sb.append(DELIMITER);
		sb.append(course.getMaxStudents());
		sb.append(DELIMITER);
		sb.append(course.getMaxLections());
		sb.append(DELIMITER);
		sb.append(course.getId());
		sb.append(DELIMITER);
		sb.append((course.getLecturer() == null) ? (SPACE) : course.getLecturer().getId());
		sb.append(DELIMITER);
		appendListAsString(sb, course.getStudents(), SPACE);
		sb.append(DELIMITER);
		appendListAsString(sb, course.getLections(), SPACE);
		return sb.toString();
	}

	private void appendListAsString(StringBuilder sb, List<? extends BaseModel> list, String delimiter)
	{
		boolean isEmpty = true;
		for(BaseModel bm : list)
		{
			if (bm != null)
			{
				sb.append(bm.getId());
				sb.append(delimiter);
				isEmpty = false;
			}
		}
		if (isEmpty)
		{
			sb.append(delimiter);
		}
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
	
	private String convertLecturerToString(Lecturer lecturer)
	{
		
		return lecturer.getName()+DELIMITER+lecturer.getAge()+DELIMITER+lecturer.getId();
	}

	private String convertLectionToString(Lection lection)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(lection.getName());
		sb.append(DELIMITER);
		appendDateAsString(sb, lection.getDate(), DELIMITER);
		sb.append(DELIMITER);
		sb.append(lection.getId());
		return sb.toString();
	}
	
	private String convertStudentToString(Student student)
	{
		
		return student.getName()+DELIMITER+student.getAge()+DELIMITER+student.getId();
	}
	
	private boolean writeStringsToFile(List<String> lines, String fileName){
		
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
				return false;
			}
		}
		BufferedWriter bufferedWriter = null;
		try{
			bufferedWriter = new BufferedWriter(new FileWriter(file, false));
			for (String line : lines){
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
			return true;
		} catch (Exception e){
			LOGGER.error(e.getMessage());
			return false;
		} finally {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
				return false;
			}
		}
	}

}
