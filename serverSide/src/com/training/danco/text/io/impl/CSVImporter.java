package com.training.danco.text.io.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.facade.api.IFacade;
import com.training.danco.model.*;
import com.training.danco.text.io.api.IImporter;
import com.training.danco.text.io.validator.api.IValidator;
import com.training.danco.text.io.validator.impl.CSVValidator;

public class CSVImporter implements IImporter{

	private static final Logger LOGGER = LogManager.getLogger(CSVImporter.class);
	
	private IFacade facade;
	
	public CSVImporter(IFacade facade) {
		super();
		this.facade = facade;
	}

	@Override
	public List<Course> importCourses(String fileName) {
		List<String> lines = getCorrectStrings(CSVValidator.COURSE_VALIDATOR, fileName);
		List<Course> courses = new ArrayList<Course>();
		try{
		for (String line : lines){
			courses.add(convertStringToCourse(line));
		}
		} catch (RuntimeException e){
			courses = null;
		}
		return courses;
	}

	@Override
	public List<Student> importStudents(String fileName) {
		List<String> lines = getCorrectStrings(CSVValidator.STUDENT_VALIDATOR, fileName);
		List<Student> students = new ArrayList<Student>();
		for (String line : lines){
			students.add(convertStringToStudent(line));
		}
		return students;
	}

	@Override
	public List<Lection> importLections(String fileName) {
		List<String> lines = getCorrectStrings(CSVValidator.LECTION_VALIDATOR, fileName);
		List<Lection> lections = new ArrayList<Lection>();
		for (String line : lines){
			lections.add(convertStringToLection(line));
		}
		return lections;
	}

	@Override
	public List<Lecturer> importLecturers(String fileName) {
		List<String> lines = getCorrectStrings(CSVValidator.LECTURER_VALIDATOR, fileName);
		List<Lecturer> lecturers = new ArrayList<Lecturer>();
		for (String line : lines){
			lecturers.add(convertStringToLecturer(line));
		}
		return lecturers;
	}

	@SuppressWarnings("deprecation")
	private Course convertStringToCourse(String string)
	{
		String[] params = string.split("[;]");
		
		Course newCourse = new Course(params[0], 
				new Date(Integer.parseInt(params[1]),Integer.parseInt(params[2]),Integer.parseInt(params[3]),
						Integer.parseInt(params[4]),Integer.parseInt(params[5])),
				new Date(Integer.parseInt(params[6]),Integer.parseInt(params[7]),Integer.parseInt(params[8]),
						Integer.parseInt(params[9]),Integer.parseInt(params[10])),Integer.parseInt(params[11]),Integer.parseInt(params[12]));
		newCourse.setId(Integer.parseInt(params[13]));
		
		if (!params[14].isEmpty()){
			int lecturerId = Integer.parseInt(params[14]);
			newCourse.setLecturer(this.facade.getLecturer(lecturerId));
		}
		
		String[] studentsIds = params[15].split(" ");
		List<Student> students = getStudentsByIds(studentsIds, Integer.parseInt(params[11]));
		newCourse.setStudents(students);
		
		String[] lectionsIds = params[16].split(" ");
		List<Lection> lections = getLectionsByIds(lectionsIds, Integer.parseInt(params[12]));
		newCourse.setLections(lections);
		return newCourse;
	}
	
	private List<Student> getStudentsByIds(String[] studentsIds, int maxStudents)
	{
		List<Student> students = new ArrayList<Student>();
		Student curStudent = null;
		for (String id : studentsIds )
		{
			if (!id.isEmpty()){
				try{
				curStudent = this.facade.getStudent(Integer.parseInt(id));
				} catch (NumberFormatException e){
					throw new RuntimeException("Not valid id of student!");
				}
				if (curStudent != null){
					
					students.add(curStudent);
				}
			 }
		}
		return students;
	}
	
	private List<Lection> getLectionsByIds(String[] lectionsIds, int maxLections)
	{
		List<Lection> lections = new ArrayList<Lection>();
		Lection curLection = null;
		for (String id : lectionsIds )
		{
			if (!id.isEmpty()){
				try{
				curLection = this.facade.getLection(Integer.parseInt(id));
				} catch (NumberFormatException e){
					throw new RuntimeException("Not valid id of lection!");
				}
				if (curLection != null){
					
					lections.add(curLection);
				}
			 }
		}
		return lections;
	}

	@SuppressWarnings("deprecation")
	private Lection convertStringToLection(String string)
	{
		String[] params = string.split("[;]");
		Lection newLection = new Lection(params[0], 
				new Date(Integer.parseInt(params[1]),Integer.parseInt(params[2]),Integer.parseInt(params[3]),
						Integer.parseInt(params[4]),Integer.parseInt(params[5])));
		newLection.setId(Integer.parseInt(params[6]));
		return newLection;
	}

	private Lecturer convertStringToLecturer(String string)
	{
		
		String[] params = string.split("[;]");
		Lecturer newLecturer = new Lecturer(params[0], Integer.parseInt(params[1]));
		newLecturer.setId(Integer.parseInt(params[2]));
		return newLecturer;
	}

	private Student convertStringToStudent(String string)
	{
		
		String[] params = string.split("[;]");
		Student newStudent = new Student(params[0], Integer.parseInt(params[1]));
		newStudent.setId(Integer.parseInt(params[2]));
		return newStudent;
	}
	
	private List<String> getCorrectStrings(IValidator validator, String fileName){
		
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
