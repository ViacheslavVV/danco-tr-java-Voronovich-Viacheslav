package com.training.danco.model;

import com.danco.training.annotation.PrintableObject;

@PrintableObject
public class Lecturer extends Man {

	/**
	 * 
	 */
	private static final long serialVersionUID = -245821430584477660L;
	
	public Lecturer(String name, int age) {
		super(name, age);
	}

}
