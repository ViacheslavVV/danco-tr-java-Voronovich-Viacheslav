package com.training.danco.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.training.danco.annotation.Printable;
import com.training.danco.annotation.PrintableObject;
import com.training.danco.annotation.PrintableRef;

@Entity
@Table(name = "Course")
@PrintableObject
public class Course extends BaseModel implements Cloneable {
	
	private static final long serialVersionUID = -4287990527523836622L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@Printable(name = "ID", order = 1)
	private Integer id;

	@Column
	@Printable(order = 2)
	private String name;

	@Column
	@Printable(order = 3, isDetailedOnly = true)
	private Date startDate;

	@Column
	@Printable(order = 4, isDetailedOnly = true)
	private Date finalDate;

	@ManyToOne(targetEntity = Lecturer.class)
	@JoinColumn(name = "lecturerId")
	@PrintableRef(name = "Lecturer", order = 5)
	private Lecturer lecturer;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	@PrintableRef(name = "Students", order = 9)
	private List<Student> students;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	@PrintableRef(name = "Lections", order = 7)
	private List<Lection> lections;

	@Column
	@Printable(order = 8, isDetailedOnly = true)
	private Integer maxStudents;

	@Column
	@Printable(order = 6, isDetailedOnly = true)
	private Integer maxLections;

	public Course() {

	}

	public Course(String name, Date startDate, Date finalDate, Integer maxStudents, Integer maxLections) {
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

	public List<Lection> getLections() {
		return this.lections;
	}

	public void setLections(List<Lection> lections) {
		this.lections = lections;
	}

	public Integer getMaxStudents() {
		return this.maxStudents;
	}

	public Integer getMaxLections() {
		return this.maxLections;
	}

	public Course clone() {
		Course course = new Course(this.name, this.startDate, this.finalDate, this.maxStudents, this.maxLections);
		course.setLections(this.lections);
		course.setLecturer(this.lecturer);
		return course;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;

	}
	
	public void setMaxStudents(Integer maxStudents) {
		this.maxStudents = maxStudents;
	}

	public void setMaxLections(Integer maxLections) {
		this.maxLections = maxLections;
	}
}
