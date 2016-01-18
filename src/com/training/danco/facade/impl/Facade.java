package com.training.danco.facade.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.danco.training.TextFileWorker;
import com.training.danco.controller.*;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.*;
import com.training.danco.text.converter.*;

public class Facade implements IFacade{

	private static final int MAX_REPOSITORY_SIZE = 100;
	private static final String DEFAULT_FILE_NAME = "file.txt";
	
	private static Facade facade;
	
	public  static Facade getInstance()
	{
		if (facade == null){
			facade = new Facade(); 
		}
		return facade;
	}
	
	private CourseController courseController;
	private LectionController lectionController;
	private LecturerController lecturerController;
	private StudentController studentController;
	
	private DataReader dataReader;
	private DataWriter dataWriter;
	
	private TextFileWorker textFileWorker;
	
	private Facade() throws RuntimeException
	{
		if (!setFileName(DEFAULT_FILE_NAME)){
			
			throw new RuntimeException("Can't create file with name '"+DEFAULT_FILE_NAME+"' !");
		}
	}

	//Course Region
	
	public boolean setCourse(Course course)
	{
		return courseController.setCourse(course);
	}
	
	public Course getCourse(int courseId)
	{
		return courseController.getCourse(courseId);
	}
	
	public boolean updateCourse(Course course)
	{
		return courseController.updateCourse(course);
	}
	
	public boolean deleteCourse(int courseId)
	{
		return courseController.deleteCourse(courseId);
	}
	
	public Lecturer getLecturerByCourse(int courseId){
		return courseController.getLecturerByCourse(courseId);
	}
	
	public Course[] getAllCourses()
	{
		return courseController.getAll();
	}
	
	public boolean setLecturerToCourse(int courseId, int lecturerId)
	{
		return courseController.setLecturer(courseId, lecturerId);
	}
	
	public boolean addLectionToCourse(int courseId, int lectionId)
	{
		return courseController.addLection(courseId, lectionId);
	}
	
	public boolean addStudentToCourse(int courseId, int studentId)
	{
		return courseController.addStudent(courseId, studentId);
	}
	
	public boolean removeLectionFromCourse(int courseId, int lectionId)
	{
		return courseController.removeLection(courseId, lectionId);
	}
	
	public boolean removeStudentFromCourse(int courseId, int studentId)
	{
		return courseController.removeStudent(courseId, studentId);
	}
	
	public int getCoursesCount()
	{
		return courseController.getCount();
	}
	
	public Lection[] getLectionsByCourse(int courseId){
		
		return courseController.getLectionsByCourse(courseId);
	}
	
	public Student[] getStudentsByCourse(int courseId){
		
		return courseController.getStudentsByCourse(courseId);
	}
	
	public Course[] getCoursesInInterval(Date dateFrom, Date dateTo)
	{
		return courseController.getCoursesInInterval(dateFrom, dateTo);
	}
	
	public Course[] getCoursesSortedByStartDate()
	{
		return courseController.getSortedByStartDate();
	}
	
	public Course[] getCoursesSortedByStudentsCount()
	{
		return courseController.getSortedByStudentsCount();
	}
	
	public Course[] getCoursesSortedByLecturer()
	{
		return courseController.getSortedByLecturer();
	}
	
	public Course[] getCoursesSortedByName()
	{
		return courseController.getSortedByName();
	}
	
	public Course[] getCurrentCoursesSortedByStartDate()
	{
		return courseController.getCurrentCoursesSortedByStartDate();
	}
	
	public Course[] getCurrentCoursesSortedByStudentsCount()
	{
		return courseController.getCurrentCoursesSortedByStudentsCount();
	}
	
	public Course[] getCurrentCoursesSortedByLecturer()
	{
		return courseController.getCurrentCoursesSortedByLecturer();
	}
	
	public Course[] getCurrentCoursesSortedByName()
	{
		return courseController.getCurrentCoursesSortedByName();
	}
	
	public Course[] getCoursesAfterDateSortedByStartDate(Date date)
	{
		return courseController.getCoursesAfterDateSortedByStartDate(date);
	}
	
	public Course[] getCoursesAfterDateSortedByStudentsCount(Date date)
	{
		return courseController.getCoursesAfterDateSortedByStudentsCount(date);
	}
	
