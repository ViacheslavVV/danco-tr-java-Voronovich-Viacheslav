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
	static private int lectionId = 1;
	static public void setLectionId(int id)	{
		lectionId = id;
	}

	@Printable(order=2)
	private String name;
	@Printable(order=3, isDetailedOnly = true)
	private Date date;
		
	public Lection(String name, Date date) {
		this.name = name;
		this.date = date;
		this.id = lectionId++;
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
	
}
