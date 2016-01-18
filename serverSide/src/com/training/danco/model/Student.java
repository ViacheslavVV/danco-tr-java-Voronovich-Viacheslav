package com.training.danco.model;

public class Student extends Man {

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
