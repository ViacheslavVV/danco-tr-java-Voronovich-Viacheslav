package com.training.danco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.training.danco.annotation.Printable;
import com.training.danco.annotation.PrintableObject;
import com.training.danco.annotation.PrintableRef;

@Entity
@Table(name = "Student")
@PrintableObject
public class Student extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 109375858079835116L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@Printable(name = "ID", order = 1)
	private Integer id;

	@Column
	@Printable(order = 2, name = "Name")
	private String name;

	@Column
	@Printable(order = 3, isDetailedOnly = true, name = "Age")
	private Integer age;

	@ManyToOne(targetEntity = Course.class)
	@JoinColumn(name = "courseId")
	@PrintableRef(name = "Course", order = 4)
	private Course course;

	public Student() {

	}

	public Student(String name, Integer age) {
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
