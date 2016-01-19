package com.training.danco.text.converter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.training.danco.controller.*;
import com.training.danco.dao.api.*;
import com.training.danco.dao.impl.*;
import com.training.danco.model.*;
import com.training.danco.services.api.*;
import com.training.danco.services.impl.*;

public class DataDeserializer {
	
	private static final int SHIFT_ID = 1;
	private ICourseService courseService;
	private ILectionService lectionService;
	private ILecturerService lecturerService;
	private IStudentService studentService;
	private final String FILE_NAME;
	
	public DataDeserializer(String fileName) {
		this.initializeServices(new ArrayList<Student>(), new ArrayList<Lection>(), new ArrayList<Lecturer>(), new ArrayList<Course>());
		this.FILE_NAME = fileName;
	}
	
	private void initializeServices(List<Student> students, List<Lection> lections, List<Lecturer> lecturers, List<Course> courses){
		ICourseRepository courseRepository = new CourseRepository(courses);
		this.courseService = new CourseService(courseRepository);
		this.lectionService  = new LectionService(new LectionRepository(lections), courseRepository);
		this.lecturerService = new LecturerService(new LecturerRepository(lecturers), courseRepository);
		this.studentService = new StudentService( new StudentRepository(students), courseRepository);
	}
	
	private int getMaxId(List< ? extends BaseModel> data){
		int maxId = 0;
		for (BaseModel item : data){
			if (item.getId() > maxId){
				maxId = item.getId();
			}
		}
		return maxId;
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadData() throws IOException
	{
		boolean result = true;
		List<Object> data = new ArrayList<Object>();
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			fileInputStream = new FileInputStream(FILE_NAME);
			objectInputStream = new ObjectInputStream(fileInputStream);
			data = (List<Object>)objectInputStream.readObject();
			List<Student> students = (List<Student>)data.get(0);
			List<Lection> lections = (List<Lection>)data.get(1);
			List<Lecturer> lecturers = (List<Lecturer>)data.get(2);
			List<Course> courses = (List<Course>)data.get(3);
			this.initializeServices(students, lections, lecturers, courses);
			Course.setCourseId(getMaxId(courses)+SHIFT_ID);
			Lection.setLectionId(getMaxId(lections)+SHIFT_ID);
			Lecturer.setLecturerId(getMaxId(lecturers)+SHIFT_ID);
			Student.setStudentId(getMaxId(students)+SHIFT_ID);
		} catch (Exception e) {
			result = false;
		} finally {
			if (objectInputStream != null){
				objectInputStream.close();
			}
		}
		return result;
		
	}
	
	public CourseController getCourseController() {
		return new CourseController(this.courseService, this.lecturerService, this.lectionService, this.studentService);
	}

	public LectionController getLectionController() {
		return new LectionController(this.lectionService);
	}

	public LecturerController getLecturerController() {
		return new LecturerController(this.lecturerService);
	}

	public StudentController getStudentController() {
		return new StudentController(this.studentService);
	}
}

