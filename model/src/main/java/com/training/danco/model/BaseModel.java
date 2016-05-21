package com.training.danco.model;

import java.io.Serializable;

public abstract class BaseModel implements Serializable{
	
	private static final long serialVersionUID = 2731347744121501881L;

	public abstract Integer getId();

	public abstract void setId(Integer id);
	
	@Override
	public int hashCode() {
		return (getId() != null) ? (this.getClass().hashCode() + getId()) : super.hashCode();

	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof BaseModel) && (getId() != 0) ? getId() == (((BaseModel) obj)
				.getId()) : (obj == this);
	}
}
