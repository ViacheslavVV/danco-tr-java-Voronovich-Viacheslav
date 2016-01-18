package com.training.danco.model;

public class Lecturer extends Man {

	private static int lecturerId = 1;
	public static void setLecturerId(int id){
		lecturerId = id;
	}
	
	public Lecturer(String name, int age) {
		super(name, age);
	this.id = lecturerId++;
	}

}
