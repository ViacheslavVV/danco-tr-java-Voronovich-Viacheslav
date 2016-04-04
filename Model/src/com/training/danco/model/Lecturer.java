package com.training.danco.model;

import java.util.List;

import com.danco.training.annotation.Printable;
import com.danco.training.annotation.PrintableObject;
import com.danco.training.annotation.PrintableRef;

@PrintableObject
public class Lecturer extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -245821430584477660L;
	@Printable(name="ID", order = 1)
	private int id;
	@Printable(order = 2, name = "Name")
	private String name;
	@Printable(order = 3, isDetailedOnly = true, name = "Age")
	private int age;
	@PrintableRef(name = "Courses", order = 4)
	private List<Course> courses;

	public Lecturer(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void setCourses(List<Course> courses){
		this.courses = courses;
	}
	
	public List<Course> getCourses(){
		return this.courses;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
