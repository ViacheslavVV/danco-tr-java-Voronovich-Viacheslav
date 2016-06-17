package com.library.model;

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

@Entity
@Table(name = "catalog")
public class Catalog extends BaseModel {

	private static final long serialVersionUID = -7941836137907750730L;
	
	private Integer id;
	private Catalog parentCatalog;
	private String title;
	private List<Catalog> catalogs;
	private List<Book> books;

	public Catalog() {
	}

	public Catalog(Catalog parentCatalog, String title, List<Catalog> catalogs, List<Book> books) {
		this.parentCatalog = parentCatalog;
		this.title = title;
		this.catalogs = catalogs;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	public Catalog getParentCatalog() {
		return this.parentCatalog;
	}

	public void setParentCatalog(Catalog parentCatalog) {
		this.parentCatalog = parentCatalog;
	}

	@Column(name = "title", length = 40)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCatalog")
	public List<Catalog> getCatalogs() {
		return this.catalogs;
	}

	public void setCatalogs(List<Catalog> catalogs) {
		this.catalogs = catalogs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "catalog")
	public List<Book> getBooks() {
		return this.books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
