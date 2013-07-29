package com.smily.experiment.db.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private BigInteger id;
	
	@Column(name = "name", length = 120)
	private String name;
	
	@Column(name = "address", length = 250)
	private String address;
	
	@Column(name = "capital")
	private BigDecimal capital;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}
	
	@Override
	public String toString() {
		return String.format("=== Company ===\n" +
				"ID: %s\n" +
				"Name: %s\n" +
				"Address: %s\n" +
				"Capital: %s\n", getId(), getName(), getAddress(), getCapital());
	}
}
