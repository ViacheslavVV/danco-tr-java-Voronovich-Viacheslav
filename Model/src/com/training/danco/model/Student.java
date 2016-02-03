package com.training.danco.model;

import com.danco.training.annotation.PrintableObject;

@PrintableObject
public class Student extends Man {

	/**
	 * 
	 */
	private static final long serialVersionUID = 109375858079835116L;
	private static int studentId = 1;
	public static void setStudentId(int id)
	{
		studentId = id;
	}
	
	public Student(String name, int age) {
		super(name, age);
		this.id = studentId++;
	}

}
