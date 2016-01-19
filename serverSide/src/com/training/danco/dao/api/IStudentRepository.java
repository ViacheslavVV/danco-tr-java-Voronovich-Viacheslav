package com.training.danco.dao.api;

import java.util.List;

import com.training.danco.model.Student;

public interface IStudentRepository {

	public boolean set(Student student);
	public Student get(int id);
	public boolean update(Student student);
	public boolean delete(Student student,ICourseRepository courseRepository);
	public List<Student> getAll();
	public int getCount();
}
