package com.training.danco.services.api;

import java.util.List;

import com.training.danco.model.Student;

public interface IStudentService {
	
	public Integer set(Student student);
	public Student get(Integer id);
	public Boolean update(Student student);
	public Boolean delete(Integer studentId);
	public List<Student> getAll();
	public Integer getCount();
	public List<Student> getStudentsByCourse(Integer courseId);
	public List<Student> getStudentsExceptCourse(Integer courseId);
}
