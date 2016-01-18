package com.training.danco.dao.impl;

import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.dao.api.IStudentRepository;
import com.training.danco.model.*;

public class StudentRepository implements IStudentRepository {

	private Student[] students;
	
	public StudentRepository(Student[] students) {
		this.students = students;
	}

	@Override
	public boolean set(Student student) {
		int index = getVocantStudentNumber();
		if (index != -1)
		{
			students[index] = student;
			return true;
		}
		return false;
	}

	@Override
	public Student get(int id) {
		int index = getStudentIndexById(id);
		if (index != -1)
		{
			return students[index];
		}
		return null;
	}

	@Override
	public boolean update(Student student) {
		int index = getStudentIndexById(student.getId());
		if (index != -1)
		{
			students[index] = student;
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Student student, ICourseRepository courseRepository) {
		int index = getStudentIndexById(student.getId());
		if (index != -1)
		{
			for (Course course : courseRepository.getAll())
			{
				course.removeStudent(student);
			}
			students[index] = null;
			return true;
		}
		return false;
	}

	@Override
	public Student[] getAll() {

		return getNotNullStudents();
	}

	private int getVocantStudentNumber()
	{
		for (int i=0; i<students.length; i++)
		{
			if (students[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int getStudentIndexById(int id)
	{
		for (int i=0; i<students.length; i++)
		{
			if (students[i] == null)
			{
				continue;
			}
			
			if (students[i].getId() == id)
			{
				return i;
			}
		}
		return -1;
	}

	@Override
	public int getCount() {
		int count = 0;
		for (Student student : this.students)
		{
			if (student != null)
			{
				count++;
			}
		}
		return count;
	}
	
	private Student[] getNotNullStudents()
	{
		Student[] notNullStudents = new Student[getCount()];
		int currentIndex =0;
		for (Student student: this.students)
		{
			if (student != null)
			{
				notNullStudents[currentIndex++] = student;
			}
		}
		return notNullStudents;
	}
	
}
