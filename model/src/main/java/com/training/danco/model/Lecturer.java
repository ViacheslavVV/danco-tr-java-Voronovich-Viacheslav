package com.training.danco.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.training.danco.annotation.Printable;
import com.training.danco.annotation.PrintableObject;
import com.training.danco.annotation.PrintableRef;
@Entity
@Table(name = "Lecturer")
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
	private Integer age;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lecturer")
	@PrintableRef(name = "Courses", order = 4)
	private List<Course> courses;

	public Lecturer(){
		
	}
	
	public Lecturer(String name, Integer age) {
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
