package com.training.danco.model;

import com.danco.training.annotation.Printable;
import com.danco.training.annotation.PrintableObject;
import com.danco.training.annotation.PrintableRef;

@PrintableObject
public class Student extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 109375858079835116L;
	@Printable(name="ID", order = 1)
	private int id;
	@Printable(order = 2, name = "Name")
	private String name;
	@Printable(order = 3, isDetailedOnly = true, name = "Age")
	private int age;
	@PrintableRef(name = "Course", order = 4)
	private Course course;
	
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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