	public Course[] getCoursesAfterDateSortedByLecturer(Date date)
	{
		return courseController.getCoursesAfterDateSortedByLecturer(date);
	}
	
	public Course[] getCoursesAfterDateSortedByName(Date date)
	{
		return courseController.getCoursesAfterDateSortedByName(date);
	}
	
	//Lection Region
	
	public boolean setLection(Lection lection)
	{
		return lectionController.setLection(lection);
	}
	
	public Lection getLection(int lectionId)
	{
		return lectionController.getLection(lectionId);
	}
	
	public boolean updateLection(Lection lection)
	{
		return lectionController.updateLection(lection);
	}
	
	public boolean deleteLection(int lectionId)
	{
		return lectionController.deleteLection(lectionId);
	}
	
	public Lection[] getAllLections()
	{
		return lectionController.getAll();
	}

	public Lection[] getLectionsSortedByDate()
	{
		return lectionController.getSortedByDate();
	}
	
	public Lection[] getLectionsSortedByName()
	{
		return lectionController.getSortedByName();
	}
	
	public Lection[] getLectionsByDate(Date date)
	{
		return lectionController.getLectionsByDate(date);
	}
	
	public int getLectionsCount()
	{
		return lectionController.getCount();
	}
	
	//LEcturer Region
	
	public boolean setLecturer(Lecturer lecturer)
	{
		return lecturerController.setLecturer(lecturer);
	}
	
	public Lecturer getLecturer(int lecturerId)
	{
		return lecturerController.getLecturer(lecturerId);
	}
	
	public boolean updateLecturer(Lecturer lecturer)
	{
		return lecturerController.updateLecturer(lecturer);
	}
	
	public boolean deleteLecturer(int lecturerId)
	{
		return lecturerController.deleteLecturer(lecturerId);
	}
	
	public Lecturer[] getAllLecturers()
	{
		return lecturerController.getAll();
	}

	public Lecturer[] getLecturersSortedByName()
	{
		return lecturerController.getSortedByName();
	}
	
	public Lecturer[] getLecturersSortedByCoursesCount()
	{
		return lecturerController.getSortedByCoursesCount();
	}
	
	public int getLecturersCount()
	{
		return lecturerController.getCount();
	}
	
	// Student Region
	
	public boolean setStudent(Student student)
	{
		return studentController.setStudent(student);
	}
	
	public Student getStudent(int studentId)
	{
		return studentController.getStudent(studentId);
	}
	
	public boolean updateStudent(Student student)
	{
		return studentController.updateStudent(student);
	}
	
	public boolean deleteStudent(int studentId)
	{
		return studentController.deleteStudent(studentId);
	}
	
	public Student[] getAllStudents()
	{
		return studentController.getAll();
	}
	
	public int getStudentsCount()
	{
		return studentController.getCount();
	}
	
	//
	
	public void loadDataFromFIle() 
	{
		dataReader = new DataReader(
				MAX_REPOSITORY_SIZE, MAX_REPOSITORY_SIZE, MAX_REPOSITORY_SIZE, MAX_REPOSITORY_SIZE, this.textFileWorker);
		try {
			dataReader.loadData();
		} catch (IOException e) {
			throw new RuntimeException("Data hasn't been loaded from file!");
		}
		
		this.courseController = dataReader.getCourseController();
		
		this.lectionController = dataReader.getLectionController();
		
		this.lecturerController = dataReader.getLecturerController();
		
		this.studentController = dataReader.getStudentController();
	}

	public void saveDataToFile()
	{
		dataWriter = new DataWriter(this.courseController, this.lectionController, this.lecturerController, this.studentController, this.textFileWorker);
		try {
			dataWriter.saveData();
		} catch (IOException e) {
			throw new RuntimeException("Output error");
		}
	}
	
	public boolean setFileName(String fileName)
	{
		File file = new File(fileName);
		
		if (!file.exists())
		{
			try {
				if (!file.createNewFile())
				{
					return false;
				}
			} catch (IOException e) {
				return false;
			}
		}
		this.textFileWorker = new TextFileWorker(fileName);
		return true;
	}

}
