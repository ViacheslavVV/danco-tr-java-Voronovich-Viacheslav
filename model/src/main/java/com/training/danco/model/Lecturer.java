package com.training.danco.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.training.danco.annotation.Printable;
import com.training.danco.annotation.PrintableObject;
import com.training.danco.annotation.PrintableRef;

@Table(name = "lecturer")
@PrintableObject
public class Lecturer extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -245821430584477660L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@Printable(name="ID", order = 1)
	private Integer id;
	
	@Column
	@Printable(order = 2, name = "Name")
	private String name;
	
	@Column
	@Printable(order = 3, isDetailedOnly = true, name = "Age")
	private int age;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
