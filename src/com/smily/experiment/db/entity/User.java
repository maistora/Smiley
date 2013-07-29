package com.smily.experiment.db.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private BigInteger id;
	
	@Column(name = "login", length = 40, nullable = false)
	private String login;
	
	@Column(name = "password", length = 120, nullable = false)
	private String password;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Cascade(value = {CascadeType.SAVE_UPDATE, CascadeType.PERSIST})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_company", nullable = false)
	private Company company;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return String.format("=== User ===\n" +
				"ID: %s\n" +
				"Login: %s\n" +
				"Password: %s\n" +
				"Email: %s\n" +
				"Company ID: %s\n",
				getId(), getLogin(), getPassword(), getEmail(), getCompany().getId());
	}
}
