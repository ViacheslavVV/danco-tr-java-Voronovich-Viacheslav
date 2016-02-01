package com.danco.training.annotation;

public class HelpObject {

	private Object object;

	protected HelpObject(Object object) {
		super();
		this.object = object;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
