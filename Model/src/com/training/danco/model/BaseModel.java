package com.training.danco.model;

import java.io.Serializable;

import com.danco.training.annotation.Printable;

public class BaseModel implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2731347744121501881L;
	@Printable(name="ID", order = 1)
	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
