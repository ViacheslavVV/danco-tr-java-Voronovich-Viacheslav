package com.training.danco.text.io.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.model.*;
import com.training.danco.text.io.api.IExporter;
import com.training.danco.text.io.converter.CSVConverter;

public class CSVExporter implements IExporter{

	private static final Logger LOGGER = LogManager.getLogger(CSVExporter.class);

	@Override
	public boolean exportCourses(String fileName, List<Course> courses) {
		
		List<String> lines = new ArrayList<String>();
		for (Course course : courses){
			lines.add(CSVConverter.convertCourseToString(course));
		}
		
		return writeStringsToFile(lines, fileName);
	}

	@Override
	public boolean exportStudents(String fileName, List<Student> students) {
		List<String> lines = new ArrayList<String>();
		for (Student student : students){
			lines.add(CSVConverter.convertStudentToString(student));
		}
		
		return writeStringsToFile(lines, fileName);
	}

	@Override
	public boolean exportLections(String fileName, List<Lection> lections) {
		List<String> lines = new ArrayList<String>();
		for (Lection lection : lections){
			lines.add(CSVConverter.convertLectionToString(lection));
		}
		
		return writeStringsToFile(lines, fileName);
	}

	@Override
	public boolean exportLecturers(String fileName, List<Lecturer> lecturers) {
		List<String> lines = new ArrayList<String>();
		for (Lecturer lecturer : lecturers){
			lines.add(CSVConverter.convertLecturerToString(lecturer));
		}
		
		return writeStringsToFile(lines, fileName);
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
