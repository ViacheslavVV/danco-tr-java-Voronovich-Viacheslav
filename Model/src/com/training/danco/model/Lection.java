package com.training.danco.model;

import java.util.Date;

import com.danco.training.annotation.Printable;
import com.danco.training.annotation.PrintableObject;

@PrintableObject(name = "Lecture")
public class Lection extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2546588600372557892L;

	@Printable(order=2)
	private String name;
	@Printable(order=3, isDetailedOnly = true)
	private Date date;
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
	
}
