package com.training.danco.text.converter;

import com.danco.training.TextFileWorker;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import com.training.danco.controller.*;
import com.training.danco.dao.api.*;
import com.training.danco.dao.impl.*;
import com.training.danco.model.*;
import com.training.danco.services.api.*;
import com.training.danco.services.impl.*;

public class DataReader {
	
	private ICourseService courseService;
	private ILectionService lectionService;
	private ILecturerService lecturerService;
	private IStudentService studentService;
	
	private TextFileWorker textFileWorker;
	
	public DataReader(int courseRepositorySize, int lectionRepositorySize,
			int lecturerRepositorySize, int studentRepositorySize, TextFileWorker textFileWorker) {
		ICourseRepository courseRepository = new CourseRepository(new Course[courseRepositorySize]);
		this.courseService = new CourseService(courseRepository);
		this.lectionService  = new LectionService(new LectionRepository(new Lection[lectionRepositorySize]), courseRepository);
		this.lecturerService = new LecturerService(new LecturerRepository(new Lecturer[lecturerRepositorySize]), courseRepository);
		this.studentService = new StudentService( new StudentRepository(new Student[studentRepositorySize]), courseRepository);
		this.textFileWorker = textFileWorker;
	}
	
	private int convertStringsToCourses(String[] strings)
	{
		int id=0;
		for (int i=1; i<strings.length-1; i++)
		{
			Course course = convertStringToCourse(strings[i]);
			if (course.getId() > id)
			{
				id = course.getId();
			}
			this.courseService.set(course);
		}
		return id;
	}
	
	@SuppressWarnings("deprecation")
	private Course convertStringToCourse(String string)
	{
		String[] params = string.split("[|]");
		
		Course newCourse = new Course(params[0], 
				new Date(Integer.parseInt(params[1]),Integer.parseInt(params[2]),Integer.parseInt(params[3]),
						Integer.parseInt(params[4]),Integer.parseInt(params[5])),
				new Date(Integer.parseInt(params[6]),Integer.parseInt(params[7]),Integer.parseInt(params[8]),
						Integer.parseInt(params[9]),Integer.parseInt(params[10])),Integer.parseInt(params[11]),Integer.parseInt(params[12]));
		newCourse.setId(Integer.parseInt(params[13]));
		
		if (!params[14].isEmpty()){
			int lecturerId = Integer.parseInt(params[14]);
			newCourse.setLecturer(this.lecturerService.get(lecturerId));
		}
		
		String[] studentsIds = params[15].split(" ");
		Student[] students = getStudentsByIds(studentsIds, Integer.parseInt(params[11]));
		newCourse.setStudents(students);
		
		String[] lectionsIds = params[16].split(" ");
		Lection[] lections = getLectionssByIds(lectionsIds, Integer.parseInt(params[12]));
		newCourse.setLections(lections);
		return newCourse;
	}
	
	private Student[] getStudentsByIds(String[] studentsIds, int maxStudents)
	{
		Student[] students = new Student[studentsIds.length];
		int currentStudentIndex = 0;
		for (String id : studentsIds )
		{
			if (!id.isEmpty()){
				students[currentStudentIndex++] = this.studentService.get(Integer.parseInt(id));
			 }
		}
		return students;
	}
	
	private Lection[] getLectionssByIds(String[] lectionsIds, int maxLections)
	{
		Lection[] lections = new Lection[lectionsIds.length];
		int currentLectionIndex = 0;
		for (String id : lectionsIds )
		{
			if (!id.isEmpty()){
				lections[currentLectionIndex++] = this.lectionService.get(Integer.parseInt(id));
			 }
		}
		return lections;
	}
	
	private int convertStringsToLections(String[] strings)
	{
		int id=0;
		for (int i=1; i<strings.length-1; i++)
		{
			Lection lection = convertStringToLection(strings[i]);
			if (lection.getId() > id)
			{
				id = lection.getId();
			}
			this.lectionService.set(lection);
		}
		return id;
	}
	
	@SuppressWarnings("deprecation")
	private Lection convertStringToLection(String string)
	{
		String[] params = string.split("[|]");
		Lection newLection = new Lection(params[0], 
				new Date(Integer.parseInt(params[1]),Integer.parseInt(params[2]),Integer.parseInt(params[3]),
						Integer.parseInt(params[4]),Integer.parseInt(params[5])));
		newLection.setId(Integer.parseInt(params[6]));
		return newLection;
	}
	
	private int convertStringsToLecturers(String[] strings)
	{
		int id=0;
		for (int i=1; i<strings.length-1; i++)
		{
			Lecturer lecturer = convertStringToLecturer(strings[i]);
			if (lecturer.getId() > id)
			{
				id = lecturer.getId();
			}
			this.lecturerService.set(lecturer);
		}
		return id;
	}
	
	private Lecturer convertStringToLecturer(String string)
	{
		
		String[] params = string.split("[|]");
		Lecturer newLecturer = new Lecturer(params[0], Integer.parseInt(params[1]));
		newLecturer.setId(Integer.parseInt(params[2]));
		return newLecturer;
	}
	
	private int convertStringsToStudents(String[] strings)
	{
		int id=0;
		for (int i=1; i<strings.length-1; i++)
		{
			Student student = convertStringToStudent(strings[i]);
			if (student.getId() > id)
			{
				id = student.getId();
			}
			this.studentService.set(student);
		}
		return id;
	}
	
	private Student convertStringToStudent(String string)
	{
		
		String[] params = string.split("[|]");
		Student newStudent = new Student(params[0], Integer.parseInt(params[1]));
		newStudent.setId(Integer.parseInt(params[2]));
		return newStudent;
	}

	public void loadData() throws IOException
	{
		String[] data = textFileWorker.readFromFile();
		
		int lecturersStartIndex = findStringPosition(data, "<lecturers>");
		int lecturersEndIndex = findStringPosition(data, "</lecturers>");
		int maxLecturerId = 0;
		if (lecturersStartIndex != -1){
			maxLecturerId = convertStringsToLecturers(Arrays.copyOfRange(data, lecturersStartIndex, lecturersEndIndex+1));
		}
		Course.setCourseId(maxLecturerId+1);
		
		int lectionsStartIndex  = findStringPosition(data, "<lections>");
		int lectionsEndIndex = findStringPosition(data, "</lections>");
		int maxLectionId = 0;
		if (lectionsStartIndex != -1){
			maxLectionId = convertStringsToLections(Arrays.copyOfRange(data, lectionsStartIndex, lectionsEndIndex+1));
		}
		Lection.setLectionId(maxLectionId+1);
		
		int studentsStartIndex = findStringPosition(data, "<students>");
		int studentsEndIndex = findStringPosition(data, "</students>");
		int maxStudentId = 0;
		if (studentsStartIndex != -1){
			maxStudentId = convertStringsToStudents(Arrays.copyOfRange(data, studentsStartIndex, studentsEndIndex+1));
		}
		Student.setStudentId(maxStudentId+1);
		
		int coursesStartIndex = findStringPosition(data, "<courses>");
		int coursesEndIndex = findStringPosition(data, "</courses>");	
		int maxCourseId = 0;
		if (coursesStartIndex != -1){
			maxCourseId = convertStringsToCourses(Arrays.copyOfRange(data, coursesStartIndex, coursesEndIndex+1));
		}
		Course.setCourseId(maxCourseId+1);
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

	public int findStringPosition(String[] strings, String string)
	{
		for (int i=0; i<strings.length; i++)
		{
			if (strings[i].equals(string))
			{
				return i;
			}
		}
		return -1;
	}
}

