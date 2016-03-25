package com.training.danco.model;

import com.danco.training.annotation.PrintableObject;

@PrintableObject
public class Student extends Man {

	/**
	 * 
	 */
	private static final long serialVersionUID = 109375858079835116L;
	
	private Course course;
	
	public Student(String name, int age) {
		super(name, age);
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
