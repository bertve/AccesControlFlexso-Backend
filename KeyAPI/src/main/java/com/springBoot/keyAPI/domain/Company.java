package com.springBoot.keyAPI.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Table
public class Company extends Audit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long companyId;
	@NotNull
	@Column(unique=true)
	private String name;
	@OneToMany(mappedBy="company", cascade=CascadeType.ALL)
	private Set<Office> offices;
	public Company(String name) {
		super();
		this.name = name;
		this.offices = new HashSet<>();
	}

	public Company() {
	}

	public Set<Office> getOffices() {
		return offices;
	}
	public void setOffices(Set<Office> offices) {
		this.offices = offices;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
