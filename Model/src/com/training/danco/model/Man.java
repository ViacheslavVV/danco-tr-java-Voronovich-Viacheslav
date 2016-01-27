package com.training.danco.model;

public class Man extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3158557603167860255L;
	protected String name;
	protected int age;
	
	
	public Man(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
