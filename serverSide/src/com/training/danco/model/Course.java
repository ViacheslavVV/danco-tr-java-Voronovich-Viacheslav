package com.training.danco.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4287990527523836622L;
	static private int courseId = 1;
	static public void setCourseId(int id)
	{
		courseId = id;
	}
	
	private String name;
	private Date startDate;
	private Date finalDate;
	private Lecturer lecturer;  // navigation fields
	private List<Student> students; //
	private List<Lection> lections; //
	private int maxStudents;
	private int maxLections;

	public Course(String name, Date startDate, Date finalDate, int maxStudents, int maxLections) {
		this.name = name;
		this.startDate = startDate;
		this.finalDate = finalDate;
		this.id = courseId++;
		this.students = new ArrayList<Student>();
		this.lections = new ArrayList<Lection>();
		this.maxStudents = maxStudents;
		this.maxLections = maxLections;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
	
	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	
	public List<Student> getStudents() {
		return this.students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public boolean setStudent(Student student)
	{
		if (this.maxStudents > this.students.size()){
			this.students.add(student);
			return true;
		}
		return false;
	}

	public boolean setLection(Lection lection)
	{
		if (this.maxLections > this.lections.size()){
			this.lections.add(lection);
			return true;
		}
		return false;
	}
	
	public boolean removeStudent(Student student)
	{
		for (int i=0; i < this.students.size(); i++){
			if (this.students.get(i).getId() == student.getId()){
				this.students.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean removeLection(Lection lection)
	{
		for (int i=0; i < this.lections.size(); i++){
			if (this.lections.get(i).getId() == lection.getId()){
				this.lections.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public List<Lection> getLections() {
		return this.lections;
	}

	public void setLections(List<Lection> lections) {
		this.lections = lections;
	}

	public int getMaxStudents() {
		return this.maxStudents;
	}

	public int getMaxLections() {
		return this.maxLections;
	}
}
