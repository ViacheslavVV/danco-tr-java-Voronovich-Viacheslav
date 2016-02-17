package com.training.danco.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.danco.training.annotation.Printable;
import com.danco.training.annotation.PrintableObject;
import com.danco.training.annotation.PrintableRef;

@PrintableObject
public class Course extends BaseModel implements Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4287990527523836622L;
	
	@Printable(order=2)
	private String name;
	@Printable(order=3, isDetailedOnly = true)
	private Date startDate;
	@Printable(order=4, isDetailedOnly = true)
	private Date finalDate;
	@PrintableRef(name = "Lecturer", order = 5)
	private Lecturer lecturer;  
	@PrintableRef(name = "Students", order = 9)
	private List<Student> students; 
	@PrintableRef(name = "Lections", order = 7)
	private List<Lection> lections; 
	@Printable(order=8, isDetailedOnly = true)
	private int maxStudents;
	@Printable(order=6, isDetailedOnly = true)
	private int maxLections;

	public Course(String name, Date startDate, Date finalDate, int maxStudents, int maxLections) {
		this.name = name;
		this.startDate = startDate;
		this.finalDate = finalDate;
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
	
	@SuppressWarnings("deprecation")
	public List<Lection> getLectionByDate(Date date){
		List<Lection> result = new ArrayList<Lection>();
		Date lectionDate;
		for (Lection lection : this.lections){
			lectionDate = lection.getDate();
			if (lectionDate.getDay() == date.getDay() && lectionDate.getMonth() == date.getMonth() && lectionDate.getYear() == date.getYear()){
				result.add(lection);
			}
		}
		return result;
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
	
	public Course clone()
	{
		Course course = new Course(this.name, this.startDate, this.finalDate, this.maxStudents, this.maxLections);
		course.setLections(this.lections);
		course.setLecturer(this.lecturer);
		return course;
	}
}
