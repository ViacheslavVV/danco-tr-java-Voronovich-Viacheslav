package com.training.danco.annotation;

import java.lang.reflect.Field;

public class FieldOrderItem {

	private Field field;
	private int order;
	
	public FieldOrderItem(Field field, int order) {
		this.field = field;
		this.order = order;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
