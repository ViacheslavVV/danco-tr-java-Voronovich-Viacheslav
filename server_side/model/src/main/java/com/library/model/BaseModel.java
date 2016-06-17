package com.library.model;

import java.io.Serializable;

public abstract class BaseModel implements Serializable{
	
	private static final long serialVersionUID = 2731347744121501881L;

	public abstract Integer getId();

	public abstract void setId(Integer id);
	
}
