package com.training.danco.controller;

import java.util.Date;
import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;
import com.training.danco.params.CourseDateParam;
import com.training.danco.params.SortingParam;
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
	
	public Integer setCourse(Course course)
	{
		return  this.courseService.set(course);
	}
	
	public Course getCourse(Integer courseId)
	{
		return this.courseService.get(courseId);
	}
	
	public Boolean updateCourse(Course course)
	{
		return this.courseService.update(course);
	}
	
	public Boolean deleteCourse(Integer id)
	{
		Course course = this.courseService.get(id);
		return this.courseService.delete(course);
	}
	
	public Boolean cloneCourse(Integer courseId){
		Course course = this.courseService.get(courseId);
		return this.courseService.cloneCourse(course);
	}
	
	public List<Course> getAll()
	{
		return this.courseService.getAll();
	}
	
	public Boolean setLecturer(Integer courseId, Integer lecturerId)
	{
		Course course = this.courseService.get(courseId);
		Lecturer lecturer = lecturerService.get(lecturerId);
		return this.courseService.setLecturer(course, lecturer);
	}
	
	public Boolean addLection(Integer courseId, Integer lectionId)
	{
		Course course = this.courseService.get(courseId);
		Lection lection = this.lectionService.get(lectionId);
		return this.courseService.addLection(course, lection);
	}
	
	public Boolean addStudent(Integer courseId, Integer studentId)
	{
		Course course = this.courseService.get(courseId);
		Student student = this.studentService.get(studentId);
		return this.courseService.addStudent(course, student);
	}
	
	public Boolean removeLection(Integer courseId, Integer lectionId)
	{
		Course course = this.courseService.get(courseId);
		Lection lection = this.lectionService.get(lectionId);
		return this.courseService.removeLection(course, lection);
	}
	
	public Boolean removeStudent(Integer courseId, Integer studentId)
	{
		Course course = this.courseService.get(courseId);
		Student student = this.studentService.get(studentId);
		return this.courseService.removeStudent(course, student);
	}
	
	public Integer getCount()
	{
		return this.courseService.getCount();
	}
	
	public List<Course> getCoursesInInterval(Date dateFrom, Date dateTo)
	{
		return this.courseService.getCoursesInInterval(dateFrom, dateTo);
	}
	
	public List<Course> getSorted(CourseDateParam courseDateParam, SortingParam sortingParam, Date date)
	{
		return this.courseService.getSorted(courseDateParam, sortingParam, date);
	}
	
}
