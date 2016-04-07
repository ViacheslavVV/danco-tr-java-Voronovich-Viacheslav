package com.training.danco.controller;

import java.util.List;

import com.training.danco.model.Student;
import com.training.danco.services.api.IStudentService;

public class StudentController {
	
	private IStudentService studentService;

	public StudentController(IStudentService studentService) {
		
		this.studentService = studentService;
	}
	
	public Integer setStudent(Student student)
	{
		return this.studentService.set(student);
	}
	
	public Student getStudent(int studentId)
	{
		return this.studentService.get(studentId);
	}
	
	public boolean updateStudent(Student student)
	{
		return this.studentService.update(student);
	}
	
	public boolean deleteStudent(int studentId)
	{
		Student student = this.studentService.get(studentId);
		return this.studentService.delete(student);
	}
	
	public List<Student> getAll()
	{
		return this.studentService.getAll();
	}
	
	public int getCount()
	{
		return this.studentService.getCount();
	}
}
