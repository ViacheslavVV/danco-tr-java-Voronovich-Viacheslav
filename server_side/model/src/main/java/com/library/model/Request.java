package com.library.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "request")
public class Request extends BaseModel {

	private static final long serialVersionUID = 1073861872223201517L;

	private Integer id;
	private User user;
	private Book book;
	private Date handOutDate;
	private Date handOverDate;
	private Date deadline;

	public Request() {
	}

	public Request(User user, Book book, Date handOutDate, Date handOverDate, Date deadline) {
		this.user = user;
		this.book = book;
		this.handOutDate = handOutDate;
		this.handOverDate = handOverDate;
		this.deadline = deadline;
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
	@JoinColumn(name = "userId")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookId")
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "handOutDate", nullable = true, length = 19)
	public Date getHandOutDate() {
		return this.handOutDate;
	}

	public void setHandOutDate(Date handOutDate) {
		this.handOutDate = handOutDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "handOverDate", nullable = true, length = 19)
	public Date getHandOverDate() {
		return this.handOverDate;
	}

	public void setHandOverDate(Date handOverDate) {
		this.handOverDate = handOverDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deadline", nullable = true, length = 19)
	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

}
