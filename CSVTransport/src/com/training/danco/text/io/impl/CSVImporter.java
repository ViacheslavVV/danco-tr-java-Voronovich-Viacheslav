package com.training.danco.text.io.impl;

import java.util.ArrayList;
import java.util.List;

import com.training.danco.facade.api.IFacade;
import com.training.danco.model.*;
import com.training.danco.text.io.api.IImporter;
import com.training.danco.text.io.converter.CSVConverter;
import com.training.danco.text.io.validator.impl.CSVValidator;

public class CSVImporter implements IImporter{

	@Override
	public List<Course> importCourses(String fileName, IFacade facade) {
		List<String> lines = CSVValidator.getCorrectStrings(CSVValidator.COURSE_VALIDATOR, fileName);
		List<Course> courses = new ArrayList<Course>();
		try{
		for (String line : lines){
			courses.add(CSVConverter.convertStringToCourse(line, facade));
		}
		} catch (RuntimeException e){
			courses = null;
		}
		return courses;
	}

	@Override
	public List<Student> importStudents(String fileName) {
		List<String> lines = CSVValidator.getCorrectStrings(CSVValidator.STUDENT_VALIDATOR, fileName);
		List<Student> students = new ArrayList<Student>();
		for (String line : lines){
			students.add(CSVConverter.convertStringToStudent(line));
		}
		return students;
	}

	@Override
	public List<Lection> importLections(String fileName) {
		List<String> lines = CSVValidator.getCorrectStrings(CSVValidator.LECTION_VALIDATOR, fileName);
		List<Lection> lections = new ArrayList<Lection>();
		for (String line : lines){
			lections.add(CSVConverter.convertStringToLection(line));
		}
		return lections;
	}

	@Override
	public List<Lecturer> importLecturers(String fileName) {
		List<String> lines = CSVValidator.getCorrectStrings(CSVValidator.LECTURER_VALIDATOR, fileName);
		List<Lecturer> lecturers = new ArrayList<Lecturer>();
		for (String line : lines){
			lecturers.add(CSVConverter.convertStringToLecturer(line));
		}
		return lecturers;
	}

}
