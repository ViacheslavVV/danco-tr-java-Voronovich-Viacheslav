package com.training.danco.model;

import com.danco.training.annotation.PrintableObject;

@PrintableObject
public class Lecturer extends Man {

	/**
	 * 
	 */
	private static final long serialVersionUID = -245821430584477660L;
	private static int lecturerId = 1;
	public static void setLecturerId(int id){
		lecturerId = id;
	}
	
	public Lecturer(String name, int age) {
		super(name, age);
	this.id = lecturerId++;
	}

}
