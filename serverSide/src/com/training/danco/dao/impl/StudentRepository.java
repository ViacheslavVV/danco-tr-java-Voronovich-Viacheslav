package com.training.danco.dao.impl;

import java.util.Collections;
import java.util.List;

import com.training.danco.comparator.Comparator;
import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.dao.api.IStudentRepository;
import com.training.danco.model.*;

public class StudentRepository implements IStudentRepository {

	private List<Student> students;
	
	public StudentRepository(List<Student> students) {
		this.students = students;
	}

	@Override
	public boolean set(Student student) {
		return this.students.add(student);
	}

	@Override
	public Student get(int id) {
		int index = getStudentIndexById(id);
		if (index != -1)
		{
			return this.students.get(index);
		}
		return null;
	}

	@Override
	public boolean update(Student student) {
		int index = getStudentIndexById(student.getId());
		if (index != -1)
		{
			this.students.set(index, student);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Student student, ICourseRepository courseRepository) {
		int index = getStudentIndexById(student.getId());
		if (index != -1)
		{
			return this.students.remove(index) != null;
		}
		return false;
	}

	@Override
	public List<Student> getAll() {
		
		Collections.sort(this.students,Comparator.STUDENT_ID_COMPARATOR);
		return this.students;
	}
	
	private int getStudentIndexById(int id)
	{
		for (int i=0; i<this.students.size(); i++)
		{			
			if (this.students.get(i).getId() == id)
			{
				return i;
			}
		}
		return -1;
	}

	@Override
	public int getCount() {
		
		return this.students.size();
	}
	
}
