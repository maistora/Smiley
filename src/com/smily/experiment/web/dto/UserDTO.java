package com.smily.experiment.web.dto;

import java.math.BigInteger;

public class UserDTO {

	private BigInteger	id;
	private String  login;
	private String  password;
	private String  email;
	private BigInteger companyId;

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

	public BigInteger getCompanyId() {
		return companyId;
	}

	public void setCompanyId(BigInteger companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return String.format("=== User DTO ===\n" +
				"ID: %s\n" +
				"Login: %s\n" +
				"Password: %s\n" +
				"Email: %s\n" +
				"Company ID: %s\n", getId(), getLogin(), getPassword(), getEmail(), getCompanyId());
	}
}
