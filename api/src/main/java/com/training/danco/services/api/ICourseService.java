package com.training.danco.services.api;

import java.util.Date;
import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.params.CourseDateParam;
import com.training.danco.params.SortingParam;

public interface ICourseService {

	public Integer set(Course course);
	public Course get(Integer id);
	public Boolean update(Course course);
	public Boolean delete(Integer courseId);
	public List<Course> getAll();
	public Boolean cloneCourse(Course course);
	public Integer getCount();
	
	public Boolean setLecturer(Integer courseId, Integer lecturerId);
	public Boolean addLection(Integer courseId, Integer lectionId);
	public Boolean addStudent(Integer courseId, Integer studentId);
	public Boolean removeLection(Integer courseId, Integer lectionId);
	public Boolean removeStudent(Integer courseId, Integer studentId);
	
	public List<Course> getCoursesByStudent(Integer studentId);
	
	public List<Course> getCoursesByLecturer(Integer lecturerId);
	
	public List<Course> getCoursesInInterval(Date dateFrom, Date dateTo);

	public List<Course> getSorted(CourseDateParam courseDateParam, SortingParam sortingParam,
			Date date);
}
