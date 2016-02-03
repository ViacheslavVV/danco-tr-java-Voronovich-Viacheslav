package com.training.danco.text.io.validator.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.text.io.impl.CSVImporter;
import com.training.danco.text.io.validator.api.IValidator;

public class CSVValidator {
	
	private static final Logger LOGGER = LogManager.getLogger(CSVImporter.class);

	public static final IValidator LECTION_VALIDATOR = new CSVLectionValidator();
	public static final IValidator LECTURER_VALIDATOR = new CSVLecturerValidator();
	public static final IValidator COURSE_VALIDATOR = new CSVCourseValidator();
	public static final IValidator STUDENT_VALIDATOR = new CSVStudentValidator();
	
	public static List<String> getCorrectStrings(IValidator validator, String fileName){
		
		List<String> lines = new ArrayList<String>();
		File file = new File(fileName);
		if (!file.exists()){
			return lines;
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null) {
			    if (validator.isValid(line)){
			    	lines.add(line);
			    }
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (reader != null)
			try {
				reader.close();
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}
		return lines;
	}
}
