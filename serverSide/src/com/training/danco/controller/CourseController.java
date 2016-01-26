package com.training.danco.controller;

import java.util.Date;
import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;
import com.training.danco.services.api.ICourseService;
import com.training.danco.services.api.ILectionService;
import com.training.danco.services.api.ILecturerService;
import com.training.danco.services.api.IStudentService;

public class CourseController {

	private ICourseService courseService;
	private ILecturerService lecturerService;
	private ILectionService lectionService;
	private IStudentService studentService;

	public CourseController(ICourseService courseService, ILecturerService lecturerService,
			ILectionService lectionService, IStudentService studentService) {
		super();
		this.courseService = courseService;
		this.lecturerService = lecturerService;
		this.lectionService = lectionService;
		this.studentService = studentService;
	}
	
	public boolean setCourse(Course course)
	{
		return  this.courseService.set(course);
	}
	
	public Course getCourse(int courseId)
	{
		return this.courseService.get(courseId);
	}
	
	public boolean updateCourse(Course course)
	{
		return this.courseService.update(course);
	}
	
	public boolean deleteCourse(int id)
	{
		Course course = this.courseService.get(id);
		return this.courseService.delete(course);
	}
	
	public boolean cloneCourse(int courseId){
		Course course = this.courseService.get(courseId);
		return this.courseService.cloneCourse(course);
	}
	
	public List<Course> getAll()
	{
		return this.courseService.getAll();
	}
	
	public boolean setLecturer(int courseId, int lecturerId)
	{
		Course course = this.courseService.get(courseId);
		Lecturer lecturer = lecturerService.get(lecturerId);
		return this.courseService.setLecturer(course, lecturer);
	}
	
	public boolean addLection(int courseId, int lectionId)
	{
		Course course = this.courseService.get(courseId);
		Lection lection = this.lectionService.get(lectionId);
		return this.courseService.addLection(course, lection);
	}
	
	public boolean addStudent(int courseId, int studentId)
	{
		Course course = this.courseService.get(courseId);
		Student student = this.studentService.get(studentId);
		return this.courseService.addStudent(course, student);
	}
	
	public boolean removeLection(int courseId, int lectionId)
	{
		Course course = this.courseService.get(courseId);
		Lection lection = this.lectionService.get(lectionId);
		return this.courseService.removeLection(course, lection);
	}
	
	public boolean removeStudent(int courseId, int studentId)
	{
		Course course = this.courseService.get(courseId);
		Student student = this.studentService.get(studentId);
		return this.courseService.removeStudent(course, student);
	}
	
	public Lecturer getLecturerByCourse(int courseId){
		
		Course course = this.courseService.get(courseId);
		return this.courseService.getLecturerByCourse(course);
	}
	
	public int getCount()
	{
		return this.courseService.getCount();
	}
	
	public List<Lection> getLectionsByCourse(int courseId){
		
		Course course = this.courseService.get(courseId);
		return this.courseService.getLectionsByCourse(course);
	}
	
	public List<Student> getStudentsByCourse(int courseId){
		
		Course course = this.courseService.get(courseId);
		return this.courseService.getStudentsByCourse(course);
	}
	
	public List<Course> getCoursesInInterval(Date dateFrom, Date dateTo)
	{
		return this.courseService.getCoursesInInterval(dateFrom, dateTo);
	}
	public List<Course> getSortedByStartDate()
	{
		return this.courseService.getSortedByStartDate();
	}
	public List<Course> getSortedByStudentsCount()
	{
		return this.courseService.getSortedByStudentsCount();
	}
	public List<Course> getSortedByLecturer()
	{
		return this.courseService.getSortedByLecturer();
	}
	public List<Course> getSortedByName()
	{
		return this.courseService.getSortedByName();
	}
	public List<Course> getCurrentCoursesSortedByStartDate()
	{
		return this.courseService.getCurrentCoursesSortedByStartDate();
	}
	public List<Course> getCurrentCoursesSortedByStudentsCount()
	{
		return this.courseService.getCurrentCoursesSortedByStudentsCount();
	}
	public List<Course> getCurrentCoursesSortedByLecturer()
	{
		return this.courseService.getCurrentCoursesSortedByLecturer();
	}
	public List<Course> getCurrentCoursesSortedByName()
	{
		return this.courseService.getCurrentCoursesSortedByName();
	}
	public List<Course> getCoursesAfterDateSortedByStartDate(Date date)
	{
		return this.courseService.getCoursesAfterDateSortedByStartDate(date);
	}
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Date date)
	{
		return this.courseService.getCoursesAfterDateSortedByStudentsCount(date);
	}
	public List<Course> getCoursesAfterDateSortedByLecturer(Date date)
	{
		return this.courseService.getCoursesAfterDateSortedByLecturer(date);
	}
	public List<Course> getCoursesAfterDateSortedByName(Date date)
	{
		return this.courseService.getCoursesAfterDateSortedByName(date);
	}
	
}
