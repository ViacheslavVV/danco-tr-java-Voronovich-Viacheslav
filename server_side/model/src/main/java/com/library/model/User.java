package com.library.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends BaseModel {

	private static final long serialVersionUID = -1797084336733179179L;

	private Integer id;
	private Basket basket;
	private String login;
	private String password;
	private String role;
	private List<Request> requests;
	private Profile profile;

	public User() {
	}

	public User(Basket basket, String login, String password, String role, List<Request> requests,
			Profile profile) {
		this.basket = basket;
		this.login = login;
		this.password = password;
		this.role = role;
		this.requests = requests;
		this.profile = profile;
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

	@OneToOne(fetch = FetchType.LAZY ) 
	@JoinColumn(name = "basketId")
	public Basket getBasket() {
		return this.basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	@Column(name = "login", length = 30)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password", length = 64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "role", length = 6)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public List<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
