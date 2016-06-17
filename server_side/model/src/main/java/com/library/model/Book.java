package com.library.model;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "book")
public class Book extends BaseModel {

	private static final long serialVersionUID = -8081840769930160976L;
	
	private Integer id;
	private Author author;
	private Basket basket;
	private Catalog catalog;
	private String bookCode;
	private Date year;
	private String title;
	private String status;
	private List<Request> requests;

	public Book() {
	}

	public Book(Author author, Basket basket, Catalog catalog, String bookCode, Date year, String title, String status,
			List<Request> requests) {
		this.author = author;
		this.basket = basket;
		this.catalog = catalog;
		this.bookCode = bookCode;
		this.year = year;
		this.title = title;
		this.status = status;
		this.requests = requests;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "authorId")
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "basketId")
	public Basket getBasket() {
		return this.basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catalogId")
	public Catalog getCatalog() {
		return this.catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	@Column(name = "bookCode", length = 23)
	public String getBookCode() {
		return this.bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "year", length = 10)
	public Date getYear() {
		return this.year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	@Column(name = "title", length = 70)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "status", length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public List<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

}
