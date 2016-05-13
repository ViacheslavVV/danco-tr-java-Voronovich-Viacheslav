package com.training.danco.annotation;

import java.lang.reflect.Field;

public class FieldOrderItem {

	private Field field;
	private Integer order;
	
	public FieldOrderItem(Field field, Integer order) {
		this.field = field;
		this.order = order;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
