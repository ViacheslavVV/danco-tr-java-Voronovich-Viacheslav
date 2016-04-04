package com.training.danco.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.training.danco.annotation.Printable;
import com.training.danco.annotation.PrintableObject;
import com.training.danco.annotation.PrintableRef;

@Table(name = "student")
@PrintableObject
public class Student extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 109375858079835116L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@Printable(name="ID", order = 1)
	private int id;
	
	@Column
	@Printable(order = 2, name = "Name")
	private String name;
	
	@Column
	@Printable(order = 3, isDetailedOnly = true, name = "Age")
	private int age;
	
	@ManyToOne(targetEntity = Course.class)
	@JoinColumn(name = "courseId")
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
