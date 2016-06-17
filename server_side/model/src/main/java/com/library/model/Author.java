package com.library.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "author")
public class Author extends BaseModel {

	private static final long serialVersionUID = 1104225196887349693L;
	
	private int id;
	private String firstName;
	private String secondName;
	private String lastName;
	private Date birthDate;
	private Date deathDate;
	private List<Book> books;

	public Author() {
	}

	public Author(String firstName, String secondName, String lastName, Date birthDate, Date deathDate,
			List<Book> books) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.deathDate = deathDate;
		this.books = books;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "firstName", length = 20)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "secondName", length = 20)
	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Column(name = "lastName", length = 20)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "birthDate", length = 10)
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "deathDate", length = 10)
	public Date getDeathDate() {
		return this.deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	public List<Book> getBooks() {
		return this.books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
