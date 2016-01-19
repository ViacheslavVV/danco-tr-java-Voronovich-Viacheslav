package com.training.danco.services.api;

import java.util.List;

import com.training.danco.model.Student;

public interface IStudentService {
	
	public boolean set(Student student);
	public Student get(int id);
	public boolean update(Student student);
	public boolean delete(Student student);
	public List<Student> getAll();
	public int getCount();
}
