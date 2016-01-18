package com.training.danco.model;

import java.util.Date;

public class Lection extends BaseModel{

	static private int lectionId = 1;
	static public void setLectionId(int id)	{
		lectionId = id;
	}
	
	private String name;
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
