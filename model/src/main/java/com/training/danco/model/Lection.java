package com.training.danco.model;

import java.util.Date;

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

@Table(name = "lection")
@PrintableObject(name = "Lection")
public class Lection extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2546588600372557892L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@Printable(name = "ID", order = 1)
	private int id;

	@Column
	@Printable(order = 2)
	private String name;

	@Column
	@Printable(order = 3, isDetailedOnly = true)
	private Date date;

	@ManyToOne(targetEntity = Course.class)
	@JoinColumn(name = "courseId")
	@PrintableRef(name = "Course", order = 4)
	private Course course;

	public Lection(String name, Date date) {
		this.name = name;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
