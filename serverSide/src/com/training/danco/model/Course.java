package com.training.danco.model;

import java.util.Date;

public class Course extends BaseModel{

	static private int courseId = 1;
	static public void setCourseId(int id)
	{
		courseId = id;
	}
	
	private String name;
	private Date startDate;
	private Date finalDate;
	private Lecturer lecturer;  // navigation fields
	private Student[] students; //
	private Lection[] lections; //
	private int maxStudents;
	private int maxLections;

	public Course(String name, Date startDate, Date finalDate, int maxStudents, int maxLections) {
		this.name = name;
		this.startDate = startDate;
		this.finalDate = finalDate;
		this.id = courseId++;
		this.students = new Student[maxStudents];
		this.lections = new Lection[maxLections];
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
	
	public Student[] getStudents() {
		return getNotNullStudents();
	}

	public void setStudents(Student[] students) {
		this.students = students;
	}
	
	public boolean setStudent(Student student)
	{
		int index = getVocantStudentNumber();
		if (index != -1)
		{
			students[index] = student;
			return true;
		}
		return false;
	}

	public boolean setLection(Lection lection)
	{
		int index = getVocantLectionNumber();
		if (index != -1)
		{
			lections[index] = lection;
			return true;
		}
		return false;
	}
	
	public boolean removeStudent(Student student)
	{
		int index = getStudentIndexById(student.getId());
		if (index != -1)
		{
			students[index] = null;
			return true;
		}
		return false;
	}
	
	public boolean removeLection(Lection lection)
	{
		int index = getLectionIndexById(lection.getId());
		if (index != -1)
		{
			lections[index] = null;
			return true;
		}
		return false;
	}
	
	public Lection[] getLections() {
		return getNotNullLections();
	}

	public void setLections(Lection[] lections) {
		this.lections = lections;
	}
	
	private int getVocantLectionNumber()
	{
		for (int i=0; i<lections.length; i++)
		{
			if (lections[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int getLectionIndexById(int id)
	{
		for (int i=0; i<lections.length; i++)
		{
			if (lections[i] == null)
			{
				continue;
			}
			
			if (lections[i].getId() == id)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int getVocantStudentNumber()
	{
		for (int i=0; i<students.length; i++)
		{
			if (students[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int getStudentIndexById(int id)
	{
		for (int i=0; i<students.length; i++)
		{
			if (students[i] == null)
			{
				continue;
			}
			
			if (students[i].getId() == id)
			{
				return i;
			}
		}
		return -1;
	}
	
	private Lection[] getNotNullLections()
	{
		Lection[] notNullLections = new Lection[getLectionsCount()];
		int currentIndex =0;
		for (Lection lection: this.lections)
		{
			if (lection != null)
			{
				notNullLections[currentIndex++] = lection;
			}
		}
		return notNullLections;
	}
	
	public int getLectionsCount() {
		int count = 0;
		for (Lection lection: this.lections)
		{
			if (lection != null)
			{
				count++;
			}
		}
		return count;
	}
	
	private Student[] getNotNullStudents()
	{
		Student[] notNullStudents = new Student[getStudentsCount()];
		int currentIndex =0;
		for (Student student: this.students)
		{
			if (student != null)
			{
				notNullStudents[currentIndex++] = student;
			}
		}
		return notNullStudents;
	}
	
	public int getStudentsCount() {
		int count = 0;
		for (Student student: this.students)
		{
			if (student != null)
			{
				count++;
			}
		}
		return count;
	}

	public int getMaxStudents() {
		return maxStudents;
	}

	public int getMaxLections() {
		return maxLections;
	}
}
