package com.training.danco.model;

import java.io.Serializable;

public abstract class BaseModel implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2731347744121501881L;

	public abstract int getId();

	public abstract void setId(int id);
	
}
