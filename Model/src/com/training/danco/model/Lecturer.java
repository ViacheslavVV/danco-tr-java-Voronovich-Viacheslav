package com.training.danco.model;

import java.util.List;

import com.danco.training.annotation.PrintableObject;
import com.danco.training.annotation.PrintableRef;

@PrintableObject
public class Lecturer extends Man {

	/**
	 * 
	 */
	private static final long serialVersionUID = -245821430584477660L;
	@PrintableRef(name = "Courses", order = 4)
	private List<Course> courses;
	
	public Lecturer(String name, int age) {
		super(name, age);
	}
	
	public void setCourses(List<Course> courses){
		this.courses = courses;
	}
	
	public List<Course> getCourses(){
		return this.courses;
	}

}
