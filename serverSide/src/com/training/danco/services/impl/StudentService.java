package com.training.danco.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.dao.api.IStudentRepository;
import com.training.danco.model.Student;
import com.training.danco.services.api.IStudentService;

public class StudentService implements IStudentService {

	private static final Logger LOGGER = LogManager.getLogger(StudentService.class);
	
	private IStudentRepository studentRepository;
	private ICourseRepository courseRepository;
	
	public StudentService(IStudentRepository studentRepository, ICourseRepository courseRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	public boolean set(Student student) {
		boolean result = true;
		try {
			result = studentRepository.set(student);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public Student get(int id) {
		
		Student resultStudent = null;
		try {
			resultStudent = this.studentRepository.get(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return resultStudent;
	}

	@Override
	public boolean update(Student student) {
		
		Student resultStudent = null;
		try {
			
			resultStudent = this.get(student.getId());
			
			if (resultStudent == null){
				if (this.set(student))
				{
					resultStudent = student;
				}
			}
			
			this.studentRepository.update(resultStudent);
		
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultStudent = null;
		}
		return resultStudent != null;
	}

	@Override
	public boolean delete(Student student) {
		boolean result = true;
		try {
			result = this.studentRepository.delete(student, this.courseRepository);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public Student[] getAll() {

		Student[] resultStudents = null;
		try {
			resultStudents = this.studentRepository.getAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resultStudents;
	}

	@Override
	public int getCount() {
		
		int count = 0;
		try {
			count = this.studentRepository.getCount();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return count;
	}

}
