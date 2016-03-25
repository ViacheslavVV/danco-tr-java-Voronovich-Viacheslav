package com.training.danco.text.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.controller.*;
import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.dao.impl.*;
import com.training.danco.model.*;
import com.training.danco.services.api.*;
import com.training.danco.services.impl.*;

public class DataConverter {

	private static final Logger LOGGER = LogManager.getLogger(DataConverter.class);
	private static final int SHIFT_ID = 1;
	private List<Course> courses;
	private List<Lection> lections;
	private List<Lecturer> lecturers;
	private List<Student> students;
	private CourseController courseController;
	private LectionController lectionController;
	private LecturerController lecturerController;
	private StudentController studentController;
	
	private int getMaxId(List< ? extends BaseModel> data){
		int maxId = 0;
		for (BaseModel item : data){
			if (item.getId() > maxId){
				maxId = item.getId();
			}
		}
		return maxId;
	}
	
	public Object convertDataToObject(List<Student> students, List<Lection> lections, List<Lecturer> lecturers, List<Course> courses){
		
		List<Object> data = new ArrayList<Object>();
		data.add(students);
		data.add(lections);
		data.add(lecturers);
		data.add(courses);
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public boolean convertObjectsToEntities(List<Object> data){
		
		boolean result = true;
		try{
			this.students = (List<Student>)data.get(0);
			this.lections = (List<Lection>)data.get(1);
			this.lecturers = (List<Lecturer>)data.get(2);
			this.courses = (List<Course>)data.get(3);
		 } catch (Exception e){
			LOGGER.error(e.getMessage());
			result = false;
			this.students = new ArrayList<Student>();
			this.lections = new ArrayList<Lection>();
			this.lecturers = new ArrayList<Lecturer>();
			this.courses = new ArrayList<Course>();
		 }
		
		return result;
	}
	
	public void fillControllers(){
		CourseRepository.setCourseId(getMaxId(this.courses)+SHIFT_ID);
		LectionRepository.setLectionId(getMaxId(this.lections)+SHIFT_ID);
		LecturerRepository.setLecturerId(getMaxId(this.lecturers)+SHIFT_ID);
		StudentRepository.setStudentId(getMaxId(this.students)+SHIFT_ID);
		ICourseRepository courseRepository = new CourseRepository(this.courses);
		ICourseService courseService = new CourseService(courseRepository);
		ILectionService lectionService = new LectionService(new LectionRepository(this.lections));
		ILecturerService lecturerService = new LecturerService(new LecturerRepository(this.lecturers), courseRepository);
		IStudentService studentService = new StudentService( new StudentRepository(this.students));
		courseController = new CourseController(courseService, lecturerService, lectionService, studentService);
		lectionController = new LectionController(lectionService);
		lecturerController = new LecturerController(lecturerService);
		studentController = new StudentController(studentService);
	}

	public CourseController getCourseController() {
		return this.courseController;
	}

	public LectionController getLectionController() {
		return this.lectionController;
	}

	public LecturerController getLecturerController() {
		return this.lecturerController;
	}

	public StudentController getStudentController() {
		return this.studentController;
	}
	
}
