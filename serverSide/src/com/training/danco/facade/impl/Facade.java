package com.training.danco.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.dim.DependencyInjectionManager;
import com.training.danco.controller.*;
import com.training.danco.data.manager.api.*;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.*;
import com.training.danco.text.converter.DataConverter;
import com.training.danco.text.io.api.IExporter;
import com.training.danco.text.io.api.IImporter;

public class Facade implements IFacade{
	
	private static final Logger LOGGER = LogManager.getLogger(Facade.class);
	
	private CourseController courseController;
	private LectionController lectionController;
	private LecturerController lecturerController;
	private StudentController studentController;
	
	private IDeserializer dataDeserializer;
	private ISerializer dataSerializer;
	private DataConverter dataConverter;
	
	private IImporter importer;
	private IExporter exporter;
	
	public Facade()
	{
		this.importer = (IImporter)DependencyInjectionManager.getClassInstance(IImporter.class);
		this.exporter = (IExporter)DependencyInjectionManager.getClassInstance(IExporter.class);
		this.dataDeserializer = (IDeserializer)DependencyInjectionManager.getClassInstance(IDeserializer.class);
		this.dataSerializer = (ISerializer)DependencyInjectionManager.getClassInstance(ISerializer.class);
		this.dataConverter = new DataConverter();
		List<Object> data  = this.dataDeserializer.getDataObjects();
		this.dataConverter.convertObjectsToEntities(data);
		this.dataConverter.fillControllers();
		this.fillControllersFromConvertor();
	}

	private void fillControllersFromConvertor() {
				
		this.courseController = this.dataConverter.getCourseController();
		this.lectionController = this.dataConverter.getLectionController();
		this.lecturerController = this.dataConverter.getLecturerController();
		this.studentController = this.dataConverter.getStudentController();
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
	
	public boolean loadDataFromFIle() 
	{
		List<Object> data  = this.dataDeserializer.getDataObjects();
		if (this.dataConverter.convertObjectsToEntities(data)){
			this.dataConverter.fillControllers();
			this.fillControllersFromConvertor();
			return true;
		}
		return false;
	}

	public boolean saveDataToFile()
	{
		Object data = this.dataConverter.convertDataToObject(this.getAllStudents(), this.getAllLections(), this.getAllLecturers(), this.getAllCourses());
		return this.dataSerializer.saveData(data);
	}

	@Override
	public boolean cloneCourse(int courseId) {
		return this.courseController.cloneCourse(courseId);
	}

	@Override
	public boolean importCourses(String fileName) {
		boolean result = false;
		List<Course> courses = this.importer.importCourses(fileName,this);
		if (courses == null){
			return result;
		}
		
		for(Course course : courses){
			if (this.updateCourse(course)){
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean importStudents(String fileName) {
		boolean result = false;
		List<Student> students = this.importer.importStudents(fileName);
		if (students == null){
			return result;
		}
		
		for(Student student : students){
			if (this.updateStudent(student)){
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean importLections(String fileName) {
		boolean result = false;
		List<Lection> lections = this.importer.importLections(fileName);
		if (lections == null){
			return result;
		}
		
		for(Lection lection : lections){
			if (this.updateLection(lection)){
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean importLecturers(String fileName) {
		boolean result = false;
		List<Lecturer> lecturers = this.importer.importLecturers(fileName);
		if (lecturers == null){
			return result;
		}
		
		for(Lecturer lecturer : lecturers){
			if (this.updateLecturer(lecturer)){
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean exportAllCourses(String fileName) {
		
		return this.exporter.exportCourses(fileName, this.getAllCourses());
	}

	@Override
	public boolean exportAllStudents(String fileName) {
		
		return this.exporter.exportStudents(fileName, this.getAllStudents());
	}

	@Override
	public boolean exportAllLections(String fileName) {

		return this.exporter.exportLections(fileName, this.getAllLections());
	}

	@Override
	public boolean exportAllLecturers(String fileName) {

		return this.exporter.exportLecturers(fileName, this.getAllLecturers());
	}

	@Override
	public boolean exportCourses(String fileName, List<Object> courseIds) {
		
		List<Course> courses = new ArrayList<Course>();
		try{
			Course course;
			for (Object courseId : courseIds){
				course = this.getCourse((int)courseId);
				if (course != null){
					courses.add(course);
				}
			}
			return this.exporter.exportCourses(fileName, courses);
		}catch (Exception e){
			LOGGER.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean exportStudents(String fileName, List<Object> studentIds) {
		List<Student> students = new ArrayList<Student>();
		try{
			Student student;
			for (Object studentId : studentIds){
				student = this.getStudent((int)studentId);
				if (student != null){
					students.add(student);
				}
			}
			return this.exporter.exportStudents(fileName, students);
		}catch (Exception e){
			LOGGER.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean exportLections(String fileName, List<Object> lectionIds) {
		List<Lection> lections = new ArrayList<Lection>();
		try{
			Lection lection;
			for (Object lectionId : lectionIds){
				lection = this.getLection((int)lectionId);
				if (lection != null){
					lections.add(lection);
				}
			}
			return this.exporter.exportLections(fileName, lections);
		}catch (Exception e){
			LOGGER.error(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean exportLecturers(String fileName, List<Object> lecturerIds) {
		List<Lecturer> lecturers = new ArrayList<Lecturer>();
		try{
			Lecturer lecturer;
			for (Object lecturerId : lecturerIds){
				lecturer = this.getLecturer((int)lecturerId);
				if (lecturer != null){
					lecturers.add(lecturer);
				}
			}
			return this.exporter.exportLecturers(fileName, lecturers);
		}catch (Exception e){
			LOGGER.error(e.getMessage());
		}
		return false;
	}

}