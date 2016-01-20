package com.training.danco.facade.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.training.danco.controller.*;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.*;
import com.training.danco.text.converter.*;

public class Facade implements IFacade{

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
	
	private DataDeserializer dataDeserializer;
	private DataSerializer dataSerializer;
	
	private String fileName = "file.txt";
	
	private Facade() throws RuntimeException
	{
		if (!setFileName(DEFAULT_FILE_NAME)){
			
			throw new RuntimeException("Can't create file with name '"+DEFAULT_FILE_NAME+"' !");
		}
		dataDeserializer = new DataDeserializer(this.fileName);
		dataSerializer = new DataSerializer(this.fileName);
		this.courseController = this.dataDeserializer.getCourseController();
		this.lectionController = this.dataDeserializer.getLectionController();
		this.lecturerController = this.dataDeserializer.getLecturerController();
		this.studentController = this.dataDeserializer.getStudentController();
		
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
	
	public List<Course> getAllCourses()
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
	
	public List<Lection> getLectionsByCourse(int courseId){
		
		return courseController.getLectionsByCourse(courseId);
	}
	
	public List<Student> getStudentsByCourse(int courseId){
		
		return courseController.getStudentsByCourse(courseId);
	}
	
	public List<Course> getCoursesInInterval(Date dateFrom, Date dateTo)
	{
		return courseController.getCoursesInInterval(dateFrom, dateTo);
	}
	
	public List<Course> getCoursesSortedByStartDate()
	{
		return courseController.getSortedByStartDate();
	}
	
	public List<Course> getCoursesSortedByStudentsCount()
	{
		return courseController.getSortedByStudentsCount();
	}
	
	public List<Course> getCoursesSortedByLecturer()
	{
		return courseController.getSortedByLecturer();
	}
	
	public List<Course> getCoursesSortedByName()
	{
		return courseController.getSortedByName();
	}
	
	public List<Course> getCurrentCoursesSortedByStartDate()
	{
		return courseController.getCurrentCoursesSortedByStartDate();
	}
	
	public List<Course> getCurrentCoursesSortedByStudentsCount()
	{
		return courseController.getCurrentCoursesSortedByStudentsCount();
	}
	
	public List<Course> getCurrentCoursesSortedByLecturer()
	{
		return courseController.getCurrentCoursesSortedByLecturer();
	}
	
	public List<Course> getCurrentCoursesSortedByName()
	{
		return courseController.getCurrentCoursesSortedByName();
	}
	
	public List<Course> getCoursesAfterDateSortedByStartDate(Date date)
	{
		return courseController.getCoursesAfterDateSortedByStartDate(date);
	}
	
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Date date)
	{
		return courseController.getCoursesAfterDateSortedByStudentsCount(date);
	}
	
	public List<Course> getCoursesAfterDateSortedByLecturer(Date date)
	{
		return courseController.getCoursesAfterDateSortedByLecturer(date);
	}
	
	public List<Course> getCoursesAfterDateSortedByName(Date date)
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
	
	public List<Lection> getAllLections()
	{
		return lectionController.getAll();
	}

	public List<Lection> getLectionsSortedByDate()
	{
		return lectionController.getSortedByDate();
	}
	
	public List<Lection> getLectionsSortedByName()
	{
		return lectionController.getSortedByName();
	}
	
	public List<Lection> getLectionsByDate(Date date)
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
	
	public List<Lecturer> getAllLecturers()
	{
		return lecturerController.getAll();
	}

	public List<Lecturer> getLecturersSortedByName()
	{
		return lecturerController.getSortedByName();
	}
	
	public List<Lecturer> getLecturersSortedByCoursesCount()
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
	
	public List<Student> getAllStudents()
	{
		return studentController.getAll();
	}
	
	public int getStudentsCount()
	{
		return studentController.getCount();
	}
	
	public void loadDataFromFIle() 
	{
		try {
			dataDeserializer.loadData();
		} catch (IOException e) {
			throw new RuntimeException("Data hasn't been loaded from file!");
		}
		
		this.courseController = dataDeserializer.getCourseController();
		
		this.lectionController = dataDeserializer.getLectionController();
		
		this.lecturerController = dataDeserializer.getLecturerController();
		
		this.studentController = dataDeserializer.getStudentController();
	}

	public void saveDataToFile()
	{
		try {
			dataSerializer.saveData(this.getAllStudents(),this.getAllLections(),this.getAllLecturers(),this.getAllCourses());
		} catch (IOException e) {
			throw new RuntimeException("Data hasn't been saved to file!");
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
		this.fileName = fileName;
		return true;
	}

	@Override
	public boolean cloneCourse(int courseId) {
		return this.courseController.cloneCourse(courseId);
	}

}
