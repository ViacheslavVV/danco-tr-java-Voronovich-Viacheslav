package com.training.danco.services.api;

import java.util.Date;
import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;
import com.training.danco.params.CourseDateParam;
import com.training.danco.params.SortingParam;

public interface ICourseService {

	public Integer set(Course course);
	public Course get(Integer id);
	public Boolean update(Course course);
	public Boolean delete(Course course);
	public List<Course> getAll();
	public Boolean cloneCourse(Course course);
	public Integer getCount();
	
	public Boolean setLecturer(Course course, Lecturer lecturer);
	public Boolean addLection(Course course, Lection lection);
	public Boolean addStudent(Course course, Student student);
	public Boolean removeLection(Course course, Lection lection);
	public Boolean removeStudent(Course course, Student student);
	
	public List<Course> getCoursesInInterval(Date dateFrom, Date dateTo);

	public List<Course> getSorted(CourseDateParam courseDateParam, SortingParam sortingParam,
			Date date);
}
