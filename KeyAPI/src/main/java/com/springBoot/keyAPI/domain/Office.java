package com.springBoot.keyAPI.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springBoot.keyAPI.domain.Address;

@Entity
@Table
public class Office extends Audit implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long officeId;
	
	@NotNull
	@Embedded
	private Address address;
	
	@ManyToOne(fetch=FetchType.LAZY,optional = false)
	@JoinColumn(name="companyId",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE )
	@JsonIgnore
	private Company company;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="officeAuthorizedPerson",
	joinColumns=@JoinColumn(name="officeId"),
	inverseJoinColumns=@JoinColumn(name="personId"))
	@JoinColumn()
	private Set<AuthorizedPerson> authorizedPersons;


	public Office() {
	}

	public Office(Address address) {
		super();
		this.address = address;
		this.authorizedPersons = new HashSet<>();
	}

	public void addAuthorizedPerson(AuthorizedPerson a) {
		this.authorizedPersons.add(a);
	}
	
	public void removeAuthorizedPerson(AuthorizedPerson a) {
		this.authorizedPersons.remove(a);
	}

	public void setCompany(Company c) {
		this.company = c;
	}

	public long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(long officeId) {
		this.officeId = officeId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Company getCompany() {
		return company;
	}

	public Set<AuthorizedPerson> getAuthorizedPersons() {
		return authorizedPersons;
	}

	public void setAuthorizedPersons(Set<AuthorizedPerson> authorizedPersons) {
		this.authorizedPersons = authorizedPersons;
	}
}
